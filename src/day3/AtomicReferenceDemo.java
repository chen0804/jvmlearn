package day3;


import java.util.concurrent.atomic.AtomicReference;

class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
//原子引用
//利用时间戳修改版本号
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User user1 = new User("chen",29);
        User user2 = new User("cao",26);
        userAtomicReference.set(user1);

        boolean b = userAtomicReference.compareAndSet(user1, user2);
        System.out.println(b + " " + userAtomicReference.get());


    }

}
