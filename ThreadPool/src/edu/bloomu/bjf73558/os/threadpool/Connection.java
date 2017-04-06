package edu.bloomu.bjf73558.os.threadpool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian Fekete
 */
public class Connection implements Runnable {

    private final Socket socket;

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
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (socket.isConnected()) {
                String input = inputStream.readLine();
                outputStream.write("Server: " + input + "\n");
                outputStream.flush();

            }

        } catch (IOException ex) {
            try {
                socket.close();
            } catch (IOException ex1) {
                System.out.println("Error closing socket in Connection.\n" + ex.toString());
            }
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing socket in Connection.\n" + ex.toString());
            }
        } // End of Finally

    } // End of run
}
