public class Kahlschlag extends Bewirtschaftet {
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0
    private int[] Zähler;

    //Vorb: altersKlassen > 0
    public Kahlschlag(int altersKlassen) {
        super(altersKlassen);
        Zähler = new int[]{0};
    }

    public float[] plusEinJahr(){
        if (Zähler[0] == 0){
            wirtschaftsfaktoren[0] = 0;
            Zähler[0]++;
        } else if (Zähler[0] == 11){
            wirtschaftsfaktoren[0] = 1;
            Zähler[0] = 0;
        }else{
            Zähler[0]++;
        }
        return super.plusEinJahr();
    }

}
