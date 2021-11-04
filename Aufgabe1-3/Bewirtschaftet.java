public class Bewirtschaftet extends Bewirtschaftungsmodell {
    //pre: w != null
    public Bewirtschaftet(int altersKlassen) {
        super(altersKlassen);
    }

    public float[] getWirtschaftsfaktoren() {
        return wirtschaftsfaktoren;
    }
}
