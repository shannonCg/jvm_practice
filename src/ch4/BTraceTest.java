package ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BTraceTest {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BTraceTest test = new BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i<10; i++){
            reader.readLine();
            int a = (int) Math.round(Math.random() * 1000);
            int b = (int) Math.round(Math.random() * 1000);
            System.out.println(test.add(a, b));
        }
    }
}

/**
 * 以下為BTrace的程式碼(btrace 2.0.2版)
 * 執行指令：btrace pid BTraceTestScript.java
 */
/*
import org.openjdk.btrace.core.annotations.*;
import static org.openjdk.btrace.core.BTraceUtils.*;
@BTrace
public class BTraceTestScript {
    @OnMethod(
        clazz="ch4.BTraceTest",
        method="add",
        location=@Location(Kind.RETURN)
    )   
    public static void func(@Self Object instance, int a, int b, @Return int result){
        println("調用推：");
        jstack();
        println(strcat("方法參數A:", str(a)));
        println(strcat("方法參數B:", str(b)));
        println(strcat("方法結果:", str(result)));    
    }   
}
*/
