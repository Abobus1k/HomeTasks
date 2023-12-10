package edu.project3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static edu.project3.utils.StatusCodeParser.getResponseName;

public class AdocRender {

    private AdocRender() {}

    private static final String ADOC_STANDART = "|===\n";


    public static void printAsciiDocResults(
        String path,
        String fromDateStr,
        String toDateStr,
        LogProcessor logProcessor
    ) {
        Map<String, Integer> resourceCount = logProcessor.getResourceCount();
        Map<Integer, Integer> responseCodeCount = logProcessor.getResponseCodeCount();
        Map<String, Integer> ipRequestCount = logProcessor.getIpRequestCount();
        Map<String, Integer> userAgentCount = logProcessor.getUserAgentCount();

        int totalRequests = logProcessor.getTotalRequests();
        int avgResponseSize = (int) logProcessor.getAverageResponseSize();

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/edu/project3/output/output.adoc"))) {

            writer.println("== Общая информация\n");
            writer.println(ADOC_STANDART);
            writer.printf("| Метрика                  | Значение    \n");
            writer.printf("| Файл(-ы)                          | %-16s \n", path);
            writer.printf("| Начальная дата                    | %-16s \n", fromDateStr != null ? fromDateStr : "-");
            writer.printf("| Конечная дата                     | %-16s \n", toDateStr != null ? toDateStr : "-");
            writer.printf("| Количество запросов               | %-16d \n", totalRequests);
            writer.printf(
                "| Средний размер ответа в (байтах)  | %-16d \n", avgResponseSize);
            writer.println(ADOC_STANDART);

            writer.println("== Запрашиваемые ресурсы\n");
            writer.println(ADOC_STANDART);
            writer.printf("|     Ресурс      | Количество \n");

            List<Map.Entry<String, Integer>> sortedResources = new ArrayList<>(resourceCount.entrySet());
            sortedResources.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry : sortedResources) {
                String resource = String.format("| %-150s | %-11d \n", entry.getKey(), entry.getValue());
                writer.println(resource);
            }
            writer.println(ADOC_STANDART);

            writer.println("== Коды ответа\n");
            writer.println(ADOC_STANDART);
            writer.println("| Код |          Имя          | Количество \n");

            List<Map.Entry<Integer, Integer>> sortedResponseCodes = new ArrayList<>(responseCodeCount.entrySet());
            sortedResponseCodes.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

            for (Map.Entry<Integer, Integer> entry : sortedResponseCodes) {
                String code = String.format("| %-3d | %-24s | %-11d \n",
                    entry.getKey(),
                    getResponseName(entry.getKey()),
                    entry.getValue()
                );
                writer.println(code);
            }
            writer.println(ADOC_STANDART);

            writer.println("== Статистика по IP-адресам\n");
            writer.println(ADOC_STANDART);
            writer.println("|      IP Address      | Количество запросов \n");

            List<Map.Entry<String, Integer>> sortedIpRequests = new ArrayList<>(ipRequestCount.entrySet());
            sortedIpRequests.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry : sortedIpRequests) {
                String ipAddress = String.format("| %-21s | %-20d \n", entry.getKey(), entry.getValue());
                writer.println(ipAddress);
            }
            writer.println(ADOC_STANDART);

            writer.println("== Статистика по User-Agent\n");
            writer.println(ADOC_STANDART);
            writer.println("|         User-Agent         | Количество запросов \n");

            List<Map.Entry<String, Integer>> sortedUserAgentRequests = new ArrayList<>(userAgentCount.entrySet());
            sortedUserAgentRequests.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            for (Map.Entry<String, Integer> entry : sortedUserAgentRequests) {
                String userAgent = String.format("| %-150s | %-12d \n", entry.getKey(), entry.getValue());
                writer.println(userAgent);
            }
            writer.println(ADOC_STANDART);

        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть файл");
        }
    }

}
