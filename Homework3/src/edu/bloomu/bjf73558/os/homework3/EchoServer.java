package edu.bloomu.bjf73558.os.homework3;

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
    public static void main(String[] args) throws IOException {
        final int PORT = 8000;
        ServerSocket server = new ServerSocket(PORT);
        
        ThreadPool threadPool = new ThreadPool();
        
        while(true){
            threadPool.addTaskToQueue(new Connection(server.accept()));
        }
    }
    
}
