package ch6;

public class TestClass {

    private int m;

    public TestClass(int mm){
        this.m = mm;
    }

    public int inc(){
        // return m+1;
        int x;
        try {
            x = 1;
            throw new Exception();
            // return x;
        } catch (Exception e) {
            x = 2;
            return x;
        }finally{
            x = 3;
            return x;
        }
    }

    public static void main(String[] args) {
        TestClass test = new TestClass(3);
        System.out.println(test.inc());
    }
}