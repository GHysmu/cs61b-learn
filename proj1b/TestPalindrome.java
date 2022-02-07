import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {

        OffByOne cc = new OffByOne();
        OffByN ccN = new OffByN(5);

        assertEquals(true,palindrome.isPalindrome("abba"));
        assertEquals(true,palindrome.isPalindrome("1"));
        assertEquals(true,palindrome.isPalindrome(""));

        assertEquals(false,palindrome.isPalindrome("cat"));
        assertEquals(false,palindrome.isPalindrome("aaabaa"));


        assertEquals(false,palindrome.isPalindrome("fdede",cc));
        assertEquals(true,palindrome.isPalindrome("flake",cc));

        assertEquals(false,palindrome.isPalindrome("flake",ccN));
        assertEquals(true,palindrome.isPalindrome("flaqa",ccN));

    }




    //Uncomment this class once you've created your Palindrome class.
}
