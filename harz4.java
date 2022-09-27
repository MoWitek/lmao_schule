import java.util.Arrays;
import java.util.Iterator;


public class harz4 <T> implements Iterable<T>, Iterator<T> {
    private T[] arr;
    private int sz;

    private int itercount;

    public String toString() {
        String sr = "[";

        harz4<T> hrz = slice(0, -2);
        for (T i: hrz) {
            sr += i + ", ";
        }
        return sr + gib(-1) + "]";
    }

    harz4<T> slice(int start, int stop) {

        if (start < 0) {
            if (sz == 0) {
                start = 0;
            } else {
                start = sz + start;
            }
        }

        if (stop < 0) {
            if (sz == 0) {
                stop = 0;
            } else {
                stop = sz + stop + 1;
            }
        }

        harz4<T> dynar = new harz4<>();

        for (int i = start; i < stop; i++) {;
            dynar.hab(arr[i]);
        }

        return dynar;
    }

    harz4() {
        arr = (T[]) new Object[0];
    }

    T gib(int pos) {
        if (pos < 0) {
            if (sz == 0) {
                pos = 0;
            } else {
                pos = sz + pos;
            }
        }
        return arr[pos];
    }

    T gibzuruck(int pos) {
        T[] ar = (T[]) new Object[sz - 1];

        // negative index support
        if (pos < 0) {
            if (sz == 0) {
                pos = 0;
            } else {
                pos = sz + pos;
            }
        }

        T out = arr[pos];

        for (int i = 0; i < pos; i++) {
            ar[i] = arr[i];
        }

        for (int i = pos; i < sz-1; i++) {;
            ar[i] = arr[i+1];
        }

        arr = ar;
        sz--;

        return out;
    }

    T gibzuruck() {
        return gibzuruck(0);
    }

    void reverse() {
        T[] ar = (T[]) new Object[sz];
        int c = sz-1;
        for (T i: arr) {
            ar[c] = i;
            c--;
        }
        arr = ar;
    }

    void hab(T item, int pos) {
        T[] ar = (T[]) new Object[sz + 1];

        // negative index support
        if (pos < 0) {
            if (sz == 0) {
                pos = 0;
            } else {
                pos = sz + pos + 1; // +1
            }
        }

        // create copy
        for (int i = 0; i < sz; i++){
            ar[i] = arr[i];
        }

        // back shift
        for (int i = sz; i > pos; i--) {
            ar[i] = ar[i-1];
        }

        // put into
        ar[pos] = item;

        arr = ar;
        sz++;
    }

    void hab(T item) {
        hab(item, -1);
    }


    // dank iterable shit

    public boolean hasNext() {
        return !(itercount >= sz);
    }

    public T next() {
        T i = arr[itercount];
        itercount++;
        return i;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        return this;
    }

}
