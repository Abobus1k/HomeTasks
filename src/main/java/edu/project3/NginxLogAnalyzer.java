package edu.project3;

import java.time.LocalDateTime;
import static edu.project3.AdocRender.printAsciiDocResults;
import static edu.project3.MarkdownRender.printMarkdownResults;
import static edu.project3.utils.CmdParser.parseCommandLine;
import static edu.project3.utils.DateParser.parseCmdDate;
import static edu.project3.utils.StatsGenerator.statics;

public class NginxLogAnalyzer {
    private static final String MARKDOWN = "markdown";
    private static final String ADOC = "adoc";

    private static final Integer MINIMUM_ARGS_NUMBER = 2;

    private NginxLogAnalyzer() {}

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        if (args.length < MINIMUM_ARGS_NUMBER) {
            System.exit(1);
        }

        Options options = parseCommandLine(args);

        String path = options.get("path");
        String fromDateStr = options.get("from");
        String toDateStr = options.get("to");
        String format = options.get("format", MARKDOWN);

        LocalDateTime fromDate = parseCmdDate(fromDateStr);
        LocalDateTime toDate = parseCmdDate(toDateStr);

        Stats stats = new Stats(statics());

        LogProcessor logProcessor = new LogProcessor(stats);

        logProcessor.processLogs(path, fromDate, toDate);

        if (MARKDOWN.equalsIgnoreCase(format)) {
            printMarkdownResults(
                path,
                fromDateStr,
                toDateStr,
                logProcessor
            );
        } else if (ADOC.equalsIgnoreCase(format)) {
            printAsciiDocResults(
                path,
                fromDateStr,
                toDateStr,
                logProcessor
            );
        }
    }
}
