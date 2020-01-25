package Aud5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {

    public static void main(String[] args) {

        StringBuilder result = new StringBuilder();
        /*if(args.length == 0){
            System.out.println("Provide filename as argument");
            return;
        }*/
        //for (String fileName : args){
            try{
                String wordCount = processFile("D:\\Napredno_zadaci\\src\\Aud5\\test.txt");
                String fileName = "test.txt";
                result.append(String.format("%s - > %s\n",fileName,wordCount));
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        //}
        System.out.println(result.toString());
    }

    // Solution using BufferedReader reading line by line

    private static String processFile(String fileName) throws IOException{
        int linesCount = 0;
        int wordsCount = 0;
        int charactersCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null){
                linesCount++;
                String[] words = line.split("\\s+");
                wordsCount += words.length;
                charactersCount += line.length() + 1;
            }
        }
        return String.format("%d %d %d" , linesCount , wordsCount, charactersCount);
    }



}
