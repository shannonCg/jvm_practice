package ch7;

/**
 * 通過數組定義來引用類，不會觸發此類的初始化
 * VM Args: -XX:+TraceClassLoading
 */
public class InitializationTest2 {
    
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }
}
