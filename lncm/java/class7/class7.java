package class7;

public class class7 {
    static void main(String[] args) {
        shuzu3();

    }

    static void shuzu() {
        int[] arr = new int[]{1,2,3};
        System.out.println(arr);
        System.out.println(arr[0]);


    }
    static void shuzu2(){
        int[] arr = new int[3];//左边arr在栈，右边在堆
        //打印数组对象的默认字符串表示（类型信息 + 哈希码）
        System.out.println(arr);//输出类似，[I@15db9742
        //打印数组元素的默认值
        System.out.println(arr[0]);//输出：0
        System.out.println(arr[1]);//输出：0
        System.out.println(arr[2]);//输出：0
        //修改数组元素的值
        arr[0] = 10; arr[1] =20; arr[2] = 30;
        //打印修改后的值
        System.out.println(arr[0]);//输出：10
    }
    static void shuzu3(){
        int[] arr = {10,20,30};
        System.out.println(arr[0]);
        System.out.println(arr[2]);
        System.out.println(arr.length);
    }
}
