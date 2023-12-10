package edu.hw6.task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String PATH = "/etc/services";

    private PortScanner() {}

    public static void printPortInfo(int port) {
        String protocol = getServiceProtocol(port);
        String serviceName = getServiceName(port);

        String fm = String.format("| %-8s | %-5d | %-25s |", protocol, port, isPortAvailable(port) ? "" : serviceName);

        LOGGER.info(fm);
        LOGGER.info("+----------+-------+---------------------------+");
    }

    public static boolean isPortAvailable(int port) {
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();

            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public static String getServiceName(int port) {
        String serviceName = "Unknown service";

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("\t" + port + "/")) {

                    int commentIndex = line.indexOf("\t");

                    if (commentIndex != -1) {
                        serviceName = line.substring(0, commentIndex).trim();
                    }

                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return serviceName;
    }

    public static String getServiceProtocol(int port) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("\t" + port + "/")) {
                    if (line.contains("/udp")) {
                        return Protocols.UDP.toString();
                    }
                    if (line.contains("/tcp")) {
                        return Protocols.TCP.toString();
                    }
                    break;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new String(sb);
    }

    @SuppressWarnings("MagicNumber")
    public static void renderMagicPorts() {
        printPortInfo(21);
        printPortInfo(22);
        printPortInfo(80);
        printPortInfo(123);
        printPortInfo(135);
        printPortInfo(137);
        printPortInfo(443);
        printPortInfo(1194);
        printPortInfo(5353);
        printPortInfo(5432);
        printPortInfo(6379);
        printPortInfo(6446);
    }
}
