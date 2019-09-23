package com.reign.bio.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName ReignResponse
 * @Description 相应的包装
 * @Author wuwenxiang
 * @Date 2019-09-23 15:38
 * @Version 1.0
 **/
public class ReignResponse {

    private OutputStream outputStream;

    public ReignResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String s) {
        //因为我们用的是http协议，所以输出也必须用http 响应协议；
        //需要给一个状态码200,以及协议信息

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append(s);
        try {
            outputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
