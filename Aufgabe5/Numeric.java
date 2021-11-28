public class Numeric implements Relation<Integer, Integer> {
    //KOMMENTAR: die Klasse Numeric implementiert Relation<Integer, Integer>
    //INV: c > 0
    //     invoked >= 0
    private int c;
    private int invoked;

    //VORB: c > 0
    public Numeric(int c) {
        this.c = c;
        invoked = 0;
    }

    @Override
    //NACHB: gibt true zurück wenn sich x und y um maximal c unterscheiden
    //       sonst false
    public boolean related(Integer x, Integer y) {
        invoked++;
        return Math.abs(x - y) <= c;
    }

    @Override
    //NACHB: gibt invoked zurück
    public int invoked() {
        return invoked;
    }
}
