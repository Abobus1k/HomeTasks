package edu.project3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static edu.project3.utils.StatusCodeParser.getResponseName;

public class MarkdownRender {

    private MarkdownRender() {}


    public static void printMarkdownResults(
        String path,
        String fromDateStr,
        String toDateStr,
        LogProcessor logProcessor
    ) {
        Map<String, Integer> resourceCount = logProcessor.getResourceCount();
        Map<Integer, Integer> responseCodeCount = logProcessor.getResponseCodeCount();
        Map<String, Integer> ipRequestCount = logProcessor.getIpRequestCount();
        Map<String, Integer> userAgentCount = logProcessor.getUserAgentCount();

        Integer totalRequests = logProcessor.getTotalRequests();
        int avgResponseSize = (int) logProcessor.getAverageResponseSize();

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/edu/project3/output/output.md"))) {

            writer.println("#### Общая информация\n");
            writer.println("| Метрика                  | Значение    |");
            writer.println("|:------------------------:|------------:|");
            writer.printf("| Файл(-ы)                          | %-16s |%n", path);
            writer.printf("| Начальная дата                    | %-16s |%n", fromDateStr != null ? fromDateStr : "-");
            writer.printf("| Конечная дата                     | %-16s |%n", toDateStr != null ? toDateStr : "-");
            writer.printf("| Количество запросов               | %-16d |%n", totalRequests);
            writer.printf("| Средний размер ответа в (байтах)  | %-16d |%n", avgResponseSize);

            writer.println("\n#### Запрашиваемые ресурсы\n");
            writer.println("|     Ресурс      | Количество |");
            writer.println("|:---------------:|-----------:|");

            List<Map.Entry<String, Integer>> sortedResources = new ArrayList<>(resourceCount.entrySet());
            sortedResources.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry : sortedResources) {
                String resource = String.format("%-150s", entry.getKey());
                String count = String.format("%-11d", entry.getValue());
                writer.println(String.format("|  %s | %s |", resource, count));
            }

            writer.println("\n#### Коды ответа\n");
            writer.println("| Код |          Имя          | Количество |");
            writer.println("|:---:|:---------------------:|-----------:|");

            List<Map.Entry<Integer, Integer>> sortedResponseCodes = new ArrayList<>(responseCodeCount.entrySet());
            sortedResponseCodes.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

            for (Map.Entry<Integer, Integer> entry : sortedResponseCodes) {
                String code = String.format("%-3d", entry.getKey());
                String name = String.format("%-24s", getResponseName(entry.getKey()));
                String count = String.format("%-13d", entry.getValue());
                writer.println(String.format("| %s | %s | %s |", code, name, count));
            }

            writer.println("\n#### Статистика по IP-адресам\n");
            writer.println("|      IP Address      | Количество запросов |");
            writer.println("|:---------------------:|---------------------:|");

            List<Map.Entry<String, Integer>> sortedIpRequests = new ArrayList<>(ipRequestCount.entrySet());
            sortedIpRequests.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry : sortedIpRequests) {
                String ipAddress = String.format("%-21s", entry.getKey());
                String requestCount = String.format("%-20d", entry.getValue());
                writer.println(String.format("| %s | %s |", ipAddress, requestCount));
            }

            writer.println("\n#### Статистика по User-Agent\n");
            writer.println("|         User-Agent         | Количество запросов |");
            writer.println("|:--------------------------:|---------------------:|");

            List<Map.Entry<String, Integer>> sortedUserAgentRequests = new ArrayList<>(userAgentCount.entrySet());
            sortedUserAgentRequests.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry : sortedUserAgentRequests) {
                String userAgent = String.format("| %-150s | %-12d |", entry.getKey(), entry.getValue());
                writer.println(userAgent);
            }

        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть файл");
        }
    }

}
