public class OffByN implements CharacterComparator {
    /**
     * A class for off-by-N comparators.
     */

    private int n;

    public OffByN(int N) {
        this.n = N;

    }
    @Override
    public boolean equalChars(char x, char y) {

        if(Math.abs((x-y)) == n) return true;
        else return false;

    }



}
