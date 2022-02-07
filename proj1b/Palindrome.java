public class Palindrome {
    /**
     *  A class for palindrome operations.
     */

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {

        Deque<Character> deque = wordToDeque(word);

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;

    }



    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);

        while (deque.size() > 1) {
            if (!cc.equalChars(deque.removeFirst(),deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
