package com.reign.bio.http;

/**
 * @ClassName ReignServlet
 * @Description 自定义servlet规范
 * @Author wuwenxiang
 * @Date 2019-09-23 15:38
 * @Version 1.0
 **/
public abstract class   ReignServlet {

    public  void service(ReignRequest reignRequest, ReignResponse reignResponse){
        if("GET".equalsIgnoreCase(reignRequest.getMethod())){
            doGet(reignRequest,reignResponse);
        }else {
            doPost(reignRequest,reignResponse);
        }

    }

    public abstract void doGet(ReignRequest reignRequest,ReignResponse reignResponse);

    public abstract void doPost(ReignRequest reignRequest,ReignResponse reignResponse);

}
