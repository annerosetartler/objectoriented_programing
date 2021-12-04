import java.util.ArrayList;

public class Forst {
    //KOMMENTAR: Ein Forst ist ein Container, der ein oder zwei Populationen beinhaltet. Während ein Forst mit nur einer
    //           Population direkt die Methoden der entsprechenden Population aufruft und alle Berechnungen dort ausführen
    //           kann, wird im Forst sichergestellt, dass die Methoden in einem Forst mit zwei Populationen richtig berechnet
    //           und ausgeführt werden.
    //           Für die Verwaltung des Forst mit zwei Populationen wird eine Gesamt-Altersstruktur, sowie
    //           gesamt-Baumgesundheit berechnet, die dann für die jeweiligen Rechnungen verwendet werden

    //INV: population1 != null
    private final Population population1, population2;
    private ArrayList<Float> gesAS;
    private float gemBaumGes;

    //KOMMENTAR: Initialisiert einen Forst, der eine Populationsart hat
    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    //NACHB: gesAS = null & gesBaumGes = 0.0f & population2 = null
    public Forst(ArrayList<Float> as, float bB, float zB, int baumart){
        if (baumart == 0){
            population1 = new Fichte(as, bB, zB);
        }
        else {
            population1 = new Buche(as, bB, zB);
        }
        population2 = null;
    }

    //KOMMENTAR: Initialisiert einen Forst, der zwei Populationsarten hat
    //VORB: as1 != null & as1.size() > 0 & bB1 > 0 & zb1 > 0 && as2 != null & as2.size() > 0 & bB2 > 0 & zb2 > 0
    //      Summe aller Werte in as1 und as2 ergibt jeweils 1.0 & Werte in as liegen in [0.0,1.0]
    //NACHB: population1.istMischwald = true & population2.istMischwald = true
    //       gesAS != null & gesAS.size = population1.altersStruktur.size = population2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    //       Werte in gesAS berechnen sich aus denen von as1 und as2
    //       gemBaumGes in [0.25,1.0]
    //HISTORY CONSTRAINT: Die Gesundheit in den einzelnen Populationen wird auf den Wert der gesamtGesundheit geändert //ToDo: Ist das ein History Constraint?
    public Forst(ArrayList<Float> as1, float bB1, float zB1, int baumart1, ArrayList<Float> as2, float bB2, float zB2, int baumart2){
        if (baumart1 == 0){
            population1 = new Fichte(as1, bB1, zB1);
        }
        else {
            population1 = new Buche(as1, bB1, zB1);
        }
        if (baumart2 == 0){
            population2 = new Fichte(as2, bB2, zB2);
        }
        else {
            population2 = new Buche(as2, bB2, zB2);
        }

        population1.setzeMischwaldBesonderheiten();
        population2.setzeMischwaldBesonderheiten();

        gesAS = new ArrayList<Float>();
        for (int i = 0; i < population1.getAltersstruktur().size(); i++) {
            gesAS.add(0.0f);
        }
        berGesamtAS();
        setzeGesamtGesundheit();
    }

    //KOMMENTAR Initialisiert einen Forst, der eine Populationsart hat
    //VORB: p1 != null
    //NACHB: gesAS = null & baumGes = 0.0f & population2 = null
    public Forst(Population p1){
        population1 = p1;
        population2 = null;
    }

    //Initialisiert einen Forst, der zwei Populationsarten hat
    //VORB: p1 != null && p2 != null;
    //NACHB: population1.istMischwald = true & population2.istMischwald = true
    //       gesAS != null & gesAS.size = population1.altersStruktur.size = population2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    //       Werte in gesAS berechnen sich aus denen von as1 und as2
    //       gemGaumGes in [0.25,1.0]
    //HISTORY CONSTRAINT: Die Gesundheit in den einzelnen Populationen wird auf den Wert der gesamtGesundheit geändert //ToDo: Ist das ein History Constraint?
    public Forst(Population w1, Population w2){
        population1 = w1;
        population2 = w2;

        population1.setzeMischwaldBesonderheiten();
        population2.setzeMischwaldBesonderheiten();

        gesAS = new ArrayList<Float>();
        for (int i = 0; i < population1.getAltersstruktur().size(); i++) {
            gesAS.add(0.0f);
        }
        berGesamtAS();
        setzeGesamtGesundheit();
    }

