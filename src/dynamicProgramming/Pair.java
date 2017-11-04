package dynamicProgramming;

/**
 * Created by Victor on 2017-05-22.
 */
public class Pair {
    private final Object o1;

    private final Object o2;
    private Pair(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public static Pair pair(Object o1, Object o2){
        return new Pair(o1, o2);
    }

    public Object getFirst() {
        return o1;
    }

    public Object getSecond() {
        return o2;
    }


}
