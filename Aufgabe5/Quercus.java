public class Quercus implements Tree {
    private Integer height;
    private Integer trunkHeight;

    public Quercus(Integer height, Integer trunkHeight){
        this.height = height;
        this.trunkHeight = trunkHeight;
    }

    public static Relation<? extends Quercus, ? extends Tree> relation(){ //oder <--->?
        return new QuercusRelation();
    }

    @Override
    public Integer height() {
        return height;
    }

    public Integer trunkHeight() {
        return trunkHeight;
    }

    static class QuercusRelation <A extends Quercus, B extends Tree> implements Relation<A, B>{
        private int invoked;

        public QuercusRelation(){
            invoked = 0;
        }

        @Override
        public boolean related(A quercus, B tree) {
            invoked++;
            return quercus.trunkHeight() >= tree.height();
        }

        @Override
        public int invoked() {
            return invoked;
        }
    }
}
//kommentar