package jvmSE.day1;

public class MyObject {

    public static void main(String[] args) {
        Object o = new Object();
        Class<?> aClass = o.getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = MyObject.class.getClassLoader();
        System.out.println(classLoader1);

        ClassLoader classLoader2 = String.class.getClassLoader();
        System.out.println(classLoader2 );
        System.out.println(classLoader1.getParent() );
        System.out.println(classLoader1.getParent().getParent() );

    }

}
