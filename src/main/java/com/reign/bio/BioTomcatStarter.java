package com.reign.bio;

import com.reign.bio.tomcat.ReignTomcat;

/**
 * @ClassName BioTomcatStarter
 * @Description 启动类
 * @Author wuwenxiang
 * @Date 2019-09-23 20:04
 * @Version 1.0
 **/
public class BioTomcatStarter {
    public static void main(String[] args) {
        ReignTomcat tomcat = new ReignTomcat();
        tomcat.start();

    }

}
