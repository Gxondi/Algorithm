import java.util.ArrayList;
import java.util.List;

class countSmaller {
    List<Integer>ans=new ArrayList<>(); //记录最终的结果
    int[]count; //记录题目中所求的count[i]
    int[]index;
    //入口
    public List<Integer> countSmaller(int[] nums){
        int len=nums.length;
        count=new int[len];
        index=new int[len];
        for (int i = 0; i <nums.length ; i++) {
            index[i]=i;
        }
        MergeSort(nums,0,nums.length-1);
        for (int i = 0; i <len ; i++) {
            ans.add(count[i]);
        }
        return ans;
    }

    /**
     * 归并排序
     * @param nums 待排序数组
     * @param start 数组开始的下标
     * @param end 数组结束的下标
     */
    private void MergeSort(int[] nums,int start,int end){
        if(start<end){
            int mid=start+(end-start)/2;
            MergeSort(nums,start,mid); //将无序数组划分
            MergeSort(nums,mid+1,end); //将无序数组划分
            merge(nums,start,mid,end); //再将两个有序数组合并,只不过这里要承接统计count[i]的功能
        }
    }

    /**
     *  双指针合并两个有序数组并统计count[i]
     * @param nums
     * @param start
     * @param mid
     * @param end
     */
    private void merge(int[]nums, int start, int mid, int end){
        int P1=start;
        int P2=mid+1;
        int cur=0;
        int[]tmp=new int[end-start+1]; //临时数组->用于存储一次归并过程中排序好的元素，
        int[]tmpIndex=new int[end-start+1];//临时数组的索引数组 -> 存储着原数组元素对应的下标

        while (P1<=mid&&P2<=end){
            if(nums[P1]>nums[P2]){
                count[index[P1]]+=end-P2+1; //右半部分小于nums[P1]元素的数目
                tmpIndex[cur]=index[P1]; //记录元素位置的改变
                tmp[cur]=nums[P1];
                P1++;
            }else {
                tmp[cur]=nums[P2];
                tmpIndex[cur]=index[P2];
                P2++;
            }
            cur++;
        }
        while (P1<=mid){
            tmp[cur]=nums[P1];
            tmpIndex[cur]=index[P1];
            P1++;
            cur++;
        }
        while (P2<=end){
            tmp[cur]=nums[P2];
            tmpIndex[cur]=index[P2];
            P2++;
            cur++;
        }
        for (int i = 0; i < end-start+1 ; i++) {
            nums[i+start]=tmp[i];
            index[i+start]=tmpIndex[i];
        }
    }


    public static void main(String[] args) {
        int[]nums={0,1,0};
        countSmaller cs=new countSmaller();
        List<Integer>ans=cs.countSmaller(nums);
        System.out.println(ans);
    }
}
