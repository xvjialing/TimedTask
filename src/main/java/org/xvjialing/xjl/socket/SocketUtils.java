package org.xvjialing.xjl.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketUtils {

    private static OutputStream outputStream;

    private static  Socket socket;
    public SocketUtils(){
        try {
            socket=new Socket("39.106.46.145",8234);
//            socket=new Socket("120.78.193.185",8234);
            outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        try {
            outputStream.write(msg.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeSocket(){
        try {
            outputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
