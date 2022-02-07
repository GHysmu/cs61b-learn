import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayDequeGold {

    @Test
    public void TestArrayDeque() {

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();

        StringBuilder errs = new StringBuilder();
        //errs.append("\n");
        String err = "";
        int s = 0;


        for (int i = 0; i < 500; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (i % 5 == 0) {
                errs.append("size()\n");
                assertEquals(errs.toString(), sad2.size(), sad1.size());
            }

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                sad2.addLast(i);
                s++;

                err = "addLast(" + i + ")\n";
                errs.append(err);

                assertEquals(errs.toString(),sad2.get(s-1),sad1.get(s-1));

            } else if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                sad2.addFirst(i);
                s++;
                err = "addFirst(" + i + ")\n";
                errs.append(err);
                assertEquals(errs.toString(),sad2.get(0),sad1.get(0));


            } else if (numberBetweenZeroAndOne < 0.75 ) {
                if (sad2.isEmpty()) {
                    errs.append("isEmpty()\n");
                    assertTrue(errs.toString(), sad1.isEmpty());
                    continue;
                }

                Integer s1 = sad1.removeLast();
                Integer s2 = sad2.removeLast();
                s--;
                err = "removeLast()\n";
                errs.append(err);
                assertEquals(errs.toString(),s2,s1);

            } else {
                if (sad2.isEmpty()) {
                    errs.append("isEmpty()\n");
                    assertTrue(errs.toString(), sad1.isEmpty());
                    continue;
                }
                Integer s1 = sad1.removeFirst();
                Integer s2 = sad2.removeFirst();
                s--;
                err = "removeFirst()\n";
                errs.append(err);
                assertEquals(errs.toString(),s2,s1);
            }

        }

        //sad1.printDeque();


    }
}
