import java.util.Arrays;

public interface Einfluesse {
    //KOMMENTAR: Einfluesse sind Wetterparameter wie zum Beispiel Niederschlag oder Wind.
    //           Für 1 Jahr werden diese in einem Array (= monatlicheWerte) der Größe 12 gespeichert. Jeder Eintrag im Array repräsentiert
    //           den durchschnittlichen Wert für den jeweiligen Monat (= index + 1).
    //           Jeder Einfluss besitzt auch ein unveränderliches Array namens normWerte. Dieses beinhaltet die über einen
    //           längeren Zeitraum beobachteten Durchschnittswerte für den jeweiligen Wetterparameter und dient als Basis
    //           für weitere Berechnungen z.B. für die Errechnung zukünfitger Werte.
    //           Einflüsse besitzen einen Faktor, der Auskunft darüber gibt wie drastisch die diesjährigen Durchschnittswerte
    //           von den Normwerten abweichen. Ist der Faktor 1.0f, dann sind die diesjährigen Werte zu stark von den
    //           Normwerten abgewichen.
    //INV: monatlicheWerte.length == 12 & normWerte.length == 12 & faktor in [0.0,1.0]

    //VORB: abweichungen.length == 12 & abweichungen[i] >= 0.5f
    //NACHB: simuliert das Vergehen eines Jahres
    //       dabei werden die monatlichen Werte neu errechnet aus abweichungen und den NormWerten
    //       anschließend wird der faktor aktualisiert
    void Plus1Jahr(float[] abweichungen);

    //VORB: ei != null
    //NACHB: gibt Auskunft darüber, wie sehr sich this Einfluss und ei in einem guten Verhältnis zueinander befinden
    //       bei einem idealen Verhältnis wird 1.0f zurückgegeben
    //       je schlechter das Verhältnis, umso näher bei 0.0f
    //       wenn das Verältnis zu stark vom idealen Verhältnis abweicht, wird 0.0f zurückgegeben
    //       gibt einen Wert in [0.0,1.0] zurück
    float VerhaeltnisZu(Einfluesse ei);

    //NACHB: gibt die monatlichen Werte zurück
    float[] getMonatlicheWerte();

    //NACHB: gibt den Faktor des Einflusses zurück
    float getFaktor();

    //NACHB: gibt den Inhalt des monatlicheWerte-Arrays als String zurück
    String toString();
}
