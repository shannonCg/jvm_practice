package ch7;

public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public final static String HELLOWORLD = "hello world";
}
