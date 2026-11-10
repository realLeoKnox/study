package class14.demo;

// 一个类的对象可以作为另一个类的成员变量（组合，has-a）
public class Student {
    private String name;
    private int age;
    private double grade;
    private Car car;          // Car 类的对象作为成员变量
    private Phone phone;
    public Student(String name, int age, double grade, Car car,Phone phone) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.car = car;
        this.phone = phone;
    }

    public void display() {
        System.out.println("姓名：" + name + "，年龄：" + age + "，成绩：" + grade);
        car.run();            // 通过成员变量 car 调用 Car 的方法
        phone.call();
    }
}
