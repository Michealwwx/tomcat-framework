package com.reign.netty.servlet;

import com.reign.netty.http.ReignNettyRequest;
import com.reign.netty.http.ReignNettyResponse;

/**
 * @ClassName: NettyOrderServlet
 * @Description: order
 * @Author: wuwx
 * @Date: 2019-09-25 13:27
 **/
public class NettyOrderServlet extends ReignNettyServlet {
    @Override
    public void doGet(ReignNettyRequest reignNettyRequest, ReignNettyResponse reignNettyResponse) {
        doPost(reignNettyRequest,reignNettyResponse);
    }

    @Override
    public void doPost(ReignNettyRequest reignNettyRequest, ReignNettyResponse reignNettyResponse) {
        reignNettyResponse.write("this is nettyOrderServlet");
    }
}
