package org.xvjialing.xjl.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Calendar;

public class MqttTool {

    private static final String topic="lightControl";

    private static MqttClient mqttClient;
    private static MqttListener mqttListener;

    public MqttTool(){
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        String clientId="backService"+ Calendar.getInstance().getTimeInMillis();
        try {
            mqttClient = new MqttClient("tcp://45.77.159.109:32768",clientId, memoryPersistence);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(2000);
            mqttConnectOptions.setKeepAliveInterval(1000);
            mqttClient.connect(mqttConnectOptions);


        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void setOnMqttListener(MqttListener mqttListener){
        this.mqttListener=mqttListener;
    }

    public void startListen(){
        try {
            mqttClient.subscribe(topic);
            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    mqttListener.connectionLost();
                }

                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    mqttListener.messageArrived(mqttMessage.toString());
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
