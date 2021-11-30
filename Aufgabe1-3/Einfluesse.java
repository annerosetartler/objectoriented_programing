import java.util.Arrays;

public interface Einfluesse {
    //INV: monatlicheWerte.length == 12 & normWerte.length == 12
    //SCHLECHT: protected Objektvariablen: können von überall verändert werden, dadurch Bedingung der Invariante gefährdet!
    //          Verbesserung: aus Einfluesse ein Interface machen und in den Untertypen private Objektvariablen anlegen
    //KOMMENTAR: normWerte beinhaltet Referenzwerte zur Errechnung zukünftiger Werte
    //CLIENT-CONSTRAINT: Faktor() || VerhältnisZu() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor entweder Faktor() oder VerhältnisZu()
    //SCHLECHT: ERROR: Da Plus1Jahr(), Faktor() & VehältnisZu() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //          ERROR: In den Untertypen wird das gleiche Problem entstehen. Verbesserung: aus Einfluesse Interface machen

    //VORB: abweichungen.length == 12 & abweichungen[i] > 0.0f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    void Plus1Jahr(float[] abweichungen);

    //VORB: abweichungsgrad >= 0.0f & grenzwertAbw > abweichungsgrad
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    float Faktor();

    float[] getMonatlicheWerte();

    String toString();
}
