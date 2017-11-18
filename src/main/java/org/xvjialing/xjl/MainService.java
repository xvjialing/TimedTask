package org.xvjialing.xjl;

import org.xvjialing.xjl.task.Task;
import org.xvjialing.xjl.task.TimerManager;

import java.util.ArrayList;
import java.util.List;

public class MainService {

    private static List<Task> taskList=new ArrayList<Task>();
    private static List<TimerManager> timerManagerList=new ArrayList<TimerManager>();


    public static void main(String[] args){

        getTaskList();


        startTimer();

        org.xvjialing.xjl.mqtt.MqttTool mqttTool=new org.xvjialing.xjl.mqtt.MqttTool();
        mqttTool.setOnMqttListener(new org.xvjialing.xjl.mqtt.MqttListener() {
            public void connectionLost() {
                System.out.println("connectionLost");
            }

            public void messageArrived(String msg) {
                System.out.println(msg);
                if (msg.equals("getTasks")){
                    cancleTimers();
                    getTaskList();
                    startTimer();
                }
            }
        });
        mqttTool.startListen();

    }

    public static void getTaskList(){
        org.xvjialing.xjl.okhttp.OkhttpUtils okhttpUtils=new org.xvjialing.xjl.okhttp.OkhttpUtils();
        taskList=okhttpUtils.getTasks();
    }

    public static void startTimer(){
        System.out.println("startTimer");
        if (taskList.size()==0){
            TimerManager timerManager = new TimerManager(23, 0, "", true);
            timerManagerList.add(timerManager);
        }
        for (Task item:taskList){
            TimerManager timerManager = new TimerManager(item.getHour(), item.getMinute(), item.getTag(), item.isRepeat());
            timerManagerList.add(timerManager);
        }
    }

    public static void cancleTimers(){
        System.out.println("cancleTimers");
        if (timerManagerList.size()!=0){
            for (TimerManager item:timerManagerList){
                item.stopTimer();
            }
        }
        timerManagerList.clear();
        taskList.clear();
    }

}
