package com.example;

import com.example.model.Person;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EasyRandomTests {

    @Test
    void givenDefaultConfiguration_thenGenerateSingleObject() {
        EasyRandom generator = new EasyRandom();
        Person person = generator.nextObject(Person.class);
        assertNotNull(person.getAge());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
    }

    @Test
    void givenDefaultConfiguration_thenGenerateObjectsList() {
        EasyRandom generator = new EasyRandom();
        List<Person> persons = generator
                .objects(Person.class, 5)
                .collect(Collectors.toList());
        assertEquals(5, persons.size());
    }

//    @Test
//    void givenCustomConfiguration_thenGenerateSingleEmployee() {
//        EasyRandomParameters parameters = new EasyRandomParameters();
//        parameters.stringLengthRange(3, 3);
//        parameters.collectionSizeRange(5, 5);
//        parameters.excludeField(FieldPredicates.named("lastName").and(FieldPredicates.inClass(Employee.class)));
//        parameters.excludeType(TypePredicates.inPackage("not.existing.pkg"));
//        parameters.randomize(YearQuarter.class, new YearQuarterRandomizer());
//
//        EasyRandom generator = new EasyRandom(parameters);
//        Employee employee = generator.nextObject(Employee.class);
//
//        assertEquals(3, employee.getFirstName().length());
//        assertEquals(5, employee.getCoworkers().size());
//        assertEquals(5, employee.getQuarterGrades().size());
//        assertNotNull(employee.getDepartment());
//
//        assertNull(employee.getLastName());
//
//        for (YearQuarter key : employee.getQuarterGrades().keySet()) {
//            assertEquals(key.getStartDate(), key.getEndDate().minusMonths(3L));
//        }
//    }
}
