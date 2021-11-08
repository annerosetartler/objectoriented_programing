import java.util.ArrayList;

public class Population {

    //Waldparameter
    protected float baumBestand;//in Festmetern fm
    protected ArrayList<Float> altersStruktur;// jeder Index repräsentiert ein Alter und jeder Eintrag den Anteil der Bäume dieses Alters; Wertebereich = [0.0,1.0]
    protected float gesundheit; //wertebereich [0.25,1.0], klein besser
    protected float zielbestand;//in Festmetern fm
    protected float ernte; //in Festmetern fm
    protected float co2Vorrat; //in Tonnen t
    protected float ausfall;// in %
    protected float zuwachs;// in Festmetern fm
    protected boolean istMischwald;

    // erzeugt Wald im Anfangszustand
    //pre: as != null & as.size() > 0 & bB > 0 & zb > 0
    //pre: Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    protected Population(ArrayList<Float> as, float bB, float zb) {
        altersStruktur = as;
        baumBestand = bB;
        zielbestand = zb;
        berGesundheit();
        ernte = 0.0f;
        co2Vorrat = baumBestand;
        ausfall = 0.0f;
        zuwachs = 0.0f;
        istMischwald = false;
    }

    //erzeugt ein Objekt von Wald als tiefe Kopie von w
    //pre: w != null
    protected Population(Population w) {
        this.baumBestand = w.baumBestand;
        altersStruktur = new ArrayList<Float>();
        for (int i = 0; i < w.altersStruktur.size(); i++) {
            float f = w.altersStruktur.get(i);
            altersStruktur.add(f);
        }
        this.gesundheit = w.gesundheit;
        this.zielbestand = w.zielbestand;
        this.ernte = w.ernte;
        this.co2Vorrat = w.co2Vorrat;
        this.ausfall = w.ausfall;
        this.zuwachs = w.zuwachs;
        istMischwald = false;
    }

    //Wird von der Simulation aus mit den zwei zuvor berechneten Arrays aufgerufen und regelt den Alterungsprozess
    //des Waldes um ein Jahr unter Berücksichtigung der hinzukommenden Katastrophen/des Zuwachses und der angewandten
    //Wirtschaftsstrategie
    //Der Wald "weiß" also nicht mehr genau, welches Modell an ihm ausgeübt wird, sondern nur, was tatsächlich
    //getan werden muss

    //pre: alle Stellen des einflussArray [0.0, 1.0], wirtschaftsfaktoren[0] ist 0 oder 1, wirtschaftsfaktoren[1] bis [3] e [0.0, 1.0]  & maxZielb > 0
    //post: gesundheit e [0.25,1.0] & baumBestand >= 0 & zielbestand > 0 & co2Vorrat >= 0
    // & altersStruktur != null & altersStruktur.size() > 0
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb, boolean istMischwald) {
        //Alle Bewirtschaftungsmodelle tun:
        berAusfall(einflussArray);
        berZuwachs(einflussArray[3]);
        updateBaumbestand();
        altersStrukturPlusEinJahr();
        berGesundheit(); //VORSICHT: Diese Gesundheit kann für Mischwälder nicht verwendet werden, sondern wird vor Beginn des neuen Jahres vom Forst überschrieben!!!
        updateZielbestand(maxZielb);
        berCO2();
        this.istMischwald = istMischwald;

        //Wenn das Modell eine Ernte beinhaltet passiert zusätzlich
        if (wirtschaftsfaktoren[0] != 0.0f || wirtschaftsfaktoren[3] != 0.0f || wirtschaftsfaktoren[1] != 0.0f || wirtschaftsfaktoren[2] != 0.0f) {
            ernteBew(einflussArray, wirtschaftsfaktoren, maxZielb); //hab hier den counter raus gegeben, der gehört ins Modell
            berCO2();
        }
    }

    //errechnet die Gesundheit des Waldes aus seiner Altersstruktur:
    //ausgegangen wird von einer Gleichverteilung als Idealzustand (= jedes Alter ist mit gleichem Anteil vertreten.)
    //als Lücken werden jene Bereiche interpretiert, die weniger als die Hälfte des erwarteten Werts erreichen
    //entsprechend der Anzahl der Lücken wird die Gesundheit zwischen 0.25 und 1.0 errechnet
    //pre: altersStruktur != null & altersStruktur.size() > 0
    //post: gesundheit e [0.25,1.0] & altersStruktur != null & altersStruktur.size() > 0
    private void berGesundheit() {
        int space = 0;
        float idealwert = 1.0f / (altersStruktur.size() * 2);
        for (Float f : altersStruktur) {
            if (f < idealwert) {
                space++;
            }
        }
        gesundheit = 0.25f + ((0.75f / (float) (altersStruktur.size())) * (float) (space));
    }

    //pre: einflussArray jeweils zwischen [0.0, 1.0] & ausfall >= 0.0f
    //inv: gesundheit e [0.25,1.0]
    //post: ausfall >= 0.0f
    private void berAusfall(float[] einflussArray) { //da ich noch unsicher bin, was man braucht für die Ausfall-Calculation
        ausfall = berAusfallsfaktor(einflussArray) * gesundheit;
    }

    //Wie in UE1: Ausfallfaktor meist zwischen 0 und 0.08, mit Durchschnitt 0.04
    //Methode hier in Oberklasse wird nie aufgerufen werden
    protected float berAusfallsfaktor(float[] einflussArray) {
        return 0.0f;
    }

    //pre: einflussArray.length == 4
    protected float ausfallHilfe(float[] einflussArray){
        int zähler = 0;
        for (int i = 0; i < einflussArray.length - 1; i++) {
            if (einflussArray[i] == 1.0f){
                zähler++;
            }
        }
        float durchschnitt = (einflussArray[0] + einflussArray[1] + einflussArray[2]) / 3.0f;
        switch (zähler){
            case 0: return durchschnitt * 0.08f;
            case 1:
            case 2:
                return durchschnitt * 0.6f;
            default: return durchschnitt;
        }
    }

    //pre zfaktor e [0.0,0.08] & ausfall >= 0.0f
    //inv: baumBestand & zielbestand bleiben unverändert
    protected void berZuwachs(float zFaktor) {
        zuwachs = zielbestand * zFaktor * 0.08f - ausfall * baumBestand; //zFaktor nochmal anschauen!!
    }

    //post:baumBestand >= 0
    private void updateBaumbestand() {
        baumBestand += zuwachs;
    }

    //simuliert das Älterwerden der Bäume im Wald
    //pre: altersStruktur != null & altersStruktur.size() > 0
    //inv: altersStruktur.size() bleibt unverändert & Summe aller Einträge ergibt 1.0
    private void altersStrukturPlusEinJahr() {
        altersStruktur.remove(altersStruktur.size() - 1);
        float sumBestand = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            float f = altersStruktur.get(i);
            f *= (1.0f - ausfall);
            sumBestand += f;
            altersStruktur.set(i, f);
        }

        altersStruktur.add(0, 1.0f - sumBestand);
    }

    //der zielbestand wird abhängig vom ausfall aktualisiert
    //pre: maxZielb >= 0 & ausfall >= 0.0
    //inv: zielbestand >= 0
    private void updateZielbestand(float maxZielb) {
        if (ausfall >= 0.3) {
            zielbestand *= (1 - ausfall);
        } else if (Float.compare(zielbestand, maxZielb - 5) <= 0) {
            zielbestand += 5.0f;
        } else {
            zielbestand = maxZielb;
        }
    }

    //inv: baumBestand & ausfall bleiben unverändert
    protected void berCO2() {
        co2Vorrat += zuwachs;
        if (ausfall < 0.3) {
            co2Vorrat += baumBestand * ausfall / 3;
        } else {
            co2Vorrat *= (1 - (ausfall / 2));
        }
    }


    private void ernteBew(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb) {
        if (wirtschaftsfaktoren[0] == 1) {
            float sumAnteil = updateAltersstruktur((int)(altersStruktur.size()/2.0f));
            ernte += (sumAnteil * baumBestand); //Alles wird aus dem Wald genommen, also kein Ausfall*Baumbestand mehr
            updateBaumGesAusfall(sumAnteil, einflussArray, maxZielb);
            return;
        }
        if (wirtschaftsfaktoren[1] != 0) {
            float sumBestand = 0.0f;
            for (int i = 0; i < altersStruktur.size()-1; i++ ){
                if (altersStruktur.get(i) < altersStruktur.get(i+1) * 1.5f){
                    sumBestand += altersStruktur.get(i+1) * 0.02f;
                    altersStruktur.set(i+1, altersStruktur.get(i+1) * 0.98f);
                }
            }
            ernte += sumBestand * baumBestand;
            altersStruktur.set(0, altersStruktur.get(0) + sumBestand);
        }
        if (wirtschaftsfaktoren[3] != 0) {
            baumBestand = baumBestand - (baumBestand * wirtschaftsfaktoren[3]);
            ernte += baumBestand * wirtschaftsfaktoren[3];
        }

    }

    public void plenterernte(float neuerbaumbestand){
        ernte += baumBestand - neuerbaumbestand;
        this.baumBestand = neuerbaumbestand;
    }

    //Hier werden alle Bäume ab (inkl.) einem bestimmten Alter (=limitAge) gefällt
    //der Gesamtanteil der gefällten Bäume wird zurückgegeben
    //pre: limitAge >= 0
    //inv: altersStruktur.size() bleibt unverändert
    //post: sA e [0.0,1.0]
    private float updateAltersstruktur(int alterslimit) {
        float sA = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            if (i >= alterslimit) {
                sA += altersStruktur.get(i);
                altersStruktur.set(i, 0.0f);
            }
        }
        return sA;
    }

    //Baumbestand, Gesundheit, Ausfall und Zielbestand werden gemäß einem anteiligen Wegfall aktualisiert
    //pre: wegfall e [0.0,1.0] & afaktor e [0.0,1.0] & maxZielb >= 0
    private void updateBaumGesAusfall(float wegfall, float[] einflussArray, float maxZielb) {
        baumBestand -= wegfall * baumBestand;
        berGesundheit();
        berAusfall(einflussArray);
        updateZielbestand(maxZielb);
    }

    public void setGesundheit(float gesundheit) {
        this.gesundheit = gesundheit;
    }

    public void setzeMischwaldVar(){
        istMischwald = true;
    }

    @Override
    // gibt Zustand des Waldes aus
    //die Ausgabe der Altersstruktur wurde zum Zweck der Leserlichkeit auskommentiert
    public String toString() {
        String s = "Baumbestand: " + String.format("%6.2f", baumBestand) + "\t\tGesundheit: " + String.format("%6.2f", gesundheit) +
                "\t\tZielbestand: " + String.format("%6.2f", zielbestand) + "\t\tErnte: " + String.format("%6.2f", ernte) +
                "\t\tCO2-Vorrat: " + String.format("%6.2f", co2Vorrat);
        return s;
    }

    //zum testen
    public float[] zustandPop(){
        float[] zustand = new float[]{baumBestand,zielbestand,gesundheit,ernte,co2Vorrat,zuwachs};
        return zustand;
    }

    public String getName(){
        return "Fichten";
    }
}
