package Streams;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StreamTest1 {

    public static void main(String[] args) {

        int[] numbers = {10,22,3,45,1};


        IntStream
                .of(numbers)
                .distinct()
                .sorted()
                .limit(3)
                .forEach(x -> System.out.println(x));

    }

}
