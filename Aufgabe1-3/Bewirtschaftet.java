public class Bewirtschaftet extends Bewirtschaftungsmodell {
    //pre: w != null
    public Bewirtschaftet(Wald w) {
        super(w);
    }

    public float[] getWirtschaftsfaktoren() {
        return wirtschaftsfaktoren;
    }
}
