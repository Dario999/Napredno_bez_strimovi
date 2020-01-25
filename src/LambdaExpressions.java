public class LambdaExpressions {

    interface MathOperation{
        int operation(int a,int b);
    }

    interface GreetingService{
        void sayMessage(String message);
    }

    private int operate(int a,int b,MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }

    public static void main(String[] args) {

        LambdaExpressions tester = new LambdaExpressions();
        //with type declaration
        MathOperation addition = (int a,int b) -> a + b;
        //without type declaration
        MathOperation substraction = (a,b) -> a - b;
        //with return statement along with curly braces
        MathOperation multiplication = (int a,int b) -> { return a * b; };
        //without return statement and without curly braces
        MathOperation division = (int a,int b) -> a/b;

        System.out.println("10 + 5 = " + tester.operate(10,5,addition));
        System.out.println("10 - 5 = " + tester.operate(10,5,substraction));
        System.out.println("10 x 5 = " + tester.operate(10,5,multiplication));
        System.out.println("10 / 5 = " + tester.operate(10,5,division));

        //with parenthesis
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        //without parenthesis
        GreetingService greetService2 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("Dario");
        greetService2.sayMessage("Dario1");

    }

}
