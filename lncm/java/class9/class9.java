package class9;
import java.util.Scanner;
public class class9 {
    static void main(String[] args) {
        class9_1();
    }
    public static void class9_1() {
        Scanner sc = new Scanner(System.in);
        String[] arr = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("请输入第" + (i + 1) + "个字符串：");
            arr[i] = sc.nextLine();
        }
        System.out.println("数组中的字符串为：");
        for (int i = 0; i < 5; i++) {
            System.out.println(arr[i]);
        }

        sc.close();


    }
    public static void class9_2() {
    }
    public static void class9_3() {}
}
