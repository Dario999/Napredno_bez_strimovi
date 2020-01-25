package PrvKolok;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class OverLimitException extends Exception {

    private int amount;

    public OverLimitException(int amount){
        this.amount = amount;
        System.out.printf("Over limit amount: %d\n",this.amount);
    }

    @Override
    public String getMessage() {
        return String.format("Over limit amount: %d\n",this.amount);
    }
}

class Fiskalna {

    private int id;
    private int sum;
    private float ddv;

    public Fiskalna(int id,int sum,float ddv){
        this.id = id;
        this.sum = sum;
        this.ddv = ddv;
    }

    @Override
    public String toString() {
        return String.format("%d\t%d\t%f\n",id,sum,ddv);
    }
}

class MojDDV {

    private List<Fiskalna> listaFiskalni;


    public void readLines(InputStream inputStream){
        listaFiskalni = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()){
            try{
                StringTokenizer st = new StringTokenizer(scanner.nextLine());
                if(!st.hasMoreTokens()){
                    break;
                }
                int id = Integer.parseInt(st.nextToken());
                int sum = 0;
                float ddv = 0;
                while (st.hasMoreTokens()){
                    int cena = Integer.parseInt(st.nextToken());
                    sum += cena;
                    char c = st.nextToken().charAt(0);
                    if(c == 'A'){
                        ddv += cena * 0.18;
                    }else if(c == 'B'){
                        ddv += cena * 0.15;
                    }else {
                        ddv += 0;
                    }
                }
                if(sum > 30000){
                    throw new OverLimitException(sum);
                }
                listaFiskalni.add(new Fiskalna(id,sum,ddv));
            }catch (OverLimitException e){
                e.getMessage();
            }
        }
    }

    public void printLines(OutputStream outputStream) throws IOException {

        StringBuilder sb = new StringBuilder();
        for(Fiskalna temp : listaFiskalni){
            sb.append(temp.toString());
        }

        outputStream.write(sb.toString().getBytes());

    }

}

public class MojDDVTest {

    public static void main(String[] args) throws IOException {

        MojDDV mojDDV = new MojDDV();

        System.out.println("=====READING LINES=====");
        mojDDV.readLines(System.in);
        System.out.println("=====PRINTING LINES=====");
        mojDDV.printLines(System.out);

    }

}
