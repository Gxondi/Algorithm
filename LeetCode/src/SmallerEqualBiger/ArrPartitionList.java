package SmallerEqualBiger;

public class ArrPartitionList {
    public class node{
        public int value;
        node next;

        public node() {
        }

        public node(int value) {
            this.value = value;
        }
    }
    public void partition(node[] arrNode , int value){
        if (arrNode == null){return;}
        int less = -1;
        int more = arrNode.length;
        int cur = 0;
        while (cur<more)
        if(arrNode[cur].value<value){
            swap(arrNode,++less,cur++);
        }else if(arrNode[cur].value>value){
            swap(arrNode,--more,cur);
        }else {
            cur++;
        }
    }
    public node partitionList(node head , int value){
        if (head == null){return null;}
        node cur = head;
        int len = 0;
        while(cur!=null){
            len++;
            cur =cur.next;
        }
        node[] arrnode = new node[len];

        cur = head;
        for (int i = 0; i < arrnode.length; i++) {
            arrnode[i] =cur;
            cur =cur.next;
        }
        partition(arrnode,value);
        for (int i = 1; i < arrnode.length; i++) {
            arrnode[i-1].next = arrnode[i];
        }
        arrnode[arrnode.length-1].next = null; //链表最后指向null
        return arrnode[0]; // 返回链表头部
    }
    public node partitionLsit(node head , int value){
        node SH = null;
        node ST = null;
        node EH = null;
        node ET = null;
        node MH = null;
        node MT = null;
        node next = null;
        while (head!=null){
            next = head.next;
            head.next = null;
            if (head.value<value){
                if (SH == head){
                    SH = head;
                    ST = head;
                }else {
                    ST.next = head;
                    ST = head;
                }
            }else if(head.value == value){
                if (EH == head){
                    EH = head;
                    ET = head;
                }else {
                    EH.next = head;
                    ET = head;
                }
            }else {
                if (MH == head){
                    MH = head;
                    MT = head;
                }else {
                    MH.next = head;
                    MT = head;
                }
            }
            head = next;
        }
        if (ST!=null){
            ST.next = EH;
            ET = ET == null?ST:ET;
        }
        if (ET!=null){
            ET.next = MH;
        }
        return SH!=null?SH:(EH!=null?EH:MH);
    }
    private void swap(node[] arrNode, int i, int i1) {
        int temp = arrNode[i].value;
        arrNode[i].value = arrNode[i1].value;
        arrNode[i1].value = temp;
    }
}
