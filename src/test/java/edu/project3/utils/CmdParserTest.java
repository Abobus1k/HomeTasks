package edu.project3.utils;

import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CmdParserTest {

    public static Stream<Arguments> provideData() {
        return Stream.of(
            Arguments.of(
                (Object) new String[]{
                    "--path",
                    "src/main/java/edu/project3/logs/logs1.txt",
                    "--format",
                    "markdown",
                    "--from",
                    "2015-05-17"
                    },
                Map.of(
                    "path", "src/main/java/edu/project3/logs/logs1.txt",
                    "format", "markdown",
                    "from", "2015-05-17"
                )
            ),
            Arguments.of(
                (Object) new String[]{
                    "--path",
                    "src/main/java/edu/project3/logs/logs1.txt",
                    "--format",
                    "markdown",
                    "--from",
                    "2015-05-17",
                    "--to",
                    "2015-06-19"
                },
                Map.of(
                    "path", "src/main/java/edu/project3/logs/logs1.txt",
                    "format", "markdown",
                    "from", "2015-05-17",
                    "to", "2015-06-19"
                )
            ),
            Arguments.of(
                (Object) new String[]{
                    "--path",
                    "src/main/java/edu/project3/logs/logs1.txt",
                    "--format",
                    "adoc",
                    "--from",
                    "2023-05-17"
                },
                Map.of(
                    "path", "src/main/java/edu/project3/logs/logs1.txt",
                    "format", "adoc",
                    "from", "2023-05-17"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void testParseCommandLine(String[] args, Map<String, String> map) {
        Assertions.assertThat(CmdParser.parseCommandLine(args).options())
            .containsExactlyInAnyOrderEntriesOf(map);
    }
}
