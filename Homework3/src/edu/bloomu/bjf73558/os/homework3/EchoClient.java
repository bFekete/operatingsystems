package edu.bloomu.bjf73558.os.homework3;

import java.io.IOException;
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
            Scanner sc = new Scanner(client.getInputStream());
            Connection connection = new Connection(client);
            while(true){
            
            }
        } catch (IOException ex) {
            System.out.println("IO Exception in Echo Client\n" + ex.toString());
        }
    }

}
