package Vezbi_Kolok2;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */

class File implements Comparable<File>{

    String name;
    int size;
    LocalDateTime dateTime;

    public File(String name,int size,LocalDateTime dateTime){
        this.name = name;
        this.size = size;
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(File file) {
        if (dateTime.compareTo(file.dateTime) == 0) {
            if (name.compareTo(file.name) == 0) {
                if (size > file.size)
                    return 1;
                else if (size < file.size)
                    return -1;
                else return 0;
            } else {
                return name.compareTo(file.name);
            }
        }else {
            return dateTime.compareTo(file.dateTime);
        }
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s",name,size,dateTime);
    }
}

class FileSystem{

    Map<Character,Set<File>> mapa;

    public FileSystem(){
        mapa = new TreeMap<>();
    }

    public void addFile(char folder,String name,int size,LocalDateTime createdAt){
        File file = new File(name,size,createdAt);
        if (!mapa.containsKey(folder)) {
            mapa.put(folder, new TreeSet<>());
            mapa.get(folder).add(file);
        }
        else
            mapa.get(folder).add(file);
    }

    public List<File> findAllHiddenFilesWithSizeLessThen(int size){
        List<File> list = new ArrayList<>();
        for (Character c : mapa.keySet()){
            Set<File> set = mapa.get(c);
            for (File file : set){
                if (file.name.startsWith(".") && file.size < size)
                    list.add(file);
            }
        }
        return list;
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders){
        int sum = 0;
        for (Character c : folders){
            Set<File> set = mapa.get(c);
            for (File file : set){
                sum += file.size;
            }
        }
        return sum;
    }

    public Map<Integer,Set<File>> byYear(){
        Map<Integer,Set<File>> mapToReturn = new TreeMap<>();
        for (Character c : mapa.keySet()){
            Set<File> set = mapa.get(c);
            for (File file : set){
                if (!mapToReturn.containsKey(file.dateTime.getYear())){
                    mapToReturn.put(file.dateTime.getYear(),new TreeSet<>());
                    mapToReturn.get(file.dateTime.getYear()).add(file);
                }else {
                    mapToReturn.get(file.dateTime.getYear()).add(file);
                }
            }
        }
        return mapToReturn;
    }

    //APRIL-10 -> 2789
    public Map<String,Long> sizeByMonthAndDay(){
        Map<String,Long> mapToReturn = new TreeMap<>();
        for (Character c : mapa.keySet()){
            Set<File> set = mapa.get(c);
            for (File file : set){
                String string = file.dateTime.getMonth() + "-" + file.dateTime.getDayOfMonth();
                if (!mapToReturn.containsKey(string)){
                    mapToReturn.put(string,Long.valueOf(file.size));
                }else {
                    Long temp1 = mapToReturn.get(string);
                    Long temp2 = Long.valueOf(file.size);
                    Long tempLong = Long.sum(temp1,temp2);
                    mapToReturn.put(string,tempLong);
                }
            }
        }
        return mapToReturn;
    }

}

public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}

// Your code here

