package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client extends Thread {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 7777;
    private static final int BUFFER_SIZE = 1024;
    private final static Logger LOGGER = LogManager.getLogger();
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    public void run() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                LOGGER.info("You: ");
                if (scanner.hasNextLine()) {
                    String message = scanner.nextLine();

                    if ("exit".equalsIgnoreCase(message)) {
                        break;
                    }

                    sendMessage(message);

                    String response = receiveMessage();
                    LOGGER.info("Server: " + response);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) throws IOException {
        outputStream.write(message.getBytes());
    }

    public String receiveMessage() throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
