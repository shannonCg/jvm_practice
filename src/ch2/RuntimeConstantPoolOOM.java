package ch2;

import java.util.HashSet;
import java.util.Set;

/**VM Args: 
 * (java6) -XX:PermSize=6m -XX:MaxPerSize=6m
 * (java7 and later version) -Xms6m -Xmx6m -XX:HeapDumpOnOutOfMemoryError
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        
        short i = 0;
        while(true){
            set.add(String.valueOf(i++).intern());
        }

        //字符串常量池引用測試(須先註解上面的while再執行)
        // String str2 = new StringBuilder("ja").append("va").toString();
        // System.out.println(str2.intern() == str2);

        // String str3 = new StringBuilder("ja").append("va").toString();
        // System.out.println(str3.intern() == str3);
        // System.out.println(str3.intern() == str2);
        // System.out.println(("java") == str2);
        // System.out.println(("ja"+"va") == str2);
        // System.out.println(("jav"+"a") == str2);
    }
}