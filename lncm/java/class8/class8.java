package class8;

public class class8 {
    static void main(String[] args) {
        homework();
    }

    static void c1() {
        String[] a;
        a=new String[5];
        int[] b ={1,2,3};
        //普通for遍历字符数组a
        for (int i = 0; i< a.length;i++){
            System.out.println(a[i]);
        }

        //增强for遍历b，不可改变数组元素
        for (int x : b) {
            System.out.println(x);
        }
    }
    static void c2(){
        int[][] arr = new int[3][4];


    }
    static void c3(){
        int[][] ragged = new int[3][];
        ragged[0] = new int[2];
        ragged[1] = new int[5];
        ragged[2] = new int[3];
    }
    static void c4(){
        int[][] arr = new int[3][];
        for (int i = 0; i< arr.length;i++){
            for (int j = 0; j< arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }
    static void homework() {
        int[] arr = {3, 7, 1, 9, 4};
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        System.out.println(max);

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // 4. 遍历二维数组，计算每行平均值
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;

            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }

            double avg = (double) sum / matrix[i].length;

            System.out.printf("第%d行平均值：%.2f%n", i + 1, avg);
        }

        // 5. 将大于5的元素替换为0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 5) {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println("修改后的矩阵：");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

