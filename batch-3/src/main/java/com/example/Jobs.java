package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.example.Jobs.Names.CHUNKS_JOB_NAME;
import static com.example.Jobs.Names.TASKLETS_JOB_NAME;

@ToString
@AllArgsConstructor
public enum Jobs {

    CHUNKS(CHUNKS_JOB_NAME),
    TASKLETS(TASKLETS_JOB_NAME);

    @Getter
    private String name;

    public interface Names {
        String CHUNKS_JOB_NAME = "ChunksJob";
        String TASKLETS_JOB_NAME = "TaskletsJob";
    }
}
