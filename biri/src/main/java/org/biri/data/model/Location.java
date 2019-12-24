package org.biri.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Location {

    @Id
    private String id;

    private String name;

    private String description;

    private Address address;
}
