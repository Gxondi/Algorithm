package GreaterHeap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class GreaterHeap<T> {
    //用动态链表定义一个堆
    public ArrayList<T> heap;
    //堆的大小
    public int heapSize;
    //任何一个传进来的T，都用indexmap记录着在heap上的什么位置（反向记录表）
    public HashMap<T,Integer> indexMap;
    public Comparator<? super T> comp;

    public GreaterHeap(Comparator<T> c) {
        this.comp = comp;
        heap = new ArrayList<>();
        heapSize = 0;
        indexMap = new HashMap<>();
    }
    //返回堆大小
    public int getHeapSize() {
        return heapSize;
    }
    public boolean isEmpty(){
        return heapSize == 0;
    }
    //此功能系统并没有提供，因此想要查找样本t需要遍历整个堆代价是O(N);
    //而拥有反向索引表indexMap的化可以直接找到
    public boolean contains(T obj){
        return indexMap.containsKey(obj);
    }
    public T peek(){
        return heap.get(0);
    }
    public void push(T obj){
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize++);
    }
    public T pop(){
        //弹出堆顶值
        T ans = heap.get(0);
        swap(0,heapSize-1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }
    // 系统不提供这个方法，因为没有反向表
    // 关键方法：为什么在一个大根堆或者小根堆中更改某个值后，仍然能调整为大根堆或小根堆
    // 用户要求改变堆中的某个值，但是没告诉我们这个值应该是往上移动还是往下移动
    // 通过indexMap表找到样本在堆中的哪个位置，然后调整为大根堆或小根堆
    // 如果参与排序的指标变换了，一样可以变成想要的堆
    public void reSign(T obj){
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }
    public void remove(T obj){
        T repalce = heap.get(heapSize-1);//找到堆中最后一个元素位置，准备替换掉
        int index = indexMap.get(obj);//找到该元素下标
        heap.remove(--heapSize);
        indexMap.remove(obj);
        if (obj!=repalce){
            heap.set(index,repalce);
            indexMap.put(repalce,index);
            reSign(repalce);
        }
    }
    public void heapInsert(int index) {
        while (comp.compare(heap.get(index),heap.get((index-1)/2))<0){
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }
    public void heapify(int index){
        int left = 2*index + 1;
        while(left<heapSize){
            int largest = left+1 < heapSize && comp.compare(heap.get(left),heap.get(left+1))<0?left+1:left;
            largest = comp.compare(heap.get(index) , heap.get(largest))<0?largest:index;
            if (largest == index){
                break;
            }
            swap(index,largest);
            index = largest;
            left = 2*index + 1;
        }
    }
    //在堆和indexmap表中，换i和j位置的数
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(j,o1);
        heap.set(i,o2);
        indexMap.put(o1,j);
        indexMap.put(o2,i);
    }
}
