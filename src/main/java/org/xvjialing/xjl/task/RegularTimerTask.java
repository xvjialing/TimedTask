package org.xvjialing.xjl.task;

import org.xvjialing.xjl.socket.SocketUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

public class RegularTimerTask extends TimerTask{

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String tag;

    public RegularTimerTask(String tag) {
        System.out.println("jump to RegularTimerTask");
        this.tag = tag;
    }

    public void run() {
        System.out.println("RegularTimerTask run");
        try {
            SocketUtils socketUtils=new SocketUtils();
            socketUtils.sendMsg(tag);
            System.out.println("has send Message");
            socketUtils.closeSocket();
            System.out.println("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("has error");
            System.out.println(e.getMessage());
        }
    }
}
