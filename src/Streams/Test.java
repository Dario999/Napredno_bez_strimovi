package Streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Comment {

    private String name;
    private Integer numPosts;

    public Comment(String name,Integer numPosts){
        this.name = name;
        this.numPosts = numPosts;
    }

    public Integer getNumPosts() {
        return numPosts;
    }

    public String getName() {
        return name;
    }
}

public class Test {

    public static void function(int number){
        if (number % 5 == 0 && number % 3 == 0)
            System.out.println("Double");
        else if(number % 5 == 0)
            System.out.println("With 5");
        else if(number % 3 == 0)
            System.out.println("With 3");
        else
            System.out.println("Nope");
    }

    public static void main(String[] args) {

        IntStream.range(0,5)
                .forEach(x -> function(x));

        List<Integer> list = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 5);

//        Integer sum =  list.stream()
//                .filter(x -> (list.stream().filter(y -> x == y).count() == 1))
//                .mapToInt(Integer::intValue)
//                .sum();

        Integer sum =  list.stream()
                .filter(x -> (list.stream().filter(y -> x == y).count() == 1))
                .collect(Collectors.summingInt(x -> x));

        System.out.println(sum);

        List<Comment> listPosts = Arrays.asList(new Comment("post1",2),
                            new Comment("post2",4),new Comment("post1",3));

        Map<String,Integer> mapa = new HashMap<>();

        mapa = listPosts.stream()
                .collect(Collectors.toMap(Comment::getName,Comment::getNumPosts
                                        ,(oldValue,newValue) -> oldValue+newValue));

        for (String key : mapa.keySet()){
            System.out.println(key + " " + mapa.get(key));
        }


    }

}
