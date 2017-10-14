package com.cheryl.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cheryl on 2017/9/30.
 * BIO（Blocking I/O）阻塞I/O介绍
 *
 *等             1.系统调用
 *待             --------》
 *处     应                   操     无数据
 *理     用                   作     2.等待返回数据
 *数     程                   系     数据准备好
 *据     序                   统     数据从内核复制到用户空间
 *               《-------           数据拷贝完成
 *               4.程序可以使用数据
 *               在内核准备数据前是被挂起的
 *               BIO特点：I/O执行的两个阶段都被block
 *
 */
public class BioTimeServer {
    /**
     * @param args
     * @thros IOException
     * @author cheryl
    * */
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0 ){

            try {
                port = Integer.valueOf(args[0]);

            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The timeSever is start in port" + port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }finally {
            if(server != null){
               System.out.print("the time server close");
                server.close();
                server = null;
            }
        }
    }














}
