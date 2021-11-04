public class Plenterwirtschaft extends Bewirtschaftet {

    //pre: w != null
    public Plenterwirtschaft(Population w) {
        super(w);

    }

    public float[] plusEinJahr(){
        wirtschaftsfaktoren[1] = 100.0f/250.0f;
        wirtschaftsfaktoren[2] = 0.45f;
        return super.plusEinJahr();
    }

}
