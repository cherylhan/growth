package com.cheryl.thread;

/**
 * Created by cheryl on 2017/10/14.
 * 多线程会加快程序的处理速度吗？性能更低
 * 计算机的速度并没有加快而是抢占了更多的带宽
 * 一个线程分配20m的资源，假设你起了多个线程则会分配到更多的带宽
 */
public class TraditionalThread {
    public static void main(String[] args) {
        //创建一个线程
        //运行线程时里面也有代码，存放代码的方法为run方法
        //想运行自己的r代码就需要重写run方法
        Thread thread = new Thread(){
            @Override
            public void run() {
             test();
            }
        };
        //启动一个线程
        thread.start();


        //将代码放到runnable对象中，再将runnable对象传给thread
        //更加体现面向对象的思维 new thread是一个线程
        //线程要运行的代码放到runnable中
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
             test();
            }
        });
        thread1.start();

       new Thread(){

       }.start();
    }

    public static void test(){
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }



}
