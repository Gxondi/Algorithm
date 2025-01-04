public class MyDoubleList<E> implements MyList<E>{
    private DoubleNode head;
    private DoubleNode tail;
    private int size;
    @Override
    public void add(E element) {
        tail = getTail();
        DoubleNode newNode = new DoubleNode(tail,element,null);
       if (tail== null) {
            head = newNode;
        }else {
            tail.setNext(newNode);
        }
        size++;
    }
    private DoubleNode getTail() {
        DoubleNode newNode = head;
        if (head == null)return null;
        while (true){
            if (newNode.getNext() == null)break;
                newNode = newNode.getNext();
        }
        return newNode;
    }

    @Override
    public E getData(int index) {
        DoubleNode<E> doubleNode = indexOfNode(index);
        E data = doubleNode.getData();
        return data;
    }

    private DoubleNode indexOfNode(int index) {
        if (index<0&&index>=size)return null;
        DoubleNode newNode = head;
        for (int i = 0; i < index; i++) {
            newNode = newNode.getNext();
        }
        return newNode;
    }

    @Override
    public E delete(int index) {
        if (index<0&&index>=size)return null;
        DoubleNode CurrentNode = indexOfNode(index);
        if (CurrentNode == head){
            head = CurrentNode.getNext();
            head.setPre(null);
            CurrentNode.setNext(null);
        }else {
             CurrentNode.getPre().setNext(CurrentNode.getNext());
        }
        if (CurrentNode.getNext() == null){
            tail = CurrentNode.getPre();
        }else {
            CurrentNode.getNext().setPre(CurrentNode.getPre());
        }
        size--;
        CurrentNode.setPre(null);
        return null;
    }

    @Override
    public int Size() {
        return size;
    }

    @Override
    public Node reverse(Node node) {
        return null;
    }

    @Override
    public DoubleNode reverse(DoubleNode node) {
        DoubleNode newNode = node;
        DoubleNode next = null;
        DoubleNode pre = null;
        while (newNode!=null){
            next = newNode.getNext();
            newNode.setNext(pre);
            newNode.setPre(next);
            pre = newNode;
            newNode = next;
        }
        return pre;
    }
}
