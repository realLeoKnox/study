package class14.demo;

public class Phone {
    private String number;
    private String brand;

    public Phone(String number, String brand) {
        this.number = number;
        this.brand = brand;
    }
    public void call() {
        System.out.println("用" + brand + "手机，号码" + number + "打电话");
    }
}
