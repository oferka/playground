package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommandExecutionResult {

    private Command command;
    private CompletionStatus completionStatus;
    private LocalDateTime start;
    private Duration duration;

    @ToString
    @AllArgsConstructor
    enum Command {
        OPEN_DEVICE ("OPEN_DEVICE"),
        GO_TO_URL ("GO_TO_URL"),
        INSTALL_APPLICATION ("INSTALL_APPLICATION"),
        CLEAN_APPLICATION ("CLEAN_APPLICATION"),
        UNINSTALL_APPLICATION ("UNINSTALL_APPLICATION"),
        UNINSTALL_APPLICATIONS ("UNINSTALL_APPLICATIONS"),
        OPEN_APPLICATION ("OPEN_APPLICATION"),
        FIND_ELEMENT ("FIND_ELEMENT"),
        CLICK("CLICK"),
        SWIPE ("SWIPE"),
        CLOSE_APPLICATION ("CLOSE_APPLICATION"),
        CLOSE_DEVICE ("CLOSE_DEVICE");

        @Getter
        private String name;
    }

    @ToString
    @AllArgsConstructor
    enum CompletionStatus {
        PASS ("PASSED"),
        FAIL("FAILED"),
        DNF ("DNF");

        @Getter
        private String name;
    }
}
