public class DoubleNode<E> {
    private DoubleNode pre;
    private E data;
    private DoubleNode next;

    public DoubleNode(DoubleNode pre, E data, DoubleNode next) {
        this.pre = pre;
        this.data = data;
        this.next = next;
    }

    public DoubleNode(E data) {
        this.data = data;
    }

    public DoubleNode() {
    }

    public DoubleNode getPre() {
        return pre;
    }

    public void setPre(DoubleNode pre) {
        this.pre = pre;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }
}
