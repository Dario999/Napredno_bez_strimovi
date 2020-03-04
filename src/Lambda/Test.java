package Lambda;

import java.util.ArrayList;
import java.util.HashMap;

interface FuncInterface {

    void abstractFun(int x);

    default void normalFun(){
        System.out.println("Hello");
    }

}

public class Test{

    interface FuncInter1 {
        int operation(int a,int b);
    }

    interface FuncInter2 {
        void sayMessage(String message);
    }

    private int operate(int a,int b,FuncInter1 fobj){
        return fobj.operation(a,b);
    }

    public static void main(String[] args) {

        FuncInterface fobj = (int x) -> System.out.println(2*x);

        fobj.abstractFun(5);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        arrayList.forEach(n -> System.out.println(n));

        arrayList.forEach(x -> {
            if (x % 2 == 0)
                System.out.println(x);
        });

        HashMap<Integer,String> mapa = new HashMap<>();
        mapa.put(1,"abc");
        mapa.put(2,"ab");
        mapa.put(3,"cd");

        mapa.forEach((x,y) -> {
            if (y.contains("ab"))
                System.out.println("Key: " + x + " Value: " + y);
        });

        FuncInter1 add = (int x,int y) -> x + y;

        FuncInter1 multiply = (int x,int y) -> x * y;

        Test test = new Test();

        System.out.println("Addition is " + test.operate(6,3,add));

        System.out.println("Multiplication is " + test.operate(6,3,multiply));

        FuncInter2 fobj1 = message -> {
            System.out.println("Hello " + message);
        };

        fobj1.sayMessage("Geek");


    }

}
