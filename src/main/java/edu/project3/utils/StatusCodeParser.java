package edu.project3.utils;

public class StatusCodeParser {
    private StatusCodeParser() {}

    @SuppressWarnings("MagicNumber")
    public static String getResponseName(int code) {
        return switch (code) {
            case 200 -> "OK";
            case 206 -> "Partial Content";
            case 304 -> "Not Modified";
            case 403 -> "Forbidden";
            case 416 -> "Range Not Satisfiable";
            case 404 -> "Not Found";
            default -> "Unknown";
        };
    }
}
