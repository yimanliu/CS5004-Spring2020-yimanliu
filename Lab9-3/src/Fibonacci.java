import java.util.Iterator;

public class Fibonacci implements Iterator {
    private int prev = 0;
    private int curr = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            int res = prev;
            int temp = curr;
            curr = curr + prev;
            prev = temp;
            return res;
        }
        return -1;
    }

    public static void main(String[] args) {
        Fibonacci fb = new Fibonacci();
        for (int i = 0; i < 20; i++) {
            System.out.println(fb.next());
        }
    }
}
