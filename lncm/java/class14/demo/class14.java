package class14.demo;

public class class14 {
    public static void main(String[] args) {
        Car c1 = new Car("红色", 180);
        Phone p1 = new Phone("13800000000","Apple");
        Student s = new Student("张三", 21, 74, c1, p1);
        s.display();
    }
}
