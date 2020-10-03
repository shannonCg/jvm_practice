package ch3;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * VM Args: -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -verbose:class or VM
 * Args: -XX:MetaspaceSize=20m -XX:MaxMetaspaceSize=20m -XX:+TraceClassLoading
 * -XX:+TraceClassUnloading
 */
public class ClassGC {

    public static void main(String[] args) throws Throwable {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        System.out.println("----class loader is " + classloader + "----");
        Thread.sleep(10000);

        // reflect by cglib
        reflectByCglib();

        // reflect by java reflection
        // reflectByJava();

        // reflect by new classloader
        // reflectByNewClassLoader();
        
        Thread.sleep(10000);
        
        System.gc();
        Thread.sleep(10000);
        System.out.println("exit");
    }

    public static void reflectByCglib() {
        int i = 1;
        while (i <= 3) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(ClassGC.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    System.out.println("invoke method:" + method.getName());
                    return proxy.invokeSuper(obj, args);
                }
            });

            enhancer.create();
            i++;
        }
    }

    public static void reflectByJava() throws Throwable {
        int i = 1;
        while (i <= 3) {
            // load class method1
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<?> c = cl.loadClass("ch3.ClassGCTestByNewClassLoader");
            // load class method2
            // Class<?> c = Class.forName("ch3.ClassGCTestByNewClassLoader");

            Object o = c.newInstance();
            Method m = c.getDeclaredMethod("testReflect");
            m.invoke(o);
            i++;
        }
    }

    public static void reflectByNewClassLoader() throws Throwable {
        int i = 1;
        
        //test1: 不會使用到新的classloader，因為會先從parent classloader找class
        //，而parent classloader的路徑是由classpath決定（而vs code會把當前專案的class打包成jar，並把classpath指向此jar)
        //，因此會由parent classloader來載入class
        // URL url = new URL("file:/Users/shaice/program/git/jvm_practice/bin/");
        //test2: 會由新的classloader來載入
        URL url = new URL("file:/Users/shaice/program/git/java_practice/target/classes/");
        
        while(i <= 3){
            try (URLClassLoader cl = new URLClassLoader(new URL[]{url});) {
                System.out.println("new class loader is "+ cl);
    
                //test1:
                // Class<?> c = cl.loadClass("ch3.ClassGCTestByNewClassLoader");
                //test2:
                Class<?> c = cl.loadClass("reflection.ClassGCTestByNewClassLoader");
    
                Object o = c.newInstance();
                Method m = c.getDeclaredMethod("testReflect");
                m.invoke(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            i++;
        }
    }

}