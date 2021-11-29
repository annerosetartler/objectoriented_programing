public class Fagus implements Tree {
    //KOMMENTAR: Fagus ist ein Untertyp von Tree.
    //           Fagus hat zusätzlich zur Baumhöhe auch noch eine Variable Schattenblätter
    //INV: height > 0
    //     shadowLeaves > 0 & shadowLeaves < 1.0
    private Integer height;
    private float shadowLeaves;

    //VORB: height > 0
    //      shadowLeaves > 0 & shadowLeaves < 1.0
    public Fagus(Integer height, float shadowLeaves) {
        this.height = height;
        this.shadowLeaves = shadowLeaves;
    }

    //NACHB: gibt ein neues Objekt von Relation<Fagus, Fagus> zurück
    public static Relation<Fagus, Fagus> relation() {
        return new FagusRelation();
    }

    @Override
    //NACHB: gibt height zurück
    public Integer height() {
        return height;
    }

    public String toString() {
        return "Fagus(" +
                "height=" + height +
                ", shadowLeaves=" + shadowLeaves +
                ')';
    }

    static class FagusRelation implements Relation<Fagus, Fagus> {
        //KOMMENTAR: FagusRelation ist ein Objekt von Relation<Fagus, Fagus>
        //           Es vergleicht zwei Objekte vom Typ Fagus
        //INV: invoked >= 0
        private int invoked;

        public FagusRelation() {
            invoked = 0;
        }

        @Override
        //VORB: fagus != null & fagus2 != null
        //NACHB: Gibt true zurück, wenn fagus.height größer ist als fagus2.height
        //       und fagus.shadowLeaves kleiner ist als fagus2.shadowleaves
        //       sonst false
        //HISTORY-CONSTRAINT SERVER: invoked erhöht sich mit jedem Aufruf von related() um 1
        public boolean related(Fagus fagus, Fagus fagus2) {
            invoked++;
            return fagus.height > fagus2.height && fagus.shadowLeaves < fagus2.shadowLeaves;
        }

        @Override
        //NACHB: Gibt invoked zurück
        public int invoked() {
            return invoked;
        }
    }
}
