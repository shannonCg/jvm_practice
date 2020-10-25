package ch3;
/**
 * VM Args: -Xms20m -Xmx20m -Xmn10m -verbose:gc -XX:+PrintGCDetails 
 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 */

public class LongSurvivorTest {

    private static final int _1MB = 1024*1024;

    public static void testTenuringThreshold(){
        byte[] allocation1, allocation2, allocation3;

        allocation1 = new byte[_1MB/4];

        allocation2 = new byte[3*_1MB];
        allocation3 = new byte[3*_1MB];
        allocation3 = null;
        allocation3 = new byte[3*_1MB];

        allocation3 = null;
        allocation3 = new byte[3*_1MB];
        allocation3 = null;
        allocation3 = new byte[3*_1MB];
    }

    /**
    * -XX:MaxTenuringThreshold=15
    */
    public static void testTenuringThreshold2(){
        byte[] allocation1, allocation2;
        byte[] allocation3, allocation3_2, allocation3_3, allocation3_4;
        byte[] allocation4;

        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[_1MB/4];

        allocation3 = new byte[3*_1MB];
        allocation4 = new byte[3*_1MB];
        allocation4 = null;
        allocation4 = new byte[3*_1MB];

        allocation4 = null;
        allocation4 = new byte[3*_1MB];
        allocation4 = null;
        allocation4 = new byte[3*_1MB];

        allocation4 = null;
        allocation4 = new byte[3*_1MB];
        allocation4 = null;
        allocation4 = new byte[3*_1MB];
    }

    public static void main(String[] args) {
        // testTenuringThreshold();
        testTenuringThreshold2();
    }
}