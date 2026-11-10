package class14.demo;

public class Car {
    private String color;
    private int speed;

    public Car(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    public void run() {
        System.out.println(this.color + "的车，时速" + this.speed);
    }
}
