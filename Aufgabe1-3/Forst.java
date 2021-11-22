import java.util.ArrayList;

public class Forst {
    //SCHLECHT: Benennungskonflikt zwischen wald (alt) und population (neu)
    //SCHLECHT: Objektvariablen haben nur in bestimmten Wäldern (= in Wäldern, in denen es einen wald2 gibt) eine Funktionalität
    //INV: wald1 != null
    private Population wald1, wald2;
    private ArrayList<Float> gesAS;
    private float baumGes;

    //KOMMENTAR: Initialisiert einen Forst, der eine Waldart hat
    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    //NACHB: gesAS = null & baumGes = 0 & wald2 = null
    public Forst(ArrayList<Float> as, float bB, float zB, int baumart){
        if (baumart == 0){
            wald1 = new Fichte(as, bB, zB);
        }
        else {
            wald1 = new Buche(as, bB, zB);
        }
        wald2 = null;
    }

    //KOMMENTAR: Initialisiert einen Forst, der zwei Waldarten hat
    //VORB: as1 != null & as1.size() > 0 & bB1 > 0 & zb1 > 0 && as2 != null & as2.size() > 0 & bB2 > 0 & zb2 > 0
    //      Summe aller Werte in as1 und as2 ergibt jeweils 1.0 & Werte in as liegen in [0.0,1.0]
    //NACHB: wald1.istMischwald = true & wald2.istMischwald = true
    //       gesAS != null & gesAS.size = wald1.altersStruktur.size = wald2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    //       baumGes in [0.25,1.0]
    public Forst(ArrayList<Float> as1, float bB1, float zB1, int baumart1, ArrayList<Float> as2, float bB2, float zB2, int baumart2){
        if (baumart1 == 0){
            wald1 = new Fichte(as1, bB1, zB1);
        }
        else {
            wald1 = new Buche(as1, bB1, zB1);
        }
        if (baumart2 == 0){
            wald2 = new Fichte(as2, bB2, zB2);
        }
        else {
            wald2 = new Buche(as2, bB2, zB2);
        }

        wald1.setzeMischwaldVar();
        wald2.setzeMischwaldVar();

        gesAS = new ArrayList<Float>();
        for (int i = 0; i < wald1.getAltersstruktur().size(); i++) {
            gesAS.add(0.0f);
        }
        berGesamtAS();
        setzeGesamtGesundheit();
    }

    //KOMMENTAR Initialisiert einen Forst, der eine Waldart hat
    //VORB: w1 != null
    //NACHB: gesAS = null & baumGes = 0 & wald2 = null
    public Forst(Population w1){
        wald1 = w1;
        wald2 = null;
    }

    //Initialisiert einen Forst, der zwei Waldarten hat
    //VORB: w1 != null && w2 != null;
    //NACHB: wald1.istMischwald = true & wald2.istMischwald = true
    //       gesAS != null & gesAS.size = wald1.altersStruktur.size = wald2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    //       baumGes in [0.25,1.0]
    public Forst(Population w1, Population w2){
        wald1 = w1;
        wald2 = w2;

        wald1.setzeMischwaldVar();
        wald2.setzeMischwaldVar();

        gesAS = new ArrayList<Float>();
        for (int i = 0; i < wald1.getAltersstruktur().size(); i++) {
            gesAS.add(0.0f);
        }
        berGesamtAS();
        setzeGesamtGesundheit();
    }

