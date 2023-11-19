package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server extends Thread {
    private static final int PORT = 7777;
    private static final int MAX_CONNECTIONS = 2;
    private final static Logger LOGGER = LogManager.getLogger();
    private final ExecutorService executorService;
    private final String path;
    private static final int BUFFER_SIZE = 1024;

    public Server() {
        this.executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        this.path = "src/main/java/edu/hw8/task1/logs/server_log.txt";
    }

    public Server(String path) {
        this.executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        this.path = path;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Сервер запущен. Ожидание подключений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new ClientHandler(clientSocket, path));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private static class ClientHandler implements Runnable {
        private final InputStream inputStream;
        private final OutputStream outputStream;

        private final Socket clientSocket;
        private final String path;

        ClientHandler(Socket clientSocket, String path) {
            try {
                this.inputStream = clientSocket.getInputStream();
                this.outputStream = clientSocket.getOutputStream();
                this.clientSocket = clientSocket;
                this.path = path;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = inputStream.read(buffer);

                    if (bytesRead != -1) {
                        String request = new String(buffer, 0, bytesRead);
                        String response = getInsultForRequest(request);

                        saveToFile(clientSocket.getInetAddress().getHostAddress(), request, response);

                        outputStream.write(response.getBytes());
                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private String getInsultForRequest(String request) {
            return switch (request.toLowerCase()) {
                case "личности" -> "Не переходи на личности там, где их нет";
                case "оскорбления" ->
                    "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами";
                case "глупый" ->
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно. Ты просто бог идиотизма.";
                case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
                default -> "Не понял запроса. Попробуй еще раз.";
            };
        }

        private void saveToFile(String ipAddress, String request, String response) throws IOException {
            String logEntry = String.format("IP: %s, Request: %s, Response: %s%n", ipAddress, request, response);
            Path logFilePath = Path.of(path);

            Files.write(logFilePath, logEntry.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
    }
}
