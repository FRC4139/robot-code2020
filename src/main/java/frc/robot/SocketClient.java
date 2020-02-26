//package net.ultra03.WebsocketClientTest;
package frc.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClient {
    private static Thread sent;
    private static Thread receive;
    private static Socket socket;

    private static class Result {
        public static String current_result;
    }

    public static String getVisionStatus(){

            try {
                socket = new Socket("localhost",8888);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            sent = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        BufferedReader stdIn =new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        // while(true){
                            out.print("vision_request"+"\r\n");
                            out.flush();
                            System.out.println("Message sent");
                            System.out.println("Trying to read...");
                            String in = stdIn.readLine();
                            System.out.println("In: " + in);
                            Result.current_result = in;
                            // }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
        sent.start();
        try {
            sent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Result.current_result;

    }

}