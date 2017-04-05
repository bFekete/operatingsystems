package edu.bloomu.bjf73558.os.homework3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Brian Fekete
 */
public class Connection implements Runnable {

    private Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    /**
     * Has loop that that reads a line of text from the client and then writes
     * the same line back to the client. You may need to flush the output after
     * writing.
     */
    @Override
    public void run() {
        try {
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois;
            ois = new ObjectInputStream(socket.getInputStream());

            
            
            ois.close();
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.out.println("I/O error in Connection.");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing socket in Connection.");
            }
        } // End of Finally

    } // End of run
}
