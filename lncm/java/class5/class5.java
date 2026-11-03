import java.util.Scanner;
public class class5 {
    static void main(String[] args) {
        test2();
    }
    public static void test1() {
        int a=11, b=23;
        System.out.println("假设a为11，b为23");
        System.out.println("使用if-else判断");
        if (a>b)
        {
            System.out.println(a+"大");
        }
        else
        {
            System.out.println(b+"大");
        }

        System.out.println("使用if判断");
        if (a>b){
            System.out.println(a+"大");

        }
        if (b>a){
            System.out.println(b+"大");
        }
    }
    public static void test2() {
        System.out.println("此块为if-else求解一元二次方程");
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入a、b、c：");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        double delta = b * b - 4 * a * c;

        if (a == 0) {
            System.out.println("不是一元二次方程");
        } else if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);

            System.out.println("两个不相等的实数根");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);

        } else if (delta == 0) {
            double x = -b / (2 * a);
            System.out.println("两个相等的实数根");
            System.out.println("x1 = x2 = " + x);

        } else {
            System.out.println("方程没有实数根");
        }

        sc.close();
    }
    public static void test3() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        switch (a) {
            case 1:
                System.out.println("A");
                break;
            case 2:
                System.out.println("B");
                break;
            case 3:
                System.out.println("C");
                break;
            case 4:
                System.out.println("D");
                break;
            default:
                System.out.println("ERROR");

        }
    }
}