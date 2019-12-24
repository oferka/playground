package org.biri.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepeatedScheduledEvent extends ScheduledEvent {

    private EventRepeatPattern repeatPattern;
}
