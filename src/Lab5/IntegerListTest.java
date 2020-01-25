//package Lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class IntegerList{

   private List<Integer> lista;

   public IntegerList(){
       this.lista = new ArrayList<>();
   }

   public IntegerList(Integer... numbers){
       lista = new ArrayList<>(Arrays.asList(numbers));
   }

   public void add(int el,int idx){
       if(idx > lista.size()){
           int length = idx - lista.size();
           for (int i = 0; i < length; i++) {
               lista.add(0);
           }
           lista.add(el);
       }else {
           this.lista.add(idx,el);
       }
   }

   public int remove(int idx){
       if (idx >= lista.size() || idx < 0)
           throw new ArrayIndexOutOfBoundsException();
       return this.lista.remove(idx);
   }

   public void set(int el,int idx){
       if (idx >= lista.size() || idx < 0)
           throw new ArrayIndexOutOfBoundsException();
       this.lista.set(idx,el);
   }

   public int get(int idx){
       if (idx >= lista.size() || idx < 0)
           throw new ArrayIndexOutOfBoundsException();
       return this.lista.get(idx);
   }

   public int size(){
       return this.lista.size();
   }

   public int count(int el){
       int counter = 0;
       for (Integer temp :
               lista) {
           if (temp.equals(el)) {
                counter++;
           }
       }
       return counter;
   }

   public void removeDuplicates(){
       for (int i = lista.size() - 1 ; i > 0 ; i--) {
           for (int j = i - 1; j >= 0; j--){
               if(lista.get(i).equals(lista.get(j))){
                   lista.remove(j);
                   i--;
                   //j--;
               }
           }
       }
   }

   public int sumFirst(int k){
       int sum = 0;
       for (int i = 0; i < lista.size(); i++) {
           if(k > 0){
               sum += lista.get(i);
           }
           k -= 1;
           if (k == 0)
               break;
       }
       return sum;
   }

   public int sumLast(int k){
       int sum = 0;
       for (int i = lista.size(); i <= 0; i++) {
           if (k > 0){
               sum += lista.get(i);
           }
           k -= 1;
           if (k == 0)
               break;
       }
       return sum;
   }

   public void shiftRight(int idx,int k){
       if (idx >= lista.size() || idx < 0)
           throw new ArrayIndexOutOfBoundsException();
       Integer temp = lista.get(idx);
       int indexToInsert = idx + k;
       while (indexToInsert >= lista.size()){
           indexToInsert -= lista.size();
       }
       lista.add(indexToInsert,temp);
       lista.remove(idx + 1);
   }

   public void shiftLeft(int idx,int k){
       if (idx >= lista.size() || idx < 0)
           throw new ArrayIndexOutOfBoundsException();
       Integer temp = lista.get(idx);
       int indexToInsert = idx - k;
       while (indexToInsert < 0){
           indexToInsert += lista.size();
       }
       lista.add(indexToInsert,temp);
       lista.remove(idx + 1);
   }

   public IntegerList addValue(int value){
       IntegerList tempList = new IntegerList();
       for (int i = 0; i < lista.size(); i++) {
           tempList.add(lista.get(i) + value,i);
       }
       return tempList;
   }

}

public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}
