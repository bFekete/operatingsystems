package edu.bloomu.bjf73558.os.homework3;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Socket client = new Socket("localhost", 8000);
        } catch (IOException ex) {
            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
