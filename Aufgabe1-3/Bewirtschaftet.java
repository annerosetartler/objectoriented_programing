public class Bewirtschaftet extends Bewirtschaftungsmodell {
    //pre: w != null
    public Bewirtschaftet(Population w) {
        super(w);
    }

    public float[] getWirtschaftsfaktoren() {
        return wirtschaftsfaktoren;
    }
}
