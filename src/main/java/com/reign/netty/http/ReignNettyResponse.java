package com.reign.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

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

}
