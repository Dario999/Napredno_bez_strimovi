package Streams;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest2 {
    public static void main(String[] args) {

        List<String> myList = Arrays.asList("a1","a2","b1","c2","c1");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        Stream.of("a1","a2","a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(x -> System.out.println(x));

        IntStream.range(1,4)
                .mapToObj(x -> "a" + x)
                .forEach(x -> System.out.println(x));

        Stream.of(1.0,2.0,3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        //Processing Order
        Stream.of("d2","a2","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        Stream.of("d2","a2","b1","b3","c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        Stream.of("d2","a2","b1","b3","c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1,s2) -> {
                    System.out.printf("sort: %s; %s\n",s1,s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //Reusing streams

        Stream<String> stream = Stream.of("d2","a2","b1","b3","c")
                .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);


    }
}
