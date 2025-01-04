package 第三天异或运算.例题.C找出数组中二个出现奇数次的数;

public class FindOddTimesNum {
    public void FindOddTimesNum(int[] nums){
        /*
        *   思路：由于有两个元素出现次数都为奇数，所以EOR=a^b
        *   假设，a^b最右侧的1在第K位，那么a和b在第K位一定是不同的
        *   因此可得，a和b在第K位的数一定一个为0，一个为1
        *   以第K位是否为1为标准，将数组分为两部分，一部分第K位为1，一部分第K位为0
        * */
        int EOR = 0;
        for (int i = 0; i < nums.length; i++) {
            EOR ^= nums[i];
        }
        int rightOne = EOR & (~EOR + 1);//提取出EOR最右侧的1（0000000001000）
        int onlyOne = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & rightOne) != 0){//找到k为1的数
                onlyOne ^= nums[i];// 功能一样：EOR ^= nums[i] == onlyOne ^= nums[i]
            }
        }
        System.out.println(onlyOne + "和" + (onlyOne ^ EOR));
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,3,4,4,4,4,5,5,5,9};
        FindOddTimesNum f = new FindOddTimesNum();
        f.FindOddTimesNum(nums);
    }
}
