package org.biri.data.simulator;

import org.biri.data.model.Group;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class GroupSimulator {

    public Group generateGroup() {
        return generateRandomGroup();
    }

    public List<Group> generateGroups(int size) {
        List<Group> groups = new ArrayList<>(size);
        for(int i=0; i<size; i++) {
            groups.add(generateGroup());
        }
        return groups;
    }

    private Group generateRandomGroup() {
        int random = nextInt(1, 1000000);
        String name = generateRandomGroupName(random);
        String description = generateRandomGroupDescription(random);
        return Group.builder()
                .name(name)
                .description(description)
                .build();
    }

    private String generateRandomGroupName(int random) {
        return format("Group %s name", random);
    }

    private String generateRandomGroupDescription(int random) {
        return format("Group %s description", random);
    }
}
