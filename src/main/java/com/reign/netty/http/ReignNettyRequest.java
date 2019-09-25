package com.reign.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

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
        return httpRequest.uri();
    }

    public String getMethod() {
        return httpRequest.method().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(httpRequest.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return name;
        } else {
            return param.get(0);
        }


    }
}
