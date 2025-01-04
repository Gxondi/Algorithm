package 二分查找;

public class nearestNum {
    //找到二分查找目标最左下标的数
    public int nearestNum(int[] nums,int target){
        int L = 0;
        int R = nums.length-1;
        int mid = 0;
        int index = -1;
        while(L<R){
            mid = L + ((R - L)>>1);
            if(target<=nums[mid]){
                index = mid;
                R = mid-1;
            }else {
                L = mid+1;
            }
        }
        return target == nums[L]?L:index;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,3,5,6,6,6,7,8,9,9,9,9,10,10};
        nearestNum n = new nearestNum();
        System.out.println( n.nearestNum(nums,9));
    }
}
