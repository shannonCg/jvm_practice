package ch7;

/**
 * 通過子類引用父類的靜態字段，不會導致子類的初始化
 * VM Args: -XX:+TraceClassLoading
 */
public class InitializationTest1 {
    
    public static void main(String[] args) {
        System.out.println(SubClass.value);   
    }
}
