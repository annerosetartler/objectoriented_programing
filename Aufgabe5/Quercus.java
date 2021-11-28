public class Quercus implements Tree {
    //KOMMENTAR: Quercus ist ein Untertyp von Tree.
    //INV: height > 0
    //     trunkHeight > 0
    private Integer height;
    private Integer trunkHeight;

    //VORB: height > 0
    //      trunkHeight > 0
    public Quercus(Integer height, Integer trunkHeight){
        this.height = height;
        this.trunkHeight = trunkHeight;
    }

    //NACHB: gibt ein neues Objekt von Relation<Quercus, tree> zurück
    public static Relation<Quercus, Tree> relation(){ //oder <--->?
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

    static class QuercusRelation implements Relation<Quercus, Tree>{
        //KOMMENTAR: Diese Klasse ist eine Hilfsklasse
        //INV: invoked >= 0
        private int invoked;

        public QuercusRelation(){
            invoked = 0;
        }

        @Override
        //VORB: fagus != null & fagus2 != null
        //NACHB: gibt true zurück wenn quercus.trunkheight minestens so groß wie tree.height ist
        //       sonst false
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
