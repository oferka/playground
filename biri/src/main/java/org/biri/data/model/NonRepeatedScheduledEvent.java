package org.biri.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=true)
public class NonRepeatedScheduledEvent extends ScheduledEvent {

    private LocalDateTime start;

    private LocalDateTime end;
}
