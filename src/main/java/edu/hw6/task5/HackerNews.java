package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private HackerNews() {}

    private static final int OK = 200;
    private static final String NOT_FOUND = "Title Not Found";
    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_FORMAT = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    public static long[] hackerNewsTopStories() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(TOP_STORIES_URL))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == OK) {
                String json = response.body();
                String[] ids = json.replaceAll("[\\[\\]\"]", "").split(",");
                long[] result = new long[ids.length];
                for (int i = 0; i < ids.length; i++) {
                    result[i] = Long.parseLong(ids[i].trim());
                }
                return result;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new long[0];
    }

    public static String news(long id) {
        String itemUrl = String.format(ITEM_URL_FORMAT, id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(itemUrl))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == OK) {
                String json = response.body();
                Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
                Matcher matcher = pattern.matcher(json);

                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return NOT_FOUND;
    }
}
