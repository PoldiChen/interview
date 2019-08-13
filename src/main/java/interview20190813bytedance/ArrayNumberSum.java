package interview20190813bytedance;

/**
 * @author poldi.chen
 * @className ArrayNumberSum
 * @description 一个整数数组，从中取出不相邻的数，使得和最大
 * @date 2019/8/13 22:13
 **/
public class ArrayNumberSum {

    private int[] numbers;

    private int getSum(int index) {
        if (index == 0) {
            return numbers[0];
        }
        if (index == 1) {
            return Math.max(numbers[0], numbers[1]);
        }
        int a = getSum(index - 1);
        int b = getSum(index - 2) + numbers[index];
        return Math.max(a, b);
    }

    public static void main(String[] args) {
        int[] numbers = {1,5,2,5,7,3};
        ArrayNumberSum object = new ArrayNumberSum();
        object.numbers = numbers;
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(object.getSum(i));
        }
    }

}
