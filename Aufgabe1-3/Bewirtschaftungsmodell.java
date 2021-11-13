public class Bewirtschaftungsmodell {
    //SCHLECHT: protected Objektvariablen: Haben in unserem Projekt die gleiche Sichtbarkeit wie public Objektvariablen
    //          Verbesserung: protected durch privat ersetzen und aus der Klasse Bewirtschaftungsmodelle ein Interface machen
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]
    //      altersKlassen > 0


    /*
    KOMMENTAR:
    wirtschaftsfaktoren[0] ist 1 wenn durch einen Kahlschlag geerntet werden soll ansonsten ist er 0
    wirtschaftsfaktoren[1] 1 wenn Plenterwirtschaft betrieben wird sonst 0
    wirtschaftsfaktoren[2] ist ein Faktor in Prozent der bestimmt ab wann im Mischwald begonnen werden muss Baumarten zu fällen
                            also bei Faktor 0,4 würde man sobald eine Baumart weniger als 40% der Festmeter im Wald ausmacht beginnen
                            die andere Baumart zu fällen sodass nach der Fällung wieder 50% Laub- sowie Nadelbäume im Wald sind
    wirtschaftsfaktoren[3] ist ein Prozentwert der aussagt wie viel Prozent Festmeter gefällt werden müssen.
                           Dieser Wert ergibt sich aus Pflegung alter Wege, Anschaffung neuer, Pflegung alter Hütten und Bauung neuer
     */
    protected float[] wirtschaftsfaktoren = new float[4];
    //ERROR:    die Variable ist ein überbleibsel welches in diesem Code keine Verwendung mehr findet
    protected int altersKlassen;

    //Vorb: altersKlassen > 0
    public Bewirtschaftungsmodell(int altersKlassen) {
        this.altersKlassen = altersKlassen;
    }
    //GUT:  Methode ist dynamisch gebunden
    public float[] plusEinJahr() {
        return wirtschaftsfaktoren;
    }

}