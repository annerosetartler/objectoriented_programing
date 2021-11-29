public class Quercus implements Tree {
    //KOMMENTAR: Quercus ist ein Untertyp von Tree.
    //           zusätzlich zur Baumhöhe gibt es auch die Variable Stammhöhe (= trunkHeight).
    //INV: height > 0
    //     trunkHeight > 0
    //     trunkHeight < height
    private Integer height;
    private Integer trunkHeight;

    //VORB: height > 0
    //      trunkHeight > 0
    //      trunkHeight < height
    public Quercus(Integer height, Integer trunkHeight) {
        this.height = height;
        this.trunkHeight = trunkHeight;
    }

    //NACHB: gibt ein neues Objekt von Relation<Quercus, Tree> zurück
    public static Relation<Quercus, Tree> relation() {
        return new QuercusRelation();
    }

    @Override
    //NACHB: gibt height zurück
    public Integer height() {
        return height;
    }

    //NACHB: gibt trunkHeight zurück
    public Integer trunkHeight() {
        return trunkHeight;
    }

    @Override
    public String toString() {
        return "Quercus(" +
                "height=" + height +
                ", trunkHeight=" + trunkHeight +
                ')';
    }

    private static class QuercusRelation implements Relation<Quercus, Tree> {
        //KOMMENTAR: QuercusRelation ist ein Objekt von Relation<Quercus, Tree>
        //           Es vergleicht ein Objekt vom Typ Quercus mit einem anderen von Typ Tree
        //INV: invoked >= 0
        private int invoked;

        private QuercusRelation() {
            invoked = 0;
        }

        @Override
        //VORB: fagus != null & fagus2 != null
        //NACHB: gibt true zurück wenn quercus.trunkheight mindestens so groß wie tree.height ist
        //       sonst false
        //HISTORY-CONSTRAINT SERVER: invoked erhöht sich mit jedem Aufruf von related() um 1
        public boolean related(Quercus quercus, Tree tree) {
            invoked++;
            return quercus.trunkHeight() >= tree.height();
        }

        @Override
        //NACHB: Gibt invoked zurück
        public int invoked() {
            return invoked;
        }
    }
}
