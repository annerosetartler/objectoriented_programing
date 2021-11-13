public class Plenterwirtschaft extends Bewirtschaftet {
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0


    //Vorb: Altersklassen > 0
    public Plenterwirtschaft(int altersKlassen) {
        super(altersKlassen);
    }


    public float[] plusEinJahr(){
        wirtschaftsfaktoren[1] = 1.0f;
        wirtschaftsfaktoren[2] = 0.45f;
        return super.plusEinJahr();
    }
}
