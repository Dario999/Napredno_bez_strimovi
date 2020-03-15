package Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest1 {

    public static void main(String[] args) throws IOException {

        int[] numbers = {10,22,3,45,1,22};


        IntStream
                .of(numbers)
                .distinct()
                .forEach(x -> System.out.println(x));




    }

}
