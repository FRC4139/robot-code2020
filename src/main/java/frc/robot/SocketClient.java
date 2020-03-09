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
                socket = new Socket("localhost",8888); // connects to a socket at localhost with port 8888 (change this to the jetson IP)
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
                            out.print("vision_request"+"\r\n"); // sends "vision_request" string to the socket server
                            out.flush();
                            System.out.println("Message sent");
                            System.out.println("Trying to read...");
                            String in = stdIn.readLine(); // gets for socket response (right, left, center -- needs optimization)
                            System.out.println("In: " + in); // prints response
                            Result.current_result = in; // stores the result in a private global variable
                            // }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
        sent.start(); // starts thread that gets vision status
        try {
            sent.join(); // waits for thread to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            socket.close(); // closes the socket gracefully - python socket server will crash if we don't close like this
        } catch(IOException e) {
            e.printStackTrace();
        }

        return Result.current_result; // return the result from the private global variable

    }

}
