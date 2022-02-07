public class LinkedListDeque<T> implements Deque<T> {

    private class LinkedListNode<T>{
        private LinkedListNode<T> prev;
        private LinkedListNode<T> next;
        T item;

        private LinkedListNode(LinkedListNode p,T i, LinkedListNode n){
            prev = p;
            next = n;
            item = i;
        }

    }


    private int size;
    private LinkedListNode<T> dummy;

    public LinkedListDeque(){
        dummy = new LinkedListNode<T>(null,(T) new Object(),null);
        dummy.prev = dummy;
        dummy.next = dummy;
        size = 0;

    }

    @Override
    public void addFirst(T item) {
        LinkedListNode<T> first = new LinkedListNode<>(dummy,item,dummy.next);
        dummy.next.prev = first;
        dummy.next = first;
        size = size +1;
    }

    @Override
    public void addLast(T item) {
        LinkedListNode<T> last = new LinkedListNode<>(dummy.prev,item,dummy);
        dummy.prev.next = last;
        dummy.prev = last;
        size = size +1;
    }

    @Override
    public boolean isEmpty() {
        return (size()==0);

    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        LinkedListNode<T> temp = dummy;
        while (temp.next != dummy){
            System.out.println(temp.next.item);
            temp = temp.next;
        }
    }
    @Override
    public T removeFirst() {
        //empty list?
        if(isEmpty()) return null;
        T removedItem = dummy.next.item;
        dummy.next = dummy.next.next;
        dummy.next.prev = dummy;
        size = size - 1;
        return removedItem;

    }


    @Override
    public T removeLast() {
        if(isEmpty()) return null;
        T removedItem = dummy.prev.item;
        dummy.prev = dummy.prev.prev;
        dummy.prev.next = dummy;
        size = size - 1;
        return removedItem;

    }


    @Override
    public T get(int index) {
        LinkedListNode<T> temp = dummy.next;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        return temp.item;
    }



}
