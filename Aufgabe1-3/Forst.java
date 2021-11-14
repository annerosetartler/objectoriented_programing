import java.util.ArrayList;

public class Forst {
    //SCHLECHT: Benennungskonflikt zwischen wald (alt) und population (neu)
    //INV: ToDo: Hier einfach nichts, oder?
    //      Es wäre ein: Wenn as != null: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //                  dann auch Wert für baumGes in [0.25,1.0], sonst 0.
    private Population wald1, wald2;
    private ArrayList<Float> gesAS;
    private float baumGes;

    //KOMMENTAR: Initialisiert einen Forst, der eine Waldart hat
    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
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
    //NACHB: istMischwald == true in wald1 und wald2 //ToDo sind das Nachbedingungen?
    //      Werte in gesAS in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //      Wert für gesamtGesundheit in [0.25,1.0]
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
        for (int i = 0; i < wald1.altersStruktur.size(); i++) {
            gesAS.add(0.0f);
        }
        berGesamtAS();
        setzeGesamtGesundheit();
    }

    //KOMMENTAR Initialisiert einen Forst, der eine Waldart hat
    //VORB: w1 != null;
    public Forst(Population w1){
        wald1 = w1;
        wald2 = null;
    }

    //Initialisiert einen Forst, der zwei Waldarten hat
    //VORB: w1 != null && w2 != null;
    //NACHB: istMischwald == true in wald1 und wald2 //ToDo: Sind das Nachbedingungen?
    //      Werte in gesAS in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //      Wert für gesamtGesundheit in [0.25,1.0]
    public Forst(Population w1, Population w2){
        wald1 = w1;
        wald2 = w2;

        wald1.setzeMischwaldVar();
        wald2.setzeMischwaldVar();

        gesAS = new ArrayList<Float>();
        for (int i = 0; i < wald1.altersStruktur.size(); i++) {
            gesAS.add(0.0f);
        }
        berGesamtAS();
        setzeGesamtGesundheit();
    }

    //KOMMENTAR: ich gebe derzeit hier jedem den halben zielbestand, wenn es zwei Waldteile gibt
    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb >= 0
    //INVAR: Werte in gesAS in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0 //ToDo ist das jetzt eine Invariante?
    //       Wert für gesamtGesundheit in [0.25,1.0]
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren,float maxZielb){

        wald1.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb / (wald2 == null? 1 : 2), (wald2 != null));

        if (wald2 != null){
            wald2.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb/2, true);
            plenter(wirtschaftsfaktoren);

            berGesamtAS();
            setzeGesamtGesundheit(); //Gesundheit, die für die einzelnen Teile gelten würde, wird hier überschrieben!!!
        }
    }

    public String toString() {
        boolean b = wald2 != null;
        String s = "Ihr Forst beinhaltet " + (b ? "zwei " : "eine ") + "Population" + (b ? "en: " : ": ") + wald1.getName() +
                (b ? " und " + wald2.getName() + "." : ".") + "\n";
        if (!b){
            s += wald1.toString();
        }
        else {
            s += "Baumbestand: " + String.format("%6.2f", wald1.baumBestand + wald2.baumBestand) + "\t\tGesundheit: " + String.format("%6.2f", baumGes) +
                    "\t\tZielbestand: " + String.format("%6.2f", wald1.zielbestand + wald2.zielbestand) + "\t\tErnte: " + String.format("%6.2f", wald1.ernte + wald2. ernte) +
                    "\t\tCO2-Vorrat: " + String.format("%6.2f", wald1.co2Vorrat + wald2.co2Vorrat);
        }
        return s;
    }

    //INVAR: Werte in gesAS in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0 //ToDo ist das jetzt eine Invariante?
    private void berGesamtAS(){
        for (int i = 0; i < wald1.altersStruktur.size(); i++) {
            gesAS.set(i, (wald1.altersStruktur.get(i) * wald1.baumBestand + wald2.altersStruktur.get(i) * wald2.baumBestand) / (wald1.baumBestand + wald2.baumBestand));
        }
    }

    //INVAR: Wert für gesamtGesundheit in [0.25,1.0]
    //SCHLECHT: gesundheit der einzelnen populationen wird hier "von außen" verändert //ToDo: SO schlecht? Wüsse nicht, wie anders...
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

    //ToDo: Stimmt das (Vorb) überhaupt? Ist das mit dem Zugriff separat als schlecht zu nennen?
    //VORB: wirtschaftsfaktoren.length > 3 & Wert in wirtschaftsfaktoren[2] [0.0,1.0]
    //SCHLECHT: greift von hier auf protected Variable zu. VERBESSERUNG: getter
    private void plenter(float[] wirtschaftsfaktoren){
        if (wald1.baumBestand < wald1.baumBestand + wald2.baumBestand * wirtschaftsfaktoren[2]){
            wald2.plenterernte((wald1.baumBestand));
        }else if (wald2.baumBestand < wald1.baumBestand + wald2.baumBestand * wirtschaftsfaktoren[2]){
            wald1.plenterernte((wald2.baumBestand));
        }
    }

}
