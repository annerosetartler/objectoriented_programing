public class Numeric implements Relation<Integer, Integer> {
    private int c;
    private int invoked;

    public Numeric(int c){
        this.c = c;
        invoked = 0;
    }

    @Override
    public boolean related(Integer x, Integer y) {
        invoked++;
        return Math.abs(x - y) <= c;
    }

    @Override
    public int invoked() {
        return invoked;
    }
}
