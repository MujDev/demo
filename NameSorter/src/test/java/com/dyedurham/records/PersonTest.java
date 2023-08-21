package com.dyedurham.records;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PersonTest {

    @Test
    void testOf() {
        String name = "John Doe";
        Person person = Person.of(name);

        assertEquals("Doe", person.lastName());
        assertEquals(List.of("John"), person.givenNames());
    }

    @Test
    void testToString() {
        String lastName = "Doe";
        List<String> givenNames = List.of("John", "James");
        Person person = new Person(lastName, givenNames);

        String expectedToString = "John James Doe";
        assertEquals(expectedToString, person.toString());
    }
}
