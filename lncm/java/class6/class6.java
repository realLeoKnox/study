import java.util.Scanner;

public class class6 {
    public static void main(String args[])
    {
        while1();
        for1();
    }

    static void switch1()
    {
        System.out.println("11111");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        switch (a/10){
            case 9:
            case 10:
                System.out.println("A");
                break;
            case 8:
                System.out.println("B");
                break;
           case 7:
               System.out.println("C");
               break;
           case 6:
               System.out.println("D");
               break;
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
            case 0:
                System.out.println("E");
                break;
            default:
                System.out.println("输入错误");
        }
    }
    static void while1()
    {
        int sum = 0;
        int i = 1;
        while(i<=100)
            {
            sum += i; /* 等价 sum = sum + i */
            i++;
            }
        System.out.println(sum);
    }
    static  void for1()
    {
        int sum,i;
        sum=0;
            for(i=1;i<=100;i++)
            {
                sum += i;
            }

        System.out.println(sum);
    }
}
