package pl.szmitka.hello;

public class App {
    public static void main(String[] args) {
        var name = "Dawid";

        System.out.printf("Hello %s%n", name);

        int a = 2;
        int b = 3;

        System.out.println(a);
        System.out.println(b);
        System.out.println("My result");
        var result = a+b+2;
        System.out.println(result);

        if (result != 5) {
            throw new IllegalStateException("Assertion error");
        }
    }
}
