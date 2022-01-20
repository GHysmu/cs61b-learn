public class TestArrayDeque {
    public static void main(String[] args){

        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(5);
        a.addFirst(10);


        System.out.println("test -1 ");
        a.printDeque();

        a.addLast(26);
        a.addLast(17);
        a.addLast(17);
        a.addLast(17);

        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();

        System.out.println("test - 2 ");
        a.printDeque();

    }
}
