package com.reign.bio.tomcat;

import com.reign.bio.http.ReignRequest;
import com.reign.bio.http.ReignResponse;
import com.reign.bio.http.ReignServlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName ReignTomcat
 * @Description 手写tomcat
 * @Author wuwenxiang
 * @Date 2019-09-23 15:25
 * @Version 1.0
 **/
public class ReignTomcat {

    private ServerSocket serverSocket;

    private int port;

    private Map<String, ReignServlet> servletMap = new HashMap<>();

    private Properties webXml = new Properties();

    /**
     * 1. 解析配置文件进行初始化
     */
    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();

            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webXml.load(fis);

            for (Object k : webXml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url", "");
                    String url = webXml.getProperty(key);
                    String className = webXml.getProperty(servletName + ".className");
                    ReignServlet obj = (ReignServlet) Class.forName(className).newInstance();
                    servletMap.put(url, obj);
                }
                if (key.endsWith("port")) {
                    port = Integer.parseInt(webXml.getProperty(key));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. tomcat启动
     */
    public void start() {
        //1.加载配置文件
        init();
        //2.启动线程监听请求
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Tomcat 已启动，端口号:"+port);
            while (true) {
                Socket socket = serverSocket.accept();
                process(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3.封装处理请求；
     */
    private void process(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        ReignRequest reignRequest = new ReignRequest(inputStream);
        ReignResponse reignResponse = new ReignResponse(outputStream);

        String url = reignRequest.getUrl();
        if(servletMap.containsKey(url)){
            servletMap.get(url).service(reignRequest,reignResponse);
        }else {
            reignResponse.write("404 - not found");
        }

        outputStream.flush();
        outputStream.close();

        inputStream.close();
        socket.close();

    }



}
