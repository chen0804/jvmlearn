package day4;

public class ValueAndReferenceTest {

    public static void main(String[] args) {
        int a = 20;
        change(a);
        System.out.println(a);

    }

    public static int change(int a){
        a = 30;
        return a;
    }

}
