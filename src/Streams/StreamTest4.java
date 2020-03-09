package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Foo{
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo: " + name + " " + bars + "\n";
    }
}

class Bar {
    String name;

    Bar(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class StreamTest4 {
    public static void main(String[] args) {

        List<Foo> foos = new ArrayList<>();

        IntStream
                .range(1,4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        foos.stream().forEach(f ->
                IntStream.range(1,4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }
}
