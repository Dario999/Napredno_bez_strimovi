package Vezbi_Kolok2;

import java.util.*;

class Candidate implements Comparable<Candidate>{

    String code;
    String name;
    int age;

    public Candidate(String code,String name,int age){
        this.code = code;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Candidate candidate) {
        return code.compareTo(candidate.code);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d\n",code,name,age);
    }
}

class Audition {

    Map<String, Set<Candidate>> mapa;

    public Audition(){
        mapa = new TreeMap<>();
    }

    public void addParticpant(String city,String code,String name,int age){
        Candidate candidate = new Candidate(code,name,age);
        if (!mapa.containsKey(city)){
            mapa.put(city,new TreeSet<>());
            mapa.get(city).add(candidate);
        }else {
            mapa.get(city).add(candidate);
        }
    }

    public void listByCity(String city){
        Set<Candidate> set = new TreeSet<>(new Comparator<Candidate>() {
            @Override
            public int compare(Candidate candidate, Candidate t1) {
                if (candidate.name.compareTo(t1.name) == 0){
                    if (candidate.age > t1.age)
                        return 1;
                    else if (candidate.age < t1.age)
                        return -1;
                    else{
                        return candidate.code.compareTo(t1.code);
                    }
                }else
                    return candidate.name.compareTo(t1.name);
            }
        });
        for (Candidate candidate : mapa.get(city)){
            set.add(candidate);
        }
        for (Candidate candidate : set)
            System.out.print(candidate);
    }

}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}