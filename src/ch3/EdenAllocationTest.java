package ch3;
/**
 * VM Args: -Xms20m -Xmx20m -Xmn10m -verbose:gc -XX:+PrintGCDetails
 */

public class EdenAllocationTest {

    private static final int _1MB = 1024*1024;

    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        allocation4 = new byte[3*_1MB]; //因新生代內存不夠，且物件還被引用，所以會把物件存到老年代
                                        //而觸發FullGC的條件是老年代的連續空間小於新生代對象總大小，
                                        //反之則只會觸發MinorGC(把VM參數調成-Xms25m -Xmx25m就不會觸發FullGC)
        // allocation4 = new byte[4*_1MB]; //在parallel scavenge會直接把4MB以上的大物件存進老年代
    }

    public static void main(String[] args) {
        testAllocation();
    }
}