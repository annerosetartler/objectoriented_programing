import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wald {

    //Waldparameter
    private float baumBestand;//in Festmetern fm
    private ArrayList<Float> altersStruktur;// jeder Index repräsentiert ein Alter und jeder Eintrag den Anteil der Bäume diesen Alters; Wertebereich = [0.0,1.0]
    private float gesundheit; //wertebereich [0.25,1.0], klein besser
    private float zielbestand;//in Festmetern fm
    private float ernte; //in Festmetern fm
    private float co2Vorrat; //in Tonnen t
    private float ausfall;// in %
    private float zuwachs;// in Festmetern fm

    // erzeugt Wald im Anfangszustand
    //pre: as != null & as.size() > 0 & bB > 0 & zb > 0
    //pre: Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    public Wald(ArrayList<Float> as, float bB, float zb) {
        altersStruktur = as;
        baumBestand = bB;
        zielbestand = zb;
        calcGesundheit();
        ernte = 0.0f;
        co2Vorrat = baumBestand;
        ausfall = 0.0f;
        zuwachs = 0.0f;
    }

    //erzeugt ein Objekt von Wald als tiefe Kopie von w
    //pre: w != null
    public Wald(Wald w) {
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
    }

    //simuliert alle Veränderungen in einem Wald ohne Ernte nach 1 Jahr
    //pre: afaktor e [0.0,1.0] & zfaktor e [0.0,0.08] & maxZielb > 0
    //post: gesundheit e [0.25,1.0] & baumBestand >= 0 & zielbestand > 0 & co2Vorrat >= 0 & altersStruktur != null & altersStruktur.size() > 0
    public void annualCalcNat(float afaktor, float zfaktor, float maxZielb) {
        calcAusfall(afaktor);
        calcZuwachs(zfaktor);
        updateBaumbestand();
        altersStrukturPlusOneYear();
        calcGesundheit();
        updateZielbestand(maxZielb);
        calcCO2();
    }

    //simuliert alle Veränderungen in einem Wald mit Ernte nach 1 Jahr
    //pre: afaktor e [0.0,1.0] & zfaktor e [0.0,0.08] & maxZielb > 0 & c != null & c.length > 0
    //post: gesundheit e [0.25,1.0] & baumBestand >= 0 & zielbestand > 0 & co2Vorrat >= 0 & altersStruktur != null & altersStruktur.size() > 0
    public void annualCalcBew(float afaktor, float zfaktor, float maxZielb, int[] c) {
        int count = c.length;
        annualCalcNat(afaktor, zfaktor, maxZielb);
        ernteBew(afaktor,c,maxZielb);
        calcCO2();
    }

    //errechnet die Gesundheit des Waldes aus seiner Altersstruktur:
    //ausgegangen wird von einer Gleichverteilung als Idealzustand (= jedes Alter ist mit gleichem Anteil vertreten.)
    //als Lücken werden jene Bereiche interpretiert, die weniger als die Hälfte des erwarteten Werts erreichen
    //entsprechend der Anzahl der Lücken wird die Gesundheit zwischen 0.25 und 1.0 errechnet
    //pre: altersStruktur != null & altersStruktur.size() > 0
    //post: gesundheit e [0.25,1.0] & altersStruktur != null & altersStruktur.size() > 0
    private void calcGesundheit() {
        int space = 0;
        float idealValue = 1.0f / (altersStruktur.size()*2);
        for (Float f : altersStruktur) {
            if (f < idealValue) {
                space++;
            }
        }
        gesundheit = 0.25f + ((0.75f / (float) (altersStruktur.size())) * (float) (space));
    }

    //pre: afaktor e [0.0,1.0] & ausfall >= 0.0f
    //inv: gesundheit e [0.25,1.0]
    //post: ausfall >= 0.0f
    private void calcAusfall(float afaktor) {
        ausfall = afaktor * gesundheit;
    }

    //pre: zfaktor e [0.0,0.08] & ausfall >= 0.0f
    //inv: baumBestand & zielbestand bleiben unverändert
    private void calcZuwachs(float zfaktor) {
        zuwachs = zielbestand * zfaktor - ausfall * baumBestand;
    }

    //post:baumBestand >= 0
    private void updateBaumbestand() {
        baumBestand += zuwachs;
    }

    //simuliert das Älterwerden der Bäume im Wald
    //pre: altersStruktur != null & altersStruktur.size() > 0
    //inv: altersStruktur.size() bleibt unverändert & Summe aller Einträge ergibt 1.0
    private void altersStrukturPlusOneYear() {
        altersStruktur.remove(altersStruktur.size() - 1);
        float sumBestand = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            float f = altersStruktur.get(i);
            f *= (1.0f - ausfall);
            sumBestand += f;
            altersStruktur.set(i,f);
        }
        altersStruktur.add(0, 1.0f-sumBestand);
    }

    //der zielbestand wird abhängig vom ausfall aktualisiert
    //pre: maxZielb >= 0 & ausfall >= 0.0
    //inv: zielbestand >= 0
    private void updateZielbestand(float maxZielb) {
        if (ausfall >= 0.3) {
            zielbestand *= (1 - ausfall);
        } else if (Float.compare(zielbestand, maxZielb-5) <= 0) {
            zielbestand += 5.0f;
        } else {
            zielbestand = maxZielb;
        }
    }

    //inv: baumBestand & ausfall bleiben unverändert & co2Vorrat >= 0
    private void calcCO2() {
        co2Vorrat += zuwachs;
        if (ausfall < 0.3) {
            co2Vorrat += baumBestand * ausfall / 3;
        } else {
            co2Vorrat *= (1 - (ausfall / 2));
        }
    }

    //für einen Wald mit Ernte wird hier abhängig vom Ausfall der Wald verändert
    //pre: afaktor e [0.0,1.0] & c != null & c.length == 1 & maxZieb >= 0
    private void ernteBew(float afaktor,int[] c, float maxZielb) { //in int[] c werden die Jahre in Folge gezählt mit ausfall < 0.1
        if (ausfall >= 0.1 && ausfall < 0.3) {
            float sumAnteil = updateAltersstruktur(46);

            ernte += (sumAnteil * baumBestand + ausfall * baumBestand) / 2;

            updateBaumGesAusfall(sumAnteil, afaktor, maxZielb);

            c[0] = 0;
        } else if (ausfall < 0.1 && c[0] == 11) {
            float sumAnteil = updateAltersstruktur(75);

            ernte += (sumAnteil * baumBestand * 2) / 3;

            updateBaumGesAusfall(sumAnteil, afaktor, maxZielb);

            c[0] = 0;
        }
        else if (ausfall < 0.1 && c[0] < 11) {
            c[0]++;
        } else if (ausfall >= 0.3) {
            c[0] = 0;
        }
    }

    //Hier werden alle Bäume ab (inkl.) einem bestimmten Alter (=limitAge) gefällt
    //der Gesamtanteil der gefällten Bäume wird zurückgegeben
    //pre: limitAge >= 0
    //inv: altersStruktur.size() bleibt unverändert
    //post: sA e [0.0,1.0]
    private float updateAltersstruktur(int limitAge){
        float sA = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            if (i >= limitAge) {
                sA += altersStruktur.get(i);
                altersStruktur.set(i, 0.0f);
            }
        }
        return sA;
    }

    //Baumbestand, Gesundheit, Ausfall und Zielbestand werden gemäß einem anteiligen Wegfall aktualisiert
    //pre: wegfall e [0.0,1.0] & afaktor e [0.0,1.0] & maxZielb >= 0
    private void updateBaumGesAusfall(float wegfall, float afaktor, float maxZielb) {
        baumBestand -= wegfall * baumBestand;
        calcGesundheit();
        calcAusfall(afaktor);
        updateZielbestand(maxZielb);
    }

    @Override
    // gibt Zustand des Waldes aus
    //die Ausgabe der Altersstruktur wurde zum Zweck der Leserlichkeit auskommentiert
    public String toString() {
        String s = "Baumbestand: " + String.format("%6.2f",baumBestand) + "\t\tGesundheit: " + String.format("%6.2f",gesundheit) + "\t\tZielbestand: " + String.format("%6.2f",zielbestand) + "\t\tErnte: " + String.format("%6.2f",ernte) + "\t\tCO2-Vorrat: " + String.format("%6.2f",co2Vorrat);
        /*
        s += "\t\tAusfall: " + ausfall + "\t\tZuwachs: " + zuwachs;
        s += "; Altersstruktur: [ ";
        for (int i = 0; i < altersStruktur.size(); i++) {
            float fm = altersStruktur.get(i) * baumBestand;
            if (i == altersStruktur.size() - 1) {
                s += "" + fm + " ]; ";
            } else {
                s += "" + fm + ", ";
            }

        }
        s += " ]";
         */
        return s;
    }
}
