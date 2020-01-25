import java.time.LocalDateTime;
import java.util.*;

class TestClass implements Comparable<TestClass>{

    String name;

    public TestClass(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(TestClass testClass) {
        return 0;
    }
}

public class Test {

    public static void main(String[] args) {


//        TreeMap<TestClass,Integer> mapa = new TreeMap<>();
//        HashMap<String,TestClass> hashMap = new HashMap<>();
//
//        TestClass testClass1 = new TestClass("t1");
//        hashMap.put("t1",testClass1);
//        mapa.put(testClass1,1);
//        TestClass testClass2 = new TestClass("t2");
//        hashMap.put("t2",testClass2);
//        mapa.put(testClass2,2);
//
//        System.out.println(mapa.get(hashMap.get("t2")));


        Set<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return 0;
            }
        });



    }

}
