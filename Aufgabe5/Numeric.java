import java.lang.Integer;

public class Numeric<Integer> implements Relation<Integer, Integer> {
    private float c;
    private int invoked;

    public Numeric(float c){
        this.c = c;
        invoked = 0;
    }

    @Override
    public boolean related(Integer x, Integer y) {
        invoked++;
        return Math.abs() < c;
    }

    @Override
    public int invoked() {
        return invoked;
    }
}
