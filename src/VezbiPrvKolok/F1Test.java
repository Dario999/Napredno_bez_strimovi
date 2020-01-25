package VezbiPrvKolok;

import java.io.InputStream;
import java.io.OutputStream;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }

}

class Racer {

    private String name;
    private int mm;
    private int ss;
    private int nnn;

    public Racer(String name,int mm,int ss,int nnn){
        this.name = name;
        this.mm = mm;
        this.ss = ss;
        this.nnn = nnn;
    }

    @Override
    public String toString() {
        return String.format("%s\t",name);
    }
}

class F1Race {
    // vashiot kod ovde

    public F1Race(){}

    public void readResults(InputStream inputStream){

    }

    public void printSorted(OutputStream outputStream){

    }

}