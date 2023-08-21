package com.dyedurham.records;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a simple record that represents a person.
 * It stores information about the person's name and list of given names.
 */
public record Person(String lastName, List<String> givenNames) {

    /**
     * @param name, name of the person.
     * of method converts string name into Person record.
     */
    public static Person of(String name) {
        List<String> names = Arrays.stream(name.split(" ")).collect(Collectors.toList());
        String lastName = names.remove(names.size() - 1);
        return new Person(lastName, names);
    }

    /**
     * toString converts person into given names joined by " " & last name.
     */
    public String toString() {
        return String.join(" ", givenNames) + " " + lastName;
    }
}
