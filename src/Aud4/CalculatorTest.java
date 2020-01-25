package Aud4;

import java.beans.ExceptionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class UnknownOperatonException extends Exception {

    public UnknownOperatonException(String operator){
        System.out.println(operator + " is an uknown operation.");
    }

}

class Calculator{

    private double broj;

    public Calculator(){
        System.out.println("Calculator is on");
        System.out.println("rezult = " + broj);
        this.broj = 0.0;
    }

    public double getBroj(){
        return broj;
    }

    @Override
    public String toString() {
        return String.format("result = %d" , broj);
    }

    public void calculate(String operator,double operand) throws UnknownOperatonException{
        if(operator.equals("+") || operator.equals("-") || operator.equals("/") || operator.equals("*")){
            double result = this.broj;
            switch (operator){
                case "+":
                    result = result + operand;
                    break;
                case "-":
                    result = result - operand;
                    break;
                case "/":
                    result = result / operand;
                    break;
                case "*":
                    result = result * operand;
                    break;
            }
            System.out.println("result " + operator + " " + operand + " = " + result);
            System.out.println("new result = " + result);
            this.broj = result;
        }else {
            throw new UnknownOperatonException(operator);
        }
    }
}

public class CalculatorTest {

    public static void main(String[] args) throws IOException {

        Calculator calculator = new Calculator();

        while (true){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            String znak = st.nextToken();
            if(znak.equals("r")){
                System.out.println("Final result = " + calculator.getBroj());
                System.out.println("Again? (y/n)");
                String line = br.readLine();
                if (line.equals("y")){
                    calculator = new Calculator();
                }else {
                    System.out.println("End of Program");
                    break;
                }
            }else {
                if (st.hasMoreTokens()) {
                    double broj = Double.parseDouble(st.nextToken());
                    try {
                        calculator.calculate(znak, broj);
                    } catch (UnknownOperatonException e) {
                        System.out.println("Reenter, your last line:");
                    }
                }
            }

        }

    }

}
