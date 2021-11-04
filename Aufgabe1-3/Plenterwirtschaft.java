public class Plenterwirtschaft extends Bewirtschaftet {

    //pre: w != null
    public Plenterwirtschaft(int altersKlassen) {
        super(altersKlassen);
    }

    public float[] plusEinJahr(){
        wirtschaftsfaktoren[1] = 1 / (float) altersKlassen;
        wirtschaftsfaktoren[2] = 0.45f;
        return super.plusEinJahr();
    }

}
