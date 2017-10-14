package com.cheryl.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by cheryl on 2017/9/30.
 * 执行完线程自动销毁被GC
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println("timeServer receive order:"+body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new java.util.Date(
                        System.currentTimeMillis()).toString():"bad error";
                out.println(currentTime);
            }
        }catch (Exception e){
                if (in != null){
                    try{
                        in.close();
                    }catch (Exception el){
                        el.printStackTrace();
                    }
                }
                if(out != null){
                    out.close();
                    out=null;
                }
                if(socket !=null){
                    try{
                        socket.close();
                    }catch (Exception es){
                        es.printStackTrace();
                    }
                    socket = null;
                }
        }
    }
}
