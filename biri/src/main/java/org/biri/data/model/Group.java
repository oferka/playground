package org.biri.data.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Group {

    @Id
    private String id;

    private String name;

    private String description;
}
