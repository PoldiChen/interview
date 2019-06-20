package vivo;

/**
 * @author poldi.chen
 * @className Test
 * @description TODO
 * @date 2019/6/20 10:55
 **/
public class Test {

    public static void main(String[] args) {
        getSeries(15);
    }

    private static void getSeries(int n) {
        int middle = (n+1)/2;
        int left = 1;
        int right = 2;
        int sum = left + right;
        while (left <= middle) {
            if (sum < n) {
                sum += ++right;
            } else if (sum > n) {
                sum -= left++;
            } else {
                System.out.println("left: " + left + ", right: " + right);
                sum += ++right;
                sum -= left++;
            }
        }
        System.out.println("done");
    }
}
