package com.reign.netty.servlet;

import com.reign.netty.http.ReignNettyRequest;
import com.reign.netty.http.ReignNettyResponse;

/**
 * @ClassName: ReignNettyServlet
 * @Description: servlet
 * @Author: wuwx
 * @Date: 2019-09-25 13:25
 **/
public abstract class ReignNettyServlet {

    public void service(ReignNettyRequest reignNettyRequest, ReignNettyResponse reignNettyResponse){
        if(reignNettyRequest.getMethod().equalsIgnoreCase("GET")){
            doGet(reignNettyRequest,reignNettyResponse);
        }else {
            doPost(reignNettyRequest,reignNettyResponse);
        }
    }

    public abstract void doGet(ReignNettyRequest reignNettyRequest,ReignNettyResponse reignNettyResponse);

    public abstract void doPost(ReignNettyRequest reignNettyRequest,ReignNettyResponse reignNettyResponse);



}
