import java.util.ArrayList;

public interface Population {


    //SCHLECHT: Objektvariablen alle protected; Verbesserung: Population als Interface und private Objektvariablen in Untertypen
    //INV: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //     Wert für gesundheit in [0.25,1.0]
    //     baumBestand >= 0
    //     zielbestand >= 0
    //     ernte >= 0
    //     ausfall in [0.0,1.0]
    //GUT: Klassenzusammenhalt: es gibt wenige public Methoden, die in sich Abläufe von private Methoden regulieren
    //                          dadurch kann eine unerwünschte Aufrufreihenfolge von Methoden durch den Client unterbunden werden
    //     Verbesserung: wäre mit einem Interface noch besser

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    //NACHB: verändert den Zustand der Population
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb, boolean istMischwald);

    void berGesundheit();

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //GUT: Methode berAusfallsfaktor() wird dynamisch gebunden
    void berAusfall(float[] einflussArray);

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //SCHLECHT: Methode in Obertyp redundant, weil sie in Obertyp nie aufgerufen wird. Verbesserung: Population als Interface
    float berAusfallsfaktor(float[] einflussArray);

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    float ausfallHilfe(float[] einflussArray);

    //VORB: Wert von zfaktor in [0.0,1.0]
    //GUT: Methode wird dynamisch gebunden
    void berZuwachs(float zFaktor);

    //ERROR: baumBestand könnte < 0 sein, muss abgefangen werden
    void updateBaumbestand();

    void altersStrukturPlusEinJahr();

    //VORB: maxZielb > 0
    void updateZielbestand(float maxZielb);

    //GUT: Methode wird dynamisch gebunden
    void berCO2();

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    void ernteBew(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb);

    //VORB: neuerbaumbestand >= 0
    void plenterernte(float neuerbaumbestand);

    //VORB: alterslimit >= 0
    //NACHB: sA e [0.0,1.0]
    //KOMMENTAR: Variable sA anders benennen, weil schwer verständlich
    float updateAltersstruktur(int alterslimit);

    //VORB: wegfall e [0.0,1.0] & maxZielb > 0
    //      einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    void updateBaumGesAusfall();

    //VORB: Wert von gesundheit in [0.25,1.0]
    void setGesundheit(float gesundheit);

    public void setzeMischwaldVar();

    public String toString();

    //KOMMENTAR: für Testcases zum Überprüfen des Populationszustands
    float[] zustandPop();

    //SCHLECHT: wenn es Instanzen von Population selbst gäbe, würde an dieser Stelle suggeriert, dass es sich um Fichten handelt
    //VERBESSERT, indem es ein Interface wurde
    String getName();

    ArrayList<Float> getAltersstruktur();

    //ToDo; evtl. in zustandPop integrieren?
    float getAusfall();

    float getBaumbestand();



