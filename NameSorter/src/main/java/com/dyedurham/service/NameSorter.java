package com.dyedurham.service;

import com.dyedurham.records.Person;
import com.dyedurham.utility.FileUtility;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Command(
        name = "NameSorter",
        description = "The names in the file should be sorted"
)
public class NameSorter implements Runnable {

    @Parameters(index = "0", description = "filePath argument")
    private String filePath;


    /**
     * @param args, takes input from the Command line e.g: unsorted-names.txt
     *              Calls the main method of name sorter i.e, run method.
     */
    public static void main(String[] args) {
        new CommandLine(new NameSorter()).execute(args);
    }

    /**
     * @param filePath, reads the file from the given filePath.
     *                  converts file and build person list.
     */
    public List<Person> buildPersons(String filePath) throws IOException {
        return FileUtility
                .getLines(filePath)
                .stream()
                .map(Person::of)
                .toList();
    }

    /**
     * @param persons, List of Persons.
     *                 Print the person line by line.
     */
    public void print(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    /**
     * @param sortedPersons, List of Persons in sorted by last Names.
     * @param filePath,      given the filePath. e.g: unsorted-names.txt
     *                       write sortedPerson in the filePath (sorted-names.txt)
     */
    public void write(List<Person> sortedPersons, String filePath) throws IOException {
        var personsString = sortedPersons.stream().map(Person::toString).collect(Collectors.toList());
        FileUtility.WriteLines(personsString, filePath.substring(2));
    }

    /**
     * runner method perform 4 operations
     * 1. build the person from the given filePath taken from the argument parameter.
     * 2. sort the persons using lastName
     * 3. print the sorted persons.
     * 4. write the sorted persons in to sorted-names.txt.
     * throws RuntimeException if anything goes wrong.
     */
    @Override
    public void run() {
        try {
            var persons = buildPersons(filePath);

            var sortedPersons = persons.stream()
                    .sorted(Comparator.comparing(Person::lastName).thenComparing(name -> name.givenNames().get(0)))
                    .collect(Collectors.toList());

            print(sortedPersons);

            write(sortedPersons, filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}