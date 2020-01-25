package Lab1;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}


class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {

        StringBuilder sb = new StringBuilder();
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] arabic = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int tmp;
        for (int i = 0; i < arabic.length; i++) {
            if(n >= arabic[i]){
                tmp = n/arabic[i];
                n -= tmp * arabic[i];
                while (tmp > 0){
                    sb.append(romans[i]);
                    tmp--;
                }
            }
        }
        return sb.toString();

    }

}
