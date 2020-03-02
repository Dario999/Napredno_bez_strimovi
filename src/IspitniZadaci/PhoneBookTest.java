//package IspitniZadaci;

import javax.naming.Name;
import java.util.*;

class DuplicateNumberException extends Exception{

}

class NameNumber{

    private String name;
    private String number;

    public NameNumber(String name,String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}

class PhoneBook {

    private TreeMap<String, HashSet<NameNumber>> mapa;

    public PhoneBook(){
        mapa = new TreeMap<>();
    }

    public void addContact(String name,String number) throws DuplicateNumberException{
        HashSet<NameNumber> set = mapa.computeIfAbsent(name, k -> new HashSet<NameNumber>());
        set.add(new NameNumber(name,number));
    }

    public void contactsByNumber(String number){
        StringBuilder sb = new StringBuilder();
        mapa.forEach((key,value) -> {
            value.stream()
                    .filter( set -> set.getNumber().contains(number))
                    .forEach(x -> sb.append(String.format("%s %s\n",x.getName(),x.getNumber())));
        });

        if (!sb.toString().isEmpty()){
            System.out.print(sb.toString());
        }else{
            System.out.println("NOT FOUND");
        }
    }

    public void contactsByName(String name){
        HashSet<NameNumber> set = mapa.get(name);

        if(set == null){
            System.out.println("NOT FOUND");
        }else{
            set.stream()
                    .sorted(Comparator.comparing(NameNumber::getNumber))
                    .forEach(x -> System.out.println(x.getName() + " " + x.getNumber()));
        }
    }

}

public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}

// Вашиот код овде

