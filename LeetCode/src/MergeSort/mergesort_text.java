package MergeSort;

import com.sun.scenario.effect.Merge;

import java.util.ArrayList;
import java.util.List;

public class mergesort_text {
    List<Integer>ans=new ArrayList<>();
    int[] count;
    int[] index;
    public List<Integer> countSmaller(int[] nums){
        int len=nums.length;
        count=new int[len];
        index=new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i;
        }
        MergeSort_Process(nums,0,nums.length-1);
        for (int i = 0; i <len ; i++) {
            ans.add(count[i]);
        }
        return ans;
    }
    private void MergeSort_Process(int[] nums, int L, int R){
        if(L<R) {
            int m = L + ((R - L) >> 1);
            MergeSort_Process(nums, L, m);//左边
            MergeSort_Process(nums, m + 1, R);//右边
            MergeSort02(nums, L, m, R);
        }
    }
    /*
    private static void MergeSort(int[] nums, int l, int m, int r) {
        //临时数组
        int[] tmp = new int[r-l+1];
        int cur = 0;
        int p1 = l;
        int p2 = m+1;
        while(p1<=m && p2<=r){
            if(nums[p1]<nums[p2]){
                tmp[cur] = nums[p1];
                p1++;
            }else {
                tmp[cur] = nums[p2];
                p2++;
            }
            cur++;
        }
        while(p1<=m){
            tmp[cur] = nums[p1];
            cur++;
            p1++;
        }
        while(p2<=r){
            tmp[cur] = nums[p2];
            cur++;
            p2++;
        }
        for (int i = 0; i < r-l+1; i++) {
            nums[i+l] = tmp[i];
        }
    }
    * */
    private void MergeSort02(int[] nums, int l, int m, int r) {
        //临时数组
        int[] tmp = new int[r-l+1];
        int[] tmeindex = new int[r-l+1];
        int cur = 0;
        int p1 = l;
        int p2 = m+1;
        while(p1<=m && p2<=r){
            if(nums[p1]>nums[p2]){
                count[index[p1]] += r-p2+1;
                tmeindex[cur] = index[p1];
               tmp[cur] = nums[p1];
                p1++;
            }else {
                tmp[cur] = nums[p2];
                tmeindex[cur] = index[p2];
                p2++;
            }
            cur++;
        }
        while(p1<=m){
            tmp[cur] = nums[p1];
            tmeindex[cur] = index[p1];
            cur++;
            p1++;
        }
        while(p2<=r){
            tmp[cur] = nums[p2];
            tmeindex[cur] = index[p2];
            cur++;
            p2++;
        }
        for (int i = 0; i < r-l+1; i++) {
            nums[i+l] = tmp[i];
            index[i+l] = tmeindex[i];
        }
    }
    public static void main(String[] args) {

        int[] a = {8,4,3,1,5,3,2,1};
        mergesort_text cs=new mergesort_text();
        List<Integer>ans=cs.countSmaller(a);
        System.out.println(ans);
    }
}
