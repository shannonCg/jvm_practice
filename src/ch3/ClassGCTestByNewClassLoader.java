package ch3;

public class ClassGCTestByNewClassLoader {

    public void testReflect(){
        System.out.println("++++invoke start++++");
        String className = this.getClass().getName();
        System.out.println("class is "+className);
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println("class loader is "+ classLoader);
        ClassLoader parentClassLoader = classLoader.getParent();
        System.out.println("parent class loader is "+ parentClassLoader);
        System.out.println("++++invoke end++++");
    }
}