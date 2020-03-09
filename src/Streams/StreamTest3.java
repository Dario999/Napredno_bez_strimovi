package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }
}

public class StreamTest3 {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("Max",18),
                new Person("Peter",23),
                new Person("Pamela",23),
                new Person("David",12));

        List<Person> filtered = persons
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .sorted()
                .collect(Collectors.toList());

        System.out.println(filtered);

        Map<Integer,List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age,p) -> System.out.format("age %s: %s\n",age,p));


        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);

        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ","Int germany "," are of legal age."));

        System.out.println(phrase);

        Map<Integer,String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1,name2) -> name1+";"+name2));

        System.out.println(map);


    }
}
