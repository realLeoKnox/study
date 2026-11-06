package class13;

public class Car_Demo2 {
    protected String color;
    int speed;
    String name;

    public Car_Demo2(){

    }
    public Car_Demo2(String color,int speed){
        this("car",color,speed);     // 调用三参构造，名字默认为 car
    }
    public Car_Demo2(String name,String color,int speed){
        this.name=name;
        this.color=color;
        this.speed=speed;
    }
    private void fly(){
        System.out.println(name+" is flying");
    }
    void fly(String color){
        System.out.println(this.color+" "+name+" is flying in "+color+" cloud");
    }

    public static void main(String[] args) {
        Car_Demo2 a = new Car_Demo2();               // 无参构造
        Car_Demo2 b = new Car_Demo2("red", 180);     // 带参构造

        System.out.println(a.color + " " + a.speed); // null 0：没赋值，是默认值
        System.out.println(b.color + " " + b.speed); // red 180

        b.fly();                 // 在本类里，可以调用 private 方法
        b.fly("black");          // red car is flying in black cloud

        Car_Demo2 c = new Car_Demo2("BMW M2", "grey", 300);  // 三参构造
        System.out.println(c.name + " " + c.color + " " + c.speed); // BMW M2 grey 300
        c.fly("white");          // grey BMW M2 is flying in white cloud
    }
}
