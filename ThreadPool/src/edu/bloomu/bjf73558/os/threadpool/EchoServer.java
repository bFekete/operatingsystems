package edu.bloomu.bjf73558.os.threadpool;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The server opens a socket and listens for connections. When it accepts a
 * new connection, the server creates a Connection object and adds it to the
 * thread pool.
 * 
 * @author Brian Fekete
 */
public class EchoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            final int PORT = 8000;
            ServerSocket server = new ServerSocket(PORT);
            
            ThreadPool threadPool = new ThreadPool();
            
            while(true){
                Connection connection = new Connection(server.accept());
                threadPool.addTaskToQueue(connection);
            }
        } catch (IOException ex) {
            System.out.println("I/O error in EchoServer.\n" + ex.toString());
        }
    }
    
}
