package ch7;

import java.io.IOException;
import java.io.InputStream;

/**
 * VM Args: -XX:+TraceClassLoading
 */
public class ClassLoaderTest {
    public static void main(String[] args)
            throws Exception {
        Object obj = newInstanceFromClassLoader();
        // Object obj = newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ch7.ClassLoaderTest);
    }

    public static Object newInstanceFromClassLoader()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ClassLoader loader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException{
                try {
                    System.out.println("name:"+name);
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    System.out.println("fileName:"+fileName);
                    InputStream in = getClass().getResourceAsStream(fileName);

                    if(in == null){
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[in.available()];
                    in.read(b);
                    return defineClass(name, b, 0, b.length);

                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        return loader.loadClass("ch7.ClassLoaderTest").newInstance();
    }

    public static Object newInstance(){
        return new ClassLoaderTest();
    }
}
