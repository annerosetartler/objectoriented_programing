import java.util.ArrayList;

public class Fichte implements Population {
    //KOMMENTAR: Eine Fichte ist eine bestimmte Baumpopulation, die alleine oder gemeinsam mit einer weiteren Population in einem Forst existieren kann.
    //           Fichten haben in Mischwäldern einen geringeren Zuwachs und CO2-Speicher, als in Wäldern mit nur einer Population.
    //INVARIANTEN: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //            Wert für gesundheit in [0.25,1.0]
    //            baumBestand >= 0 & zielbestand >= 0 & ernte >= 0
    //            ausfall in [0.0,1.0]
    //GUT: Klassenzusammenhalt: es gibt wenige public Methoden, die in sich Abläufe von private Methoden regulieren
    //                          dadurch kann eine unerwünschte Aufrufreihenfolge von Methoden durch den Client unterbunden werden

    private float baumBestand;//KOMMENTAR: in Festmetern fm
    private ArrayList<Float> altersStruktur;//KOMMENTAR: jeder Index repräsentiert ein Alter und jeder Eintrag den Anteil der Bäume dieses Alters
    private float gesundheit;
    private float zielbestand;//KOMMENTAR: in Festmetern fm
    private float ernte; //KOMMENTAR: in Festmetern fm
    private float co2Vorrat; //KOMMENTAR: in Tonnen t
    private float ausfall;//KOMMENTAR: in %
    private float zuwachs;//KOMMENTAR: in Festmetern fm
    private boolean istMischwald;

    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    public Fichte(ArrayList<Float> as, float bB, float zb) {
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

    //VORB: w != null
    public Fichte(Population w) {
        float[] wZustand = w.zustandPop();
        ArrayList<Float> wAltersstruktur = w.getAltersstruktur();
        this.baumBestand = wZustand[0];
        altersStruktur = new ArrayList<Float>();
        for (int i = 0; i < wAltersstruktur.size(); i++) {
            float f = wAltersstruktur.get(i);
            altersStruktur.add(f);
        }
        this.gesundheit = wZustand[2];
        this.zielbestand = wZustand[1];
        this.ernte = wZustand[3];
        this.co2Vorrat = wZustand[4];
        this.ausfall = wZustand[6];
        this.zuwachs = wZustand[5];
        istMischwald = false;
    }

    //KOMMENTAR: Unabhängig vom aktiven Bewirtschaftungsmodell wird die Veränderung des Waldes berechnet. Eine Ernte wird nur
    //           dann berechnet, wenn diese im jeweiligen Modell vorgesehen ist.
    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    //NACHB: verändert den Zustand der Population
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb, boolean istMischwald) {
        berAusfall(einflussArray);
        berZuwachs(einflussArray[3]);
        updateBaumbestand();
        altersStrukturPlusEinJahr();
        berGesundheit();
        updateZielbestand(maxZielb);
        berCO2();
        this.istMischwald = istMischwald;

        if (wirtschaftsfaktoren[0] != 0.0f || wirtschaftsfaktoren[3] != 0.0f || wirtschaftsfaktoren[1] != 0.0f || wirtschaftsfaktoren[2] != 0.0f) {
            ernteBew(einflussArray, wirtschaftsfaktoren, maxZielb);
            berCO2();
        }
    }

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

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    private void berAusfall(float[] einflussArray) {
        ausfall = berAusfallsfaktor(einflussArray) * gesundheit;
    }

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    private float berAusfallsfaktor(float[] einflussArray) {
        float[] einflussArrayKopie = new float[einflussArray.length];
        for (int i = 0; i < einflussArray.length - 1; i++) {
            einflussArrayKopie[i] = einflussArray[i];
        }
        einflussArrayKopie[0] *= 1.05f;
        if (einflussArrayKopie[0] >= 1.0f) {
            einflussArrayKopie[0] = 1.0f;
        }
        einflussArrayKopie[2] *= 0.8f;

        return ausfallHilfe(einflussArrayKopie);
    }

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    private float ausfallHilfe(float[] einflussArray) {
        int zähler = 0;
        for (int i = 0; i < einflussArray.length - 1; i++) {
            if (einflussArray[i] == 1.0f) {
                zähler++;
            }
        }
        float durchschnitt = (einflussArray[0] + einflussArray[1] + einflussArray[2]) / 3.0f;
        switch (zähler) {
            case 0:
                return durchschnitt * 0.08f;
            case 1:
            case 2:
                return durchschnitt * 0.6f;
            default:
                return durchschnitt;
        }
    }

