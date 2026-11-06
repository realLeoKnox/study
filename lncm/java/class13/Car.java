package class13;

class Car {
    protected String color;
    private int speed;

    void fly(){
        System.out.println("车在飞");
    }

    void fly(String color){
        System.out.println(this.color+" 车在"+color+"颜色的云飞");
    }

public void main(String[] args) {
        Car a = new Car();
        Car b = new Car();

        a.color="red";
        a.speed=180;
        a.fly();
        a.fly("black");

        b.color="green";
        b.speed=720;
        b.fly();
        b.fly("blue");

}
}