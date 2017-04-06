package edu.bloomu.bjf73558.os.threadpool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * The client tries to open a connection on the localhost. It reads a line of
 * text from the keyboard, writes it to the server and then writes the server's
 * response to the console. This continues until the the user enters a single
 * period, which terminates the connection.
 *
 * @author Brian Fekete
 */
public class EchoClient {

    public static void main(String[] args) {
        try {
            System.out.println("Echo client starting.");
            
            Socket client = new Socket("localhost", 8000);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            
            System.out.println("Echo client connected.");

            Scanner sc = new Scanner(System.in);

            while (client.isConnected()) {
                String clientInput = sc.nextLine();
                outputStream.write(clientInput + "\n");
                outputStream.flush(); // Needed
                if(clientInput.equals(".")){
                    break;
                } 
                System.out.println(inputStream.readLine());
           
            }
            inputStream.close();
            outputStream.close();

        } catch (IOException ex) {
            System.out.println("IO Exception in Echo Client\n" + ex.toString());
            System.out.println("Client done.");
            System.exit(1);
        }
        System.out.println("Client done.");
    }

}
