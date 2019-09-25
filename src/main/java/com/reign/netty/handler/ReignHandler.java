package com.reign.netty.handler;

import com.reign.bio.http.ReignRequest;
import com.reign.netty.http.ReignNettyRequest;
import com.reign.netty.http.ReignNettyResponse;
import com.reign.netty.tomcat.NettyTomcat;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @ClassName ReignHandler
 * @Description 自定义处理器
 * @Author wuwenxiang
 * @Date 2019-09-23 22:15
 * @Version 1.0
 **/
public class ReignHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest)msg;
            ReignNettyRequest reignNettyRequest = new ReignNettyRequest(ctx,request);
            ReignNettyResponse reignNettyResponse = new ReignNettyResponse(ctx,request);
            //实际业务处理
            String url = reignNettyRequest.getUrl();
            if(NettyTomcat.getServletMap().containsKey(url)){
                NettyTomcat.getServletMap().get(url).service(reignNettyRequest,reignNettyResponse);
            }

        }
    }
}
