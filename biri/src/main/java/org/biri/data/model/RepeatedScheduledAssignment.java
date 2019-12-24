package org.biri.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepeatedScheduledAssignment extends  ScheduledAssignment {

    private AssignmentRepeatPattern repeatPattern;
}
