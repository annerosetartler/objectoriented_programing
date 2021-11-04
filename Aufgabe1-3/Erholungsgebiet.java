public class Erholungsgebiet extends Bewirtschaftet {
    private int AnzahlWege;
    private int AnzahlHütten;
    private int neuH;
    private int neuW;

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

        wirtschaftsfaktoren[3] = neuH * 0.0001f + neuW + 0.001f + AnzahlHütten * 0.00001f + AnzahlWege * 0.00001f;

        return super.plusEinJahr();
    }

}
