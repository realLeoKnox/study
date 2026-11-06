package class12;

public class Test {
    static void main(String[] args) {
        Car car = new Car();
        Car car1 = new Car();

        car.color = "red";
        //car.speed = 180;

        car.fly("black");

        car1.color = "green";
        car1.fly("yello");

    }
}
class Car{
    String color;
    private int speed;
    int sear=5;

    void fly(String color){
        System.out.println(this.color+" car is flying in "+color+ "cloud");
    }
    void speed(int speed){

    }
}
