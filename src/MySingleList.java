import sun.applet.Main;

import javax.lang.model.element.VariableElement;

public class MySingleList<E> implements MyList{
    Node head;//头
    Node tail;//尾
    int size;
    @Override
    public void add(Object element) {
        Node newNode = new Node(element,null);
        Node tail = getTail();
        if (tail == null){
            head = newNode;
        }else {
            tail.setNext(newNode);
        }
        size++;
    }

    @Override
    public E getData(int index) {
        if (head == null){
            return null;
        }
        Node<E> indexOfNode = getIndexOfNode(index);
        return indexOfNode.getData();
    }

    private Node getIndexOfNode(int index) {
        Node newNode = head;
        if (index<0 && index>=size){
            return null;
        }
        for (int i = 0; i < index; i++) {
            newNode = newNode.getNext();
        }
        return newNode;
    }

    Node getTail(){
        Node node = head;
        if (head == null){
            return null;
        }else {
            while (true){
                if (node.getNext()==null)break;
                node = node.getNext();
            }
        }
        return node;
    }

    @Override
    public E delete(int index) {
        Node<E> CruuentNode = getIndexOfNode(index);
        E data = CruuentNode.getData();
        if (head == CruuentNode){
            head = CruuentNode.getNext();
        }else {
            Node preNode = getIndexOfNode(index-1);//当前节点前一个
            preNode.setNext(CruuentNode.getNext());
        }
        CruuentNode.setNext(null);
        size--;
        return data;
    }

    @Override
    public int Size() {
        return size;
    }

    @Override
    public Node reverse(Node headNode) {
        Node pre = null;
        Node next = null;
        while (headNode!=null){
            next = headNode.getNext();
            headNode.setNext(pre);
           pre = headNode;
           headNode = next;
        }
        return pre;
    }

    @Override
    public DoubleNode reverse(DoubleNode node) {
        return null;
    }

}
