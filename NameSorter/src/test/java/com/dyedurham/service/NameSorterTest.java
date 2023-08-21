package com.dyedurham.service;

import com.dyedurham.records.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NameSorterTest {

    private NameSorter nameSorter;

    @BeforeEach
    void setUp() {
        nameSorter = new NameSorter();
    }

    @Test
    void testBuildPersons() throws IOException {
        List<Person> exceptedPersons = new ArrayList<>();
        exceptedPersons.add(Person.of("Janet Parsons"));
        exceptedPersons.add(Person.of("Vaughn Lewis"));
        exceptedPersons.add(Person.of("Adonis Julius Archer"));
        exceptedPersons.add(Person.of("Shelby Nathan Yoder"));
        exceptedPersons.add(Person.of("Marin Alvarez"));
        exceptedPersons.add(Person.of("London Lindsey"));
        exceptedPersons.add(Person.of("Beau Tristan Bentley"));
        exceptedPersons.add(Person.of("Leo Gardner"));
        exceptedPersons.add(Person.of("Hunter Uriah Mathew Clarke"));
        exceptedPersons.add(Person.of("Mikayla Lopez"));
        exceptedPersons.add(Person.of("Frankie Conner Ritter"));

        List<Person> actualPersons = nameSorter.buildPersons("unsorted-names-test.txt");
        assertEquals(actualPersons, exceptedPersons);
    }

    @Test
    void testPrint() {
        List<Person> persons = new ArrayList<>();
        persons.add(Person.of("Janet Parsons"));
        persons.add(Person.of("Vaughn Lewis"));

        // Redirect System.out to a mock output stream
        PrintStream originalOut = System.out;
        ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOut));

        nameSorter.print(persons);

        // Verify output content using assertions on mockOut.toString()
        // Restore original System.out
        System.setOut(originalOut);
    }

    @Test
    void testWrite() throws IOException {
        List<Person> exceptedSortedPersons = new ArrayList<>();
        exceptedSortedPersons.add(Person.of("Vaughn Lewis"));
        exceptedSortedPersons.add(Person.of("Smith, Jane"));

        nameSorter.write(exceptedSortedPersons, "unsorted-names-test.txt");

        List<Person> actualPersons = nameSorter.buildPersons("sorted-names-test.txt");
        assertEquals(actualPersons, exceptedSortedPersons);
    }

}
