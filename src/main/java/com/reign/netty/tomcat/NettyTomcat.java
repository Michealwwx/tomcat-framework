package com.reign.netty.tomcat;

import com.reign.netty.handler.ReignHandler;
import com.reign.netty.servlet.ReignNettyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName NettyTomcat
 * @Description netty改造的tomcat
 * @Author wuwenxiang
 * @Date 2019-09-23 22:06
 * @Version 1.0
 **/
public class NettyTomcat {

    private int port;

    private static Map<String, ReignNettyServlet> servletMap = new HashMap<>();

    private Properties webXml = new Properties();

    public static Map<String, ReignNettyServlet> getServletMap() {
        return servletMap;
    }


    /**
     * 1. 解析配置文件进行初始化
     */
    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();

            FileInputStream fis = new FileInputStream(WEB_INF + "web1.properties");

            webXml.load(fis);

            for (Object k : webXml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url", "");
                    String url = webXml.getProperty(key);
                    String className = webXml.getProperty(servletName + ".className");
                    ReignNettyServlet obj = (ReignNettyServlet) Class.forName(className).newInstance();
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
        //2.netty
        //boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //netty服务
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    //主线程处理类
                    .channel(NioServerSocketChannel.class)
                    //子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            //编解码器
                            //TODO 对进入的请求解析解码，对返回的结果解析编码
                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new HttpRequestDecoder());

                            //业务处理
                            client.pipeline().addLast(new ReignHandler());

                        }
                    })
                    //针对主线程的配置，分配线程最大数量  128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //针对子线程的配置，保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f  = server.bind(port).sync();
            System.out.println("Netty - Tomcat  已启动,端口：" + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
