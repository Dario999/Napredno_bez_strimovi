package Lab6;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class SuperString{

    private LinkedList<String> lista;
    private Stack<String> lastAdded;

    public SuperString(){
        lista = new LinkedList<>();
        lastAdded = new Stack<>();
    }

    public void append(String s){
        lista.add(s);
        lastAdded.push(s);
    }

    public void insert(String s){
        lista.add(0,s);
        lastAdded.push(s);
    }

    public boolean contains(String s){
        String temp = this.toString();
        return temp.contains(s);
    }

    public void reverse(){
        Iterator<String> iterator = lista.descendingIterator();
        LinkedList<String> newStrings = new LinkedList<>();
        while (iterator.hasNext()){
            String toReverse = iterator.next();
            toReverse = reverseString(toReverse);
            newStrings.add(toReverse);
        }
        this.lista = newStrings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String temp : lista){
            sb.append(temp);
        }
        return sb.toString();
    }

    public String reverseString(String string){
        StringBuffer sb = new StringBuffer(string);
        return sb.reverse().toString();
    }

    public void removeLast(int k){
        while (k != 0){
            String current = lastAdded.pop();
            String currentReversed = reverseString(current);
            lista.remove(currentReversed);
            lista.remove(current);
            k--;
        }
    }
}

public class SuperStringTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }

}