//ToDo Methodenbeschreibungen und noch schauen, ob Kommentare passen







    /*
    ***************************************************************************************************************************



    //SCHLECHT: Objektvariablen alle protected; Verbesserung: Population als Interface und private Objektvariablen in Untertypen
    //INV: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //     Wert für gesundheit in [0.25,1.0]
    //     baumBestand >= 0
    //     zielbestand >= 0
    //     ernte >= 0
    //     ausfall in [0.0,1.0]
    //GUT: Klassenzusammenhalt: es gibt wenige public Methoden, die in sich Abläufe von private Methoden regulieren
    //                          dadurch kann eine unerwünschte Aufrufreihenfolge von Methoden durch den Client unterbunden werden
    //     Verbesserung: wäre mit einem Interface noch besser
    protected float baumBestand;//KOMMENTAR: in Festmetern fm
    protected ArrayList<Float> altersStruktur;//KOMMENTAR: jeder Index repräsentiert ein Alter und jeder Eintrag den Anteil der Bäume dieses Alters
    protected float gesundheit;
    protected float zielbestand;//KOMMENTAR: in Festmetern fm
    protected float ernte; //KOMMENTAR: in Festmetern fm
    protected float co2Vorrat; //KOMMENTAR: in Tonnen t
    protected float ausfall;//KOMMENTAR: in %
    protected float zuwachs;//KOMMENTAR: in Festmetern fm
    protected boolean istMischwald;

    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
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

    //VORB: w != null
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

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    //NACHB: verändert den Zustand der Population
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb, boolean istMischwald) {
        //KOMMENTAR: Alle Bewirtschaftungsmodelle tun:
        berAusfall(einflussArray);
        berZuwachs(einflussArray[3]);
        updateBaumbestand();
        altersStrukturPlusEinJahr();
        berGesundheit();
        updateZielbestand(maxZielb);
        berCO2();
        this.istMischwald = istMischwald;

        //KOMMENTAR: Wenn das Modell eine Ernte beinhaltet passiert zusätzlich
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
    //GUT: Methode berAusfallsfaktor() wird dynamisch gebunden
    private void berAusfall(float[] einflussArray) { //da ich noch unsicher bin, was man braucht für die Ausfall-Calculation
        ausfall = berAusfallsfaktor(einflussArray) * gesundheit;
    }

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //SCHLECHT: Methode in Obertyp redundant, weil sie in Obertyp nie aufgerufen wird. Verbesserung: Population als Interface
    protected float berAusfallsfaktor(float[] einflussArray) {
        return 0.0f;
    }

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
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

    //VORB: Wert von zfaktor in [0.0,1.0]
    //GUT: Methode wird dynamisch gebunden
    protected void berZuwachs(float zFaktor) {
        zuwachs = zielbestand * zFaktor * 0.08f - ausfall * baumBestand;
    }

    //ERROR: baumBestand könnte < 0 sein, muss abgefangen werden
    private void updateBaumbestand() {
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
        if (ausfall >= 0.3) {
            zielbestand *= (1 - ausfall);
        } else if (Float.compare(zielbestand, maxZielb - 5) <= 0) {
            zielbestand += 5.0f;
        } else {
            zielbestand = maxZielb;
        }
    }

    //GUT: Methode wird dynamisch gebunden
    protected void berCO2() {
        co2Vorrat += zuwachs;
        if (ausfall < 0.3) {
            co2Vorrat += baumBestand * ausfall / 3;
        } else {
            co2Vorrat *= (1 - (ausfall / 2));
        }
    }

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    private void ernteBew(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb) {
        if (wirtschaftsfaktoren[0] == 1) {
            float sumAnteil = updateAltersstruktur((int)(altersStruktur.size()/2.0f));
            ernte += (sumAnteil * baumBestand);
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

    //VORB: neuerbaumbestand >= 0
    public void plenterernte(float neuerbaumbestand){
        ernte += baumBestand - neuerbaumbestand;
        this.baumBestand = neuerbaumbestand;
    }

    //VORB: alterslimit >= 0
    //NACHB: sA e [0.0,1.0]
    //KOMMENTAR: Variable sA anders benennen, weil schwer verständlich
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

    public void setzeMischwaldVar(){
        istMischwald = true;
    }

    @Override
    public String toString() {
        String s = "Baumbestand: " + String.format("%6.2f", baumBestand) + "\t\tGesundheit: " + String.format("%6.2f", gesundheit) +
                "\t\tZielbestand: " + String.format("%6.2f", zielbestand) + "\t\tErnte: " + String.format("%6.2f", ernte) +
                "\t\tCO2-Vorrat: " + String.format("%6.2f", co2Vorrat);
        return s;
    }

    //KOMMENTAR: für Testcases zum Überprüfen des Populationszustands
    public float[] zustandPop(){
        float[] zustand = new float[]{baumBestand,zielbestand,gesundheit,ernte,co2Vorrat,zuwachs};
        return zustand;
    }

    //SCHLECHT: wenn es Instanzen von Population selbst gäbe, würde an dieser Stelle suggeriert, dass es sich um Fichten handelt
    public String getName(){
        return "Fichten";
    }
     */
}
