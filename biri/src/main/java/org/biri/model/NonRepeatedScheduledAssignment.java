package org.biri.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=true)
public class NonRepeatedScheduledAssignment extends ScheduledAssignment {

    private LocalDateTime dueDate;
}
