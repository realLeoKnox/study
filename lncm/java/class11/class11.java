package class11;

public class class11 {
    public static int sum(int... nums){
        int sums = 0;
        for(int num : nums){
            sums += num;
        }
        return sums;
    }
    public static void main(String[] args) {
        System.out.println(sum(1,2,3));
    }
}
