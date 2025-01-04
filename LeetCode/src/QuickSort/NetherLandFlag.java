package QuickSort;
//荷兰国旗问题（快速排序）
public class NetherLandFlag {
    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    /*
    * 最近一个数与末尾right交换，以right的数为参考
    * 其次，partition会将数组划分为左中右
    * 然后调用quicSort再次快速排序
    * */
    private static void quicSort(int[] nums,int left,int right){
        if (nums.length<2||nums == null){
            return;
        }
        if(left<right){
            swap(nums, left + (int) (Math.random() * (right - left + 1)), right);

            int p[] = partition(nums,left,right);
            quicSort(nums,left,p[0]-1);
            quicSort(nums,p[1]+1,right);
        }
    }
    /*
    * 方法说明：
    * less左边界
    * more右边界，以right为参考值
    * 再数值判断大小以后与right相等的数值会被放在中间这些值已经可以不动了因为前面的比他小后面的比他大不需要再动了
    * 再次调用swap，把right的值和more的值交换
    * 把边界返回回去
    * 再次进行左，右的递归调用
    * */
    private static int[] partition(int[] nums, int left, int right) {
        int less = left-1;
        int more = right;
        while(left<more){
            if(nums[left] < nums[right]){
                swap(nums,++less,left++);//++less和left（下标）交换，因为小的在左边
            }else if(nums[left]>nums[right]){
                swap(nums,--more,left);
            }else {
                left++;
            }
        }
        swap(nums,more,right);
        return new int[]{less+1,more};
    }

    private  static int[] NetherLandFlag(int[] nums, int left,int right,int num){
        int less = left-1;
        int more = right+1;
        int index = left;
        while(index<more){
            if (nums[index] < num){
                swap(nums,++less,index++);
            }else if(nums[index] > num){
                swap(nums,--more,index);
            }else {
                index++;
            }
        }
        int ans[] = {less+1,more-1};
        return ans;
    }
    public static void main(String[] args) {
        int[] a = {0,1,2,5,4,1,3,5,1,0,1,0,0,0};
        quicSort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }

    }
}
