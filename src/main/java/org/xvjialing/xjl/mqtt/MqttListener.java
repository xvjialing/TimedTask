package org.xvjialing.xjl.mqtt;

public interface MqttListener {
    void connectionLost();

    void messageArrived(String msg);

}
