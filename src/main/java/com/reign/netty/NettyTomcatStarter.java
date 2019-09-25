package com.reign.netty;

import com.reign.netty.tomcat.NettyTomcat;

/**
 * @ClassName BioTomcatStarter
 * @Description 采用netty重构tomcat IO模型
 * @Author wuwenxiang
 * @Date 2019-09-23 20:57
 * @Version 1.0
 **/
public class NettyTomcatStarter {

    public static void main(String[] args) {
        NettyTomcat nettyTomcat = new NettyTomcat();
        nettyTomcat.start();
    }
}
