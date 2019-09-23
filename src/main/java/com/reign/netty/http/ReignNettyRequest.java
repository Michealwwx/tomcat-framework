package com.reign.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import java.io.InputStream;

/**
 * @ClassName ReignNettyRequest
 * @Description 请求
 * @Author wuwenxiang
 * @Date 2019-09-23 22:25
 * @Version 1.0
 **/
public class ReignNettyRequest {

    private ChannelHandlerContext channelHandlerContext;

    private HttpRequest httpRequest;

    public ReignNettyRequest(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        this.channelHandlerContext = channelHandlerContext;
        this.httpRequest = httpRequest;
    }

    public String getUrl() {
        return null;
    }
}
