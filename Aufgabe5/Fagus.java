public class Fagus implements Tree {
    private Integer height;
    private float shadowLeaves;

    public Fagus(Integer height, float shadowLeaves){
        this.height = height;
        this.shadowLeaves = shadowLeaves;
    }

    public static Relation<Fagus, Fagus> relation(){ //oder <--->?
        return new FagusRelation();
    }

    @Override
    public Integer height() {
        return height;
    }

    static class FagusRelation implements Relation<Fagus, Fagus>{
        private int invoked;

        public FagusRelation(){
            invoked = 0;
        }

        @Override
        public boolean related(Fagus fagus, Fagus fagus2) {
            invoked++;
            return fagus.height > fagus2.height && fagus.shadowLeaves < fagus2.shadowLeaves;
        }

        @Override
        public int invoked() {
            return invoked;
        }
    }
}
