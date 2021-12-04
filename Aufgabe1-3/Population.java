import java.util.ArrayList;

public interface Population {
    //KOMMENTAR: Population ist ein Interface mit verschiedene Arten von Baum-Populationen (in unserem Fall derzeit zwei:
    //           Fichte oder Buche) als Instanzen. Alle Implementierungen des Interfaces verfügen über eine plusEinJahr()-Methode,
    //           die eine Alterung des Walds um ein Jahr simuliert
    //INV: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //     Wert für gesundheit in [0.25,1.0]
    //     baumBestand >= 0
    //     zielbestand >= 0
    //     ernte >= 0
    //     ausfall in [0.0,1.0]
    //GUT: Klassenzusammenhalt: es gibt wenige public Methoden, die in sich Abläufe von private Methoden regulieren
    //                          dadurch kann eine unerwünschte Aufrufreihenfolge von Methoden durch den Client unterbunden werden

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      maxZielb > 0
    //NACHB: verändert den Zustand der Population
    void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren, float maxZielb, boolean istMischwald);

    //VORB: neuerbaumbestand >= 0
    void plenterernte(float neuerbaumbestand);

    //VORB: Wert von gesundheit in [0.25,1.0]
    void setGesundheit(float gesundheit);

    //KOMMENTAR: setzt die istMischwald-Variable auf true, wenn eine Population von Fichte und eine von Buche gemeinsam
    //in einem Forst existieren
    public void setzeMischwaldVar();

    public String toString();

    //KOMMENTAR: für Testcases zum Überprüfen des Populationszustands
    float[] zustandPop();

    //KOMMENTAR: Gibt die Art (= Name) der Population als aus
    String getName();

    ArrayList<Float> getAltersstruktur();

    float getBaumbestand();

}
