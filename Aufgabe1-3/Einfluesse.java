import java.util.Arrays;

public interface Einfluesse {
    //INV: monatlicheWerte.length == 12 & normWerte.length == 12
    //KOMMENTAR: normWerte beinhaltet Referenzwerte zur Errechnung zukünftiger Werte

    //VORB: abweichungen.length == 12 & abweichungen[i] >= 0.5f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    void Plus1Jahr(float[] abweichungen);

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    float VerhaeltnisZu(Einfluesse ei);

    float[] getMonatlicheWerte();

    float getFaktor();

    String toString();
}
