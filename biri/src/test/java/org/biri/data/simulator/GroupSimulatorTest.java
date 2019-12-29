package org.biri.data.simulator;

import lombok.extern.slf4j.Slf4j;
import org.biri.data.model.Group;
import org.junit.Test;

import java.util.List;

import static java.lang.String.format;

@Slf4j
public class GroupSimulatorTest {

    @Test
    public void shouldGenerateGroup() {
        GroupSimulator groupSimulator = new GroupSimulator();
        Group group = groupSimulator.generateGroup();
        validateGroup(group);
    }

    @Test
    public void shouldGenerateGroups() {
        GroupSimulator groupSimulator = new GroupSimulator();
        int size = 10;
        List<Group> groups = groupSimulator.generateGroups(size);
        assert groups.size() == size;
        for(Group group : groups) {
            validateGroup(group);
        }
    }

    private void validateGroup(Group group) {
        log.info(format("Generated group: %s", group));
        assert group != null;
    }
}
