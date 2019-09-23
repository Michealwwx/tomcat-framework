package com.reign.bio.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ReignRequest
 * @Description 对请求的保证
 * @Author wuwenxiang
 * @Date 2019-09-23 15:38
 * @Version 1.0
 **/
public class ReignRequest {

    private String method;

    private String url;

    public ReignRequest(InputStream inputStream) {
        try {
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;

            if ((len = inputStream.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }

            System.out.println(content);
            //TODO 输出来的内容
            //GET /userServlet HTTP/1.1
            //Host: localhost:8080
            //Connection: keep-alive
            //Upgrade-Insecure-Requests: 1
            //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36
            //Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
            //Accept-Encoding: gzip, deflate, br
            //* Accept-Language: zh-CN,zh;q=0.9
            //Cookie: Idea-2faf78d5=caa0dd4e-abbc-4838-9cc1-27116ef94f1c; _ga=GA1.1.525811842.1551181707
            //先拿到第一行
            String line = content.split("\\n")[0];
            //空格为隔断；
            String[] arr = line.split("\\s");
            //简单粗暴的从请求中获取方法类型以及相应的url
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