    //KOMMENTAR: wenn es zwei Waldteile gibt, dann erhält jeder Waldteil den halben Zielbestand
    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb >= 0
    //NACHB: Wenn wald2 != null, dann: gesAS != null & gesAS.size = wald1.altersStruktur.size = wald2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    //                                 baumGes in [0.25,1.0]
    //       Sonst: gesAS = null & baumGes = 0 & wald2 = null
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren,float maxZielb){

        wald1.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb / (wald2 == null? 1 : 2), (wald2 != null));

        if (wald2 != null){
            wald2.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb/2, true);
            plenter(wirtschaftsfaktoren);

            berGesamtAS();
            setzeGesamtGesundheit(); //KOMMENTAR: Gesundheit, die für die einzelnen Teile gelten würde, wird hier überschrieben!!!
        }
    }

    //ERROR: zielbestand wird im Fall eines Mischwalds im 0ten Jahr doppelt so hoch angezeigt
    public String toString() {
        boolean b = wald2 != null;
        String s = "Ihr Forst beinhaltet " + (b ? "zwei " : "eine ") + "Population" + (b ? "en: " : ": ") + wald1.getName() +
                (b ? " und " + wald2.getName() + "." : ".") + "\n";
        if (!b){
            s += wald1.toString();
        }
        else {
            //ToDo: hierfür evtl. stattdessen eine Methode schreiben?
            float[] w1Zustand = wald1.zustandPop();
            float[] w2Zustand = wald2.zustandPop();
            s += "Baumbestand: " + String.format("%6.2f", w1Zustand[0] + w2Zustand[0]) + "\t\tGesundheit: " + String.format("%6.2f", baumGes) +
                    "\t\tZielbestand: " + String.format("%6.2f", w1Zustand[1] + w2Zustand[1]) + "\t\tErnte: " + String.format("%6.2f", w1Zustand[3] + w2Zustand[3]) +
                    "\t\tCO2-Vorrat: " + String.format("%6.2f", w1Zustand[4] + w2Zustand[4]);
        }
        return s;
    }

    //VORB: wald2 != null & gesAS != null & gesAS.size = wald1.altersStruktur.size = wald2.altersStruktur.size
    //NACHB: gesAS != null & gesAS.size = wald1.altersStruktur.size = wald2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    private void berGesamtAS(){
        ArrayList<Float> wald1AS = wald1.getAltersstruktur();
        ArrayList<Float> wald2AS = wald2.getAltersstruktur();
        for (int i = 0; i < wald1.getAltersstruktur().size(); i++) {
            gesAS.set(i, (wald1AS.get(i) * wald1.getBaumbestand() + wald2AS.get(i) * wald2.getBaumbestand()) / (wald1.getBaumbestand() + wald2.getBaumbestand()));
        }
    }

    //VORB: wald2 != null & gesAS != null
    //NACHB: baumGes in [0.25,1.0] & wald1.gesundheit = wald2.gesundheit = baumGes
    //SCHLECHT: gesundheit der einzelnen populationen wird hier "von außen" verändert
    private void setzeGesamtGesundheit(){
        int space = 0;
        float idealwert = 1.0f / (gesAS.size() * 2);
        for (Float f : gesAS) {
            if (f < idealwert) {
                space++;
            }
        }
        baumGes = 0.25f + ((0.75f / (float) (gesAS.size())) * (float) (space));

        wald1.setGesundheit(baumGes);
        wald2.setGesundheit(baumGes);
    }

    //VORB: wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //NACHB: wenn wald1.baumBestand < als Anteil wirtschaftsfaktoren[2] des gesamten Waldes ist dann: wald2.baumBestand = wald1.baumbestand
    //       wenn wald2.baumBestand < als Anteil wirtschaftsfaktoren[2] des gesamten Waldes ist dann: wald1.baumBestand = wald2.baumbestand
    //SCHLECHT: greift von hier auf protected Variable zu
    //          Es wird zu viel übergeben: nur Übergabe von wirtschaftsfaktoren[2] nötig
    private void plenter(float[] wirtschaftsfaktoren){
        float wald1BB = wald1.getBaumbestand();
        float wald2BB = wald2.getBaumbestand();
        if (wald1BB < (wald1BB + wald2BB) * wirtschaftsfaktoren[2]){
            wald2.plenterernte((wald1BB));
        }else if (wald2BB < (wald1BB + wald2BB) * wirtschaftsfaktoren[2]){
            wald1.plenterernte((wald2BB));
        }
    }
}
