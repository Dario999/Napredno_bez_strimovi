package Aud5;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class OldestPerson {

    public static void main(String[] args) {

        try {
            findWithScanner(new FileInputStream("D:\\Napredno_zadaci\\src\\Aud5\\persons.txt"));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        try{
            findWithBufferedReader("D:\\Napredno_zadaci\\src\\Aud5\\persons.txt");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    //solution with Scanner
    public static void findWithScanner(InputStream input) {
        try (Scanner scanner = new Scanner(input)) {
            String oldestPerson = null;
            int maxAge = Integer.MIN_VALUE;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                if (age > maxAge) {
                    oldestPerson = name;
                    maxAge = age;
                }
            }
            System.out.println("Name: " + oldestPerson);
            System.out.println("Age: " + maxAge);
        }
    }

    //solution with BufferedReader
    public static void findWithBufferedReader(String input) {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String oldestPerson = null;
            int maxAge = Integer.MIN_VALUE;
            String line = null;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String name = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                if (age > maxAge) {
                    oldestPerson = name;
                    maxAge = age;
                }
            }
            System.out.println("Name: " + oldestPerson);
            System.out.println("Age: " + maxAge);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}