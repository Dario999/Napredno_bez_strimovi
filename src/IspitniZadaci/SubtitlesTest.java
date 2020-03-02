package IspitniZadaci;

import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Subtitle {

    private int number;
    private Time start;
    private Time end;
    private String text;

    public Subtitle(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        number = Integer.parseInt(scanner.next());
        String[] line = scanner.nextLine().split(" --> ");
        start = Time.valueOf(line[0]);
        end = Time.valueOf(line[1]);
        text = scanner.next();
    }

    @Override
    public String toString() {
        return String.format("%d\n%s --> %s\n%s\n",number,start,end,text);
    }
}

class Subtitles {

    private List<Subtitle> lista;

    public Subtitles(){
        lista = new ArrayList<>();
    }

    public int loadSubtitles(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);

        return 0;
    }

    public void print(){

    }

    public void shift(int ms){

    }

}

public class SubtitlesTest {
    public static void main(String[] args) {
        Subtitles subtitles = new Subtitles();
        int n = subtitles.loadSubtitles(System.in);
        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
        subtitles.print();
        int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
        System.out.println(String.format("SHIFT FOR %d ms", shift));
        subtitles.shift(shift);
        System.out.println("+++++ SHIFTED SUBTITLES +++++");
        subtitles.print();
    }
}

// Вашиот код овде
