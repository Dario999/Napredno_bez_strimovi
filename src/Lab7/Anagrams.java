//package Lab7;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Anagrams {

    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        TreeMap<String,TreeSet<String>> words = new TreeMap<>();

        while (scanner.hasNextLine()){
            String word = scanner.nextLine();

            boolean check = false;
            for (String key : words.keySet()){
                char[] first = key.toCharArray();
                char[] second = word.toCharArray();
                Arrays.sort(first);
                Arrays.sort(second);
                check = Arrays.equals(first,second);
                if(check){
                    words.get(key).add(word);
                    break;
                }
            }

            if(!check){
                TreeSet<String> value = new TreeSet<>();
                value.add(word);
                words.put(word,value);
            }

        }
        scanner.close();

        StringBuilder sb = new StringBuilder();
        for (String key : words.keySet()){
            if(words.get(key).size() >= 5){
                sb.append(String.join(" ",words.get(key)));
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
