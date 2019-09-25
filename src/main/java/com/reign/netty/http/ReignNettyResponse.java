package com.reign.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.internal.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ReignNettyResponse
 * @Description 响应
 * @Author wuwenxiang
 * @Date 2019-09-23 22:25
 * @Version 1.0
 **/
public class ReignNettyResponse {
    //SocketChannel的封装
    private ChannelHandlerContext channelHandlerContext;

    private HttpRequest httpRequest;

    public ReignNettyResponse(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        this.channelHandlerContext = channelHandlerContext;
        this.httpRequest = httpRequest;
    }

    public void write(String msg){
        try {
            if(StringUtil.isNullOrEmpty(msg)){
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
            response.headers().set("Content-Type","text/html;");
            channelHandlerContext.write(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            //TODO  注意，这里写完数据后一定要flush 和关闭；不然无法响应；
            channelHandlerContext.flush();
            channelHandlerContext.close();
        }
    }

}
