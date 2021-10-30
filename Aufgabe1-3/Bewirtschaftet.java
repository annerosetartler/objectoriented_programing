public class Bewirtschaftet extends Bewirtschaftungsmodell {

    private int[] counter;

    //pre: w != null
    public Bewirtschaftet(Wald w) {
        super(w);
        counter = new int[]{0};
    }
}
