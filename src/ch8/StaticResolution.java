package ch8;

/**
 * 觀看字節碼指令：javap -verbose bin.ch8.StaticResolution
 */

public class StaticResolution {
    
    public static void sayHello(){
        System.out.println("hello world");
    }

    public void name() {
        System.out.println("my name is John");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
