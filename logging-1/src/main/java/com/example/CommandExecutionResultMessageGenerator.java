package com.example;

import com.example.CommandExecutionResult.Command;
import com.example.CommandExecutionResult.CompletionStatus;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
public class CommandExecutionResultMessageGenerator implements MessageGenerator {

    @Override
    public String generate() {
        CommandExecutionResult result = new CommandExecutionResult(
                getRandomCommand(),
                getRandomCompletionStatus(),
                LocalDateTime.now(),
                getRandomDuration()
        );
        return result.toString();
    }

    private Command getRandomCommand() {
        Command[] commands = Command.values();
        int randomIndex = RandomUtils.nextInt(0, commands.length);
        return commands[randomIndex];
    }

    private CompletionStatus getRandomCompletionStatus() {
        CompletionStatus[] completionStatuses = CompletionStatus.values();
        int randomIndex = RandomUtils.nextInt(0, completionStatuses.length);
        return completionStatuses[randomIndex];
    }

    private Duration getRandomDuration() {
        return Duration.ofMillis(RandomUtils.nextLong(1, 10000));
    }
}
