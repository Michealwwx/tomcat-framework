package com.reign.bio.servlet;

import com.reign.bio.http.ReignRequest;
import com.reign.bio.http.ReignResponse;
import com.reign.bio.http.ReignServlet;

/**
 * @ClassName OrderServlet
 * @Description 订单Servlet
 * @Author wuwenxiang
 * @Date 2019-09-23 15:38
 * @Version 1.0
 **/
public class OrderServlet extends ReignServlet {
    @Override
    public void doGet(ReignRequest reignRequest, ReignResponse reignResponse) {
        this.doPost(reignRequest,reignResponse);
    }

    @Override
    public void doPost(ReignRequest reignRequest, ReignResponse reignResponse) {
        reignResponse.write("Order Servlet");
    }
}
