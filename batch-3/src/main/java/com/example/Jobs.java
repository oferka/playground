package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Jobs {

    CHUNKS(Names.CHUNKS_JOB_NAME),
    TASKLETS(Names.TASKLETS_JOB_NAME);

    @Getter
    private String name;

    public interface Names {
        String CHUNKS_JOB_NAME = "ChunksJob";
        String TASKLETS_JOB_NAME = "TaskletsJob";
    }
}
