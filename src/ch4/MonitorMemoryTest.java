package ch4;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms100m -Xmx100m
 */

public class MonitorMemoryTest {

    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            list.add(new OOMObject());
            Thread.sleep(50);
        }
        System.gc(); //old space不會被清空，因為還在相同的方法(作用域)內
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}