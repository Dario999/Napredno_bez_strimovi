public class HelloWorldAnonymousClasses {

    interface HelloWorld{
        public void greet();
        public void greetSomeone(String someone);
    }

    public void sayHello(){

        class EnglishGreeting implements HelloWorld{
            String name = "world";
            public void greet(){
                greetSomeone("world");
            }
            public void greetSomeone(String someone){
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        HelloWorld englishGreeting = new EnglishGreeting();

        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            @Override
            public void greet() {
                greetSomeone("tout le monde");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };

    }

    public static void main(String[] args) {
        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();
        myApp.sayHello();
    }

}