    //KOMMENTAR: Aufruf der Methode plusEinJahr simuliert die Vorgänge im Forst für ein Jahr.
    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb >= 0
    //NACHB: Wenn population2 != null, dann: gesAS != null & gesAS.size = population1.altersStruktur.size = population2.altersStruktur.size
    //       & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0 & gemBaumGes in [0.25,1.0]
    //       Sonst: gesAS = null & baumGes = 0 & population2 = null
    //INVARIANTE: Wenn sich der Forst aus zwei Populationen zusammensetzt, dann erhält jede Population (in ihrer Funktion
    //            als Forsthälfte) den halben Zielbestand ToDo: ist das eine Invariante?
    //HISTORY CONSTRAINT: Die Gesundheit in den einzelnen Populationen wird auf den Wert der gesamtGesundheit geändert //ToDo: Ist das ein History Constraint?
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren,float maxZielb){

        population1.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb / (population2 == null? 1 : 2), (population2 != null));

        if (population2 != null){
            population2.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb/2, true);
            plenter(wirtschaftsfaktoren);

            berGesamtAS();
            setzeGesamtGesundheit();
        }
    }

    public String toString() {
        boolean b = population2 != null;
        String s = "Ihr Forst beinhaltet " + (b ? "zwei " : "eine ") + "Population" + (b ? "en: " : ": ") + population1.getName() +
                (b ? " und " + population2.getName() + "." : ".") + "\n";
        if (!b){
            s += population1.toString();
        }
        else {
            float[] w1Zustand = population1.zustandPop();
            float[] w2Zustand = population2.zustandPop();
            s += "Baumbestand: " + String.format("%6.2f", w1Zustand[0] + w2Zustand[0]) + "\t\tGesundheit: " + String.format("%6.2f", gemBaumGes) +
                    "\t\tZielbestand: " + String.format("%6.2f", w1Zustand[1] + w2Zustand[1]) + "\t\tErnte: " + String.format("%6.2f", w1Zustand[3] + w2Zustand[3]) +
                    "\t\tCO2-Vorrat: " + String.format("%6.2f", w1Zustand[4] + w2Zustand[4]);
        }
        return s;
    }

    //VORB: population2 != null & gesAS != null & gesAS.size = population1.altersStruktur.size = population2.altersStruktur.size
    //NACHB: gesAS != null & gesAS.size = population1.altersStruktur.size = population2.altersStruktur.size & alle Werte in gesAS in [0.0,1.0] & Summe aller Werte in gesAS ergibt 1.0
    private void berGesamtAS(){
        ArrayList<Float> popu1AS = population1.getAltersstruktur();
        ArrayList<Float> popu2AS = population2.getAltersstruktur();
        for (int i = 0; i < population1.getAltersstruktur().size(); i++) {
            gesAS.set(i, (popu1AS.get(i) * population1.getBaumbestand() + popu2AS.get(i) * population2.getBaumbestand()) / (population1.getBaumbestand() + population2.getBaumbestand()));
        }
    }

    //VORB: population2 != null & gesAS != null
    //NACHB: baumGes in [0.25,1.0] & population1.gesundheit = population2.gesundheit = baumGes
    //HISTORY CONSTRAINT: Verändert die Population der zwei Populationen durch aufruf der setGesundheit-Methode der jeweilgen Populationen //ToDo: Ist das ein History Constraint?
    private void setzeGesamtGesundheit(){
        int space = 0;
        float idealwert = 1.0f / (gesAS.size() * 2);
        for (Float f : gesAS) {
            if (f < idealwert) {
                space++;
            }
        }
        gemBaumGes = 0.25f + ((0.75f / (float) (gesAS.size())) * (float) (space));

        population1.setGesundheit(gemBaumGes);
        population2.setGesundheit(gemBaumGes);
    }

    //ToDo: Methodenbeschreibung? (DAVID)
    //VORB: wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //NACHB: wenn population1.baumBestand < als Anteil wirtschaftsfaktoren[2] des gesamten Forsts ist dann: population2.baumBestand = population1.baumbestand
    //       wenn population2.baumBestand < als Anteil wirtschaftsfaktoren[2] des gesamten Forsts ist dann: population1.baumBestand = population2.baumbestand
    //SCHLECHT: greift von hier auf protected Variable zu
    //          Es wird zu viel übergeben: nur Übergabe von wirtschaftsfaktoren[2] nötig
    private void plenter(float[] wirtschaftsfaktoren){
        float popu1BB = population1.getBaumbestand();
        float popu2BB = population2.getBaumbestand();
        if (popu1BB < (popu1BB + popu2BB) * wirtschaftsfaktoren[2]){
            population2.plenterernte((popu1BB));
        }else if (popu2BB < (popu1BB + popu2BB) * wirtschaftsfaktoren[2]){
            population1.plenterernte((popu2BB));
        }
    }
}
