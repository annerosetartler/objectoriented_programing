public class Bewirtschaftet extends Bewirtschaftungsmodell {
    private float[] wirtschaftsfaktoren;

    //pre: w != null
    public Bewirtschaftet(Wald w) {
        super(w);
    }

    public float[] getWirtschaftsfaktoren() {
        return wirtschaftsfaktoren;
    }
}
