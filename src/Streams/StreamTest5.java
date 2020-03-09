package Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamTest5 {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Max",18),
                new Person("Peter",23),
                new Person("Pamela",23),
                new Person("David",12));

        persons
                .stream()
                .reduce((p1,p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);

        persons
                .stream()
                .max(Comparator.comparing(Person::getName).thenComparing(Person::getAge))
                .ifPresent(System.out::println);

        Person result = persons
                .stream()
                .reduce(new Person("",0), (p1,p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });

        System.out.printf("name=%s; age=%s\n",result.name,result.age);

        Integer ageSum = persons
                .stream()
                .reduce(0,(sum,p) -> sum += p.age,(sum1,sum2) -> sum1+sum2);

        System.out.println(ageSum);

        Integer ageSum1 = persons
                .stream()
                .reduce(0,(sum,p) -> {
                    System.out.printf("accumulator: sum=%s; person:%s\n",sum,p);
                    return sum += p.age;
                },(sum1,sum2) -> {
                    System.out.printf("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                    return sum1+sum2;
                });
    }
}
