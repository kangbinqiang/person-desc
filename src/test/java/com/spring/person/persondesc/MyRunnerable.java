package com.spring.person.persondesc;


public class MyRunnerable implements Runnable {
    static MyRunnerable myRunnerable = new MyRunnerable();
    @Override
    public void run() {
        try {
            System.out.print("start:my name is:"+ Thread.currentThread().getName()+"\n");
            long startTime = System.currentTimeMillis();
            Thread.sleep(3000);
            System.out.print("end:my name is:"+ Thread.currentThread().getName()+"\n");
            long endTime = System.currentTimeMillis();
            System.out.print((endTime-startTime)/1000 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(myRunnerable);
        thread.start();
    }
}
