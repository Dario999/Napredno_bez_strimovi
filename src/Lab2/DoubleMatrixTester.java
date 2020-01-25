package Lab2;


import javax.swing.plaf.DesktopPaneUI;
import javax.swing.plaf.IconUIResource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.DoubleBuffer;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

class InsufficientElementsException extends Exception {

    public InsufficientElementsException(){
    }

    public String getMessage(){
        return "Insufficient number of elements";
    }

}

class InvalidRowNumberException extends Exception{

    public InvalidRowNumberException(){
    }

    public String getMessage(){
        return "Invalid row number";
    }

}

class InvalidColumnNumberException  extends Exception{

    public InvalidColumnNumberException (){
    }

    public String getMessage(){
        return "Invalid column number";
    }

}

final class DoubleMatrix{

    private final double matrix[][];
    private int m,n;

    public DoubleMatrix(double a[],int m,int n) throws InsufficientElementsException{
        if(m * n > a.length+1){
            throw new InsufficientElementsException();
        }
        this.m = m;
        this.n = n;
        int counter = 0;
        if(m*n < a.length + 1){
            counter = (a.length) - (m*n);
        }
        this.matrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = a[counter];
                counter++;
            }
        }
    }

    public String getDimensions(){
        StringBuilder st = new StringBuilder();
        st.append("[");
        st.append(this.m);
        st.append(" x ");
        st.append(this.n + "]");
        return st.toString();
    }

    public int rows(){
        return m;
    }

    public int columns(){
        return n;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if(row-1 < 0 || row > m){
            throw new InvalidRowNumberException();
        }else {
            double max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if (matrix[row - 1][i] > max) {
                    max = matrix[row - 1][i];
                }
            }
            return max;
        }
    }

    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if(column-1 < 0 || column > n){
            throw new InvalidColumnNumberException();
        }else {
            double max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                if (matrix[i][column - 1] > max) {
                    max = matrix[i][column - 1];
                }
            }
            return max;
        }
    }

    public double sum(){
        double sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public double[] toSortedArray(){
        int numElem = m*n;
        double niza[] = new double[numElem];
        int counter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                niza[counter] = matrix[i][j];
                counter++;
            }
        }
        for (int i = 0; i < numElem; i++) {
            niza[i] *= -1;
        }
        Arrays.sort(niza);
        for (int i = 0; i < numElem; i++) {
            niza[i] *= -1;
        }
        return niza;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j != n-1){
                    st.append(String.format("%.2f" ,matrix[i][j]) + "\t");
                }else{
                    st.append(String.format("%.2f" ,matrix[i][j]));
                }
            }
            if(i != m-1){
                st.append("\n");
            }
        }
        return st.toString();
    }

    public double[][] getMatrix(){
        return matrix;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        DoubleMatrix temp = (DoubleMatrix)obj;
        double[][] matrix1 = temp.getMatrix();
        if(!this.getDimensions().equals(temp.getDimensions())){
            return false;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] != matrix1[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(matrix);
        return result;
    }
}

class MatrixReader{

    public static DoubleMatrix read(InputStream input) throws InsufficientElementsException {
        Scanner scanner = new Scanner(input);
        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        double niza[] = new double[m*n];

        int counter = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(scanner.nextLine());
            for (int j = 0; j < n; j++) {
                niza[counter] = Double.parseDouble(st.nextToken());
                counter++;
            }
        }
        return new DoubleMatrix(niza,m,n);
    }

}

public class DoubleMatrixTester {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }

            }

        }

        scanner.close();
    }
}
