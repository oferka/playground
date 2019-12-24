package org.biri.data.model;

import lombok.Data;

@Data
public abstract class ScheduledEvent {

    private String name;

    private String description;

    private Location location;
}
