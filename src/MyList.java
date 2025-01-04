public interface MyList<E> {
    void add(E element);//添加数据
    E getData(int index);
    E delete(int index);//根据下标删除数据
    int Size();//返回链表大小
    Node reverse(Node node);//反转链表
    DoubleNode reverse(DoubleNode node);
}
