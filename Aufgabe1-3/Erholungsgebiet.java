public class Erholungsgebiet extends Bewirtschaftet {
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0
    private int AnzahlWege;
    private int AnzahlHütten;
    private int neuH;
    private int neuW;

    //Vorb: altersKlassen > 0
    public Erholungsgebiet(int altersKlassen) {
        super(altersKlassen);
    }


    public float[] plusEinJahr() {
        neuH = 0;
        neuW = 0;
        if (AnzahlWege == 0){
            AnzahlWege++;
            neuW++;
        }else if (Math.random() > 0.95){
            wirtschaftsfaktoren[3] = 1;
            AnzahlWege++;
            neuW++;
        }
        if (Math.random() > 0.98){
            AnzahlHütten++;
            neuH++;
        }

        wirtschaftsfaktoren[3] = neuH * 0.001f + neuW + 0.01f + AnzahlHütten * 0.0001f + AnzahlWege * 0.0001f;

        return super.plusEinJahr();
    }

}
