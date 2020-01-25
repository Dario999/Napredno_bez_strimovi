//package Lab7;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

class TermFrequency{

    private TreeMap<String,Integer> mapa;

    public TermFrequency(InputStream inputStream,String[] stopWords){

        mapa = new TreeMap<>();

        Scanner scannerLine = new Scanner(inputStream);

        while (scannerLine.hasNextLine()){
            String line = scannerLine.nextLine();
            StringTokenizer parts = new StringTokenizer(line);

            while (parts.hasMoreTokens()){
                String word = parts.nextToken();
                word = word.toLowerCase();
                if (Character.compare(word.charAt(word.length()-1),'.') == 0 || Character.compare(word.charAt(word.length()-1),',') == 0 )
                    word = word.substring(0,word.length()-1);
                if (!Arrays.asList(stopWords).contains(word) && word.length() != 0){
                    //System.out.println(word);
                    if (mapa.containsKey(word)){
                        Integer temp = mapa.get(word);
                        temp++;
                        mapa.put(word,temp);
                    }else {
                        mapa.put(word,1);
                    }
                }
            }
        }
    }

    public int countTotal(){
        int count = 0;
        for (String word : mapa.keySet()){
            count += mapa.get(word);
        }
        return count;
    }

    public int countDistinct(){
        return mapa.size();
    }

    public List<String> mostOften(int k) {

        TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(new ValuesComparator(mapa));
        sorted.putAll(mapa);
        Iterator<Entry<String, Integer>> it = sorted.entrySet().iterator();
        List<String> mostUsed = new ArrayList<String>();

        while (it.hasNext()) {
            mostUsed.add(it.next().getKey());
            k--;

            if (k == 0) {
                break;
            }
        }

        return mostUsed;

    }

}

class ValuesComparator implements Comparator<String>{

    Map<String,Integer> base;

    public ValuesComparator(Map<String,Integer> base){
        this.base = base;
    }

    @Override
    public int compare(String s, String t1) {
        int temp = base.get(t1)-base.get(s);
        if (temp != 0)
            return temp;
        else
            return s.compareTo(t1);
    }
}

public class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };
        TermFrequency tf = new TermFrequency(System.in,
                stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}
// vasiot kod ovde
