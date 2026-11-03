import java.util.Scanner;

public class class4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请依次输入a，b，c");

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        sc.close();

        double delta = (b * b - 4 * a * c);
        double sqrtdata = Math.sqrt(delta);

        double x1 = (-b + sqrtdata) / (2 * a);
        double x2 = (-b - sqrtdata) / (2 * a);

        System.out.println("x1=" + x1);
        System.out.println("x2=" + x2);
    }
}