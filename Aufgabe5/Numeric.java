public class Numeric implements Relation<Integer, Integer> {
    //KOMMENTAR: Numeric ist eine Relation vom Typ Relation<Integer, Integer>
    //           zusätzlich zur Variable invoked gibt es hier noch die variable c.
    //INV: c > 0
    //     invoked >= 0
    private final int c;
    private int invoked;

    //VORB: c > 0
    public Numeric(int c) {
        this.c = c;
        invoked = 0;
    }

    @Override
    //NACHB: gibt true zurück wenn sich x und y um maximal c voneinander unterscheiden
    //       sonst false
    //HISTORY-CONSTRAINT SERVER: invoked erhöht sich mit jedem Aufruf von related() um 1
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
