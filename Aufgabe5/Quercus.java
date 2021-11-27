public class Quercus implements Tree {
    private Integer height;
    private Integer trunkHeight;

    public Quercus(Integer height, Integer trunkHeight){
        this.height = height;
        this.trunkHeight = trunkHeight;
    }

    public static Relation<Quercus, Tree> relation(){ //oder <--->?
        return new QuercusRelation();
    }

    @Override
    public Integer height() {
        return height;
    }

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
        private int invoked;

        public QuercusRelation(){
            invoked = 0;
        }

        @Override
        public boolean related(Quercus quercus, Tree tree) {
            invoked++;
            return quercus.trunkHeight() >= tree.height();
        }

        @Override
        public int invoked() {
            return invoked;
        }
    }
}
