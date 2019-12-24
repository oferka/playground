package org.biri.data.model;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {

    private List<ScheduledEvent> scheduledEvents;

    private List<ScheduledAssignment> scheduledAssignments;

}