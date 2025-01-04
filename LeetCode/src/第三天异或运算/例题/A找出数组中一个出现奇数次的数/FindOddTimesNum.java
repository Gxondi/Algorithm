package 第三天异或运算.例题.A找出数组中一个出现奇数次的数;

public class FindOddTimesNum {
    public int FindOddTimesNum(int[] nums){
        int EOR = 0;
        for (int i = 0; i < nums.length; i++) {
            EOR ^= nums[i];
        }
        return EOR;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,3,4,4,4,4,5,5,5,};
        /*
        * 申请临时变量EOR,循环遍历数组，每个元素都和EOR做异或运算。
        * 根据异或运算的性质，相同的数异或为0，0与任何数异或都为任何数
        * 出现奇数次的数组展现！
        * */
        FindOddTimesNum f = new FindOddTimesNum();
        System.out.println(f.FindOddTimesNum(nums));
    }
}