    //VORB: Wert von zfaktor in [0.0,1.0]
    private void berZuwachs(float zFaktor) {
        zuwachs = zielbestand * zFaktor * 0.08f * (istMischwald ? 0.95f : 1) - ausfall * baumBestand;
    }

    private void updateBaumbestand() {
        if (baumBestand < 0) {
            baumBestand = zuwachs;
        }
        baumBestand += zuwachs;
    }

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

    //VORB: maxZielb > 0
    private void updateZielbestand(float maxZielb) {
        if (ausfall >= 0.2) {
            zielbestand *= (1 - ausfall);
        } else if (Float.compare(zielbestand, maxZielb - 5) <= 0) {
            zielbestand += 5.0f;
        } else {
            zielbestand = maxZielb;
        }
    }

    private void berCO2() {
        co2Vorrat += zuwachs;
        if (ausfall < 0.3) {
            co2Vorrat += baumBestand * ausfall / 3;
        } else {
            co2Vorrat *= (1 - (ausfall / 2));
        }
        co2Vorrat *= 0.98; //KOMMENTAR: Nadelwald-Penalty
    }

    ///KOMMENTAR: Methode zur Ernte je nach Bewirtschaftungsmodell
    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    private void ernteBew(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb) {
        if (wirtschaftsfaktoren[0] == 1) {
            float sumAnteil = updateAltersstruktur((int) (altersStruktur.size() / 2.0f));
            ernte += (sumAnteil * baumBestand);
            updateBaumGesAusfall(sumAnteil, einflussArray, maxZielb);
            return;
        }
        if (wirtschaftsfaktoren[1] != 0) {
            float sumBestand = 0.0f;
            for (int i = 0; i < altersStruktur.size() - 1; i++) {
                if (altersStruktur.get(i) < altersStruktur.get(i + 1) * 1.5f) {
                    sumBestand += altersStruktur.get(i + 1) * 0.02f;
                    altersStruktur.set(i + 1, altersStruktur.get(i + 1) * 0.98f);
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

    //VORB: neuerbaumbestand >= 0
    public void plenterernte(float neuerbaumbestand) {
        ernte += baumBestand - neuerbaumbestand;
        this.baumBestand = neuerbaumbestand;
    }

    //VORB: alterslimit >= 0
    //NACHB: SummeAltersstruktur e [0.0,1.0]
    private float updateAltersstruktur(int alterslimit) {
        float SummeAltersstruktur = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            if (i >= alterslimit) {
                SummeAltersstruktur += altersStruktur.get(i);
                altersStruktur.set(i, 0.0f);
            }
        }
        return SummeAltersstruktur;
    }

    //VORB: wegfall e [0.0,1.0] & maxZielb > 0
    //      einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    private void updateBaumGesAusfall(float wegfall, float[] einflussArray, float maxZielb) {
        baumBestand -= wegfall * baumBestand;
        berGesundheit();
        berAusfall(einflussArray);
        updateZielbestand(maxZielb);
    }

    //VORB: Wert von gesundheit in [0.25,1.0]
    public void setGesundheit(float gesundheit) {
        this.gesundheit = gesundheit;
    }

    //KOMMENTAR: setzt die istMischwald-Variable auf true und halbiert den Anfangs-Zielbestand
    public void setzeMischwaldBesonderheiten() {
        istMischwald = true;
        zielbestand /= 2;
    }

    @Override
    public String toString() {
        String s = "Baumbestand: " + String.format("%6.2f", baumBestand) + "\t\tGesundheit: " + String.format("%6.2f", gesundheit) +
                "\t\tZielbestand: " + String.format("%6.2f", zielbestand) + "\t\tErnte: " + String.format("%6.2f", ernte) +
                "\t\tCO2-Vorrat: " + String.format("%6.2f", co2Vorrat);
        return s;
    }

    //KOMMENTAR: für Testcases zum Überprüfen des Populationszustands, Forst Zwei-Populationen-toString und für den Konstruktor mit Eingabeparameter Population
    public float[] zustandPop() {
        float[] zustand = new float[]{baumBestand, zielbestand, gesundheit, ernte, co2Vorrat, zuwachs, ausfall};
        return zustand;
    }

    public String getName() {
        return "Fichten";
    }

    public ArrayList<Float> getAltersstruktur() {
        return altersStruktur;
    }

    public float getBaumbestand() {
        return baumBestand;
    }

}
