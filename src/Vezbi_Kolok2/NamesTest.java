package Vezbi_Kolok2;

import java.util.*;


class Names {

    private Map<String,Integer> mapa;

    public Names(){
        mapa = new TreeMap<>();
    }

    public void addName(String name){
        if (!mapa.containsKey(name))
            mapa.put(name,1);
        else{
            int temp = mapa.get(name) + 1;
            mapa.put(name,temp);
        }
    }

    public void printN(int n){
        for (String name : mapa.keySet()){
            if (mapa.get(name) >= n) {
                int uniqueChars = 0;
                Set<Character> set = new TreeSet<>();
                for (int i=0;i<name.length();i++){
                    set.add(Character.toLowerCase(name.charAt(i)));
                }
                System.out.printf("%s (%d) %d\n", name, mapa.get(name), set.size());
            }
        }
    }

    public String findName(int len,int x){
        List<String> lista = new ArrayList<>();
        for (String temp : mapa.keySet()){
            if (temp.length() < len)
                lista.add(temp);
        }
        return lista.get(x % lista.size());
    }

}

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}

// vashiot kod ovde


