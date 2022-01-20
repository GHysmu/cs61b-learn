public class LinkedListDeque<T> {
    /**
     * Doubled Link Node
     * @param <T>
     */
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

    /**
     * dummy -> first ->>>>>> last -> dummy
     * first = dummy.next
     * last = dummy.prev
     */
    private int size;
    private LinkedListNode<T> dummy;
    /*
    public LinkedListDeque(T i){
        LinkedListNode<T> first = new LinkedListNode<T>(null,i,null);
        dummy = new LinkedListNode<T>(first,i,first);
        first.next = dummy;
        first.prev = dummy;
        size = 1;
    }
    */
    public LinkedListDeque(){
        dummy = new LinkedListNode<T>(null,(T) new Object(),null);
        dummy.prev = dummy;
        dummy.next = dummy;
        size = 0;

    }

    public void addFirst(T item){
        LinkedListNode<T> first = new LinkedListNode<>(dummy,item,dummy.next);
        dummy.next.prev = first;
        dummy.next = first;
        size = size +1;
    }

    public  void addLast(T item){
        LinkedListNode<T> last = new LinkedListNode<>(dummy.prev,item,dummy);
        dummy.prev.next = last;
        dummy.prev = last;
        size = size +1;

    }

    public boolean isEmpty(){
        return (size()==0);

    }

    public int size(){
        return size;

    }

    public void printDeque(){
        LinkedListNode<T> temp = dummy;
        while (temp.next != dummy){
            System.out.println(temp.next.item);
            temp = temp.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeFirst(){
        //empty list?
        if(isEmpty()) return null;
        T removedItem = dummy.next.item;
        dummy.next = dummy.next.next;
        dummy.next.prev = dummy;
        size = size - 1;
        return removedItem;

    }

    /**
     * Removes and returns the item at the back of the deque.
     *If no such item exists, returns null.
     * @return
     */
    public T removeLast(){
        if(isEmpty()) return null;
        T removedItem = dummy.prev.item;
        dummy.prev = dummy.prev.prev;
        dummy.prev.next = dummy;
        size = size - 1;
        return removedItem;

    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index){
        LinkedListNode<T> temp = dummy.next;
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        return temp.item;
    }

    public T getRecursive(int index){

        return getRecursiceHelp(index, dummy.next);


    }

    private T getRecursiceHelp(int index,LinkedListNode<T> temp){
        if(index == 0) return temp.item;
        else return getRecursiceHelp(index - 1,temp.next);
    }
}
