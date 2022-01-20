public class ArrayDeque<T>{

    /**
     * 因为arraydeque中需要实现add/remove first 以O（1）的时间复杂度
     * 本质双边（循环序列） 还是需要left & right
     * save unused [capacity -1 ] as dummy
     */
    public T[] items;
    private int left;
    private int right;
    private int capacity = 8;

    public ArrayDeque(){
        items = (T[]) new Object[capacity];
        left = right = 0;//empty

    }

    private boolean isFull(){
        if((right-left+capacity)%capacity==capacity-1){
            return true;
        }
        else return false;
    }

    /**
     * use % to combine both minus and positive condition (loop queue)
     * e.g. left = 0 -> new left = capacity -1
     * e.g. left = capacity - 1 -> new left = capacity -2
     * make sure each index is positive
     * add first: move + add
     * @param item
     */
    public void addFirst(T item){
        if(isFull()){
            resize((int) (capacity*1.5));
        }
        left = (left-1+capacity)%capacity;
        items[left] = item;


    }

    /**
     * add last : add&move
     * @param item
     */
    public void addLast(T item){
        if(isFull())
        {
            resize((int) (capacity*1.5));
        }
        items[right] = item;
        right = (right+1)%capacity;

    }

    private void resize(int newSize){
        /**
         * use iteration for copy
         */
        T[] temp = (T[]) new Object[newSize];
        int size = size();

        if(left<right) {
            for (int i = left, j = 0; i < right; i++, j++) {
                temp[j] = items[i];
            }
        }
        else {
            int j=0;
            for(int i=left;i<capacity;i++,j++){
                temp[j] = items[i];
            }
            for(int i=0;i<right;i++,j++){
                temp[j] = items[i];
            }

        }

        left = 0;
        right = size;
        capacity = newSize;
        items = temp;

        }


    public boolean isEmpty(){
        if(left == right) return true;
        else return false;

    }

    public int size(){

        return (right-left+capacity)%capacity;
    }

    public boolean lowUsage(){
        if(capacity>=16&&size()<capacity/4){
            return true;
        }
        else return false;
    }

    public T removeFirst(){
        //check useage
        if(size()==0) return null;
        if(lowUsage()){
            resize(capacity/2);
        }

        //remove
        T removedFirst = items[left];
        left = (left+1+capacity)%capacity;
        return removedFirst;

    }
    public T removeLast(){
        /**
         * if usage <= 0.25 * items.length && size >= minize items.length
         * change items.length -> 1/2 items.length;
         */

        //check uaseage
        if(size()==0) return null;
        if(lowUsage()){
            resize(capacity/2);
        }

        //
        right = (right-1+capacity)%capacity;
        T removedLast = items[right];
        return removedLast;

    }

    public T get(int index){

        if(index==0||index>size()-1) return null;
        else{
            int newIndex = (index+left+capacity)%capacity;
            return items[newIndex];
        }
    }

    public void printDeque(){

        if(left<=right){
            for(int i = left;i<right;i++)
            System.out.println(items[i]);
        }
        else {
            for(int i=left;i<capacity;i++){
                System.out.println(items[i]);
            }
            for(int j=0;j<right;j++){
                System.out.println(items[j]);
            }
        }
    }

}
