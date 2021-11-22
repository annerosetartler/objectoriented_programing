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
    void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb, boolean istMischwald);

    //VORB: neuerbaumbestand >= 0
    void plenterernte(float neuerbaumbestand);

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
}
