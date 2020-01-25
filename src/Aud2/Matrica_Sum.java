package Aud2;

import java.util.Arrays;

public class Matrica_Sum {

    public static double sumStream(double[][] a){
        return Arrays.stream(a)
                .mapToDouble(row -> Arrays.stream(row).sum())
                .sum();
    }

}
