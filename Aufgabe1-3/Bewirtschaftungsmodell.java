public class Bewirtschaftungsmodell {
    //Input für plusOneYear: ein Array mit [Hitze,Mure,Sturm,Zuwachs] mit jeweils Werten zwischen [0.0,1.0]
    //vll ist das eine Objekteigenschaft von dem jeweiligen Modell?

    /*
    wirtschaftsfaktoren[0] ist 1 wenn durch einen Kahlschlag geerntet werden soll ansonsten ist er 0
    wirtschaftsfaktoren[1] ist ein Faktor in Prozent der sagt wie hoch der Anteil an Festmetern pro Altersstruktur sein darf
    wirtschaftsfaktoren[2] ist ein Faktor in Prozent der bestimmt ab wann im Mischwald begonnen werden muss Baumarten zu fällen
                            also bei Faktor 0,4 würde man sobald eine Baumart weniger als 40% der Festmeter im Wald ausmacht beginnen
                            die andere Baumart zu fällen sodass nach der Fällung wieder 50% Laub- sowie Nadelbäume im Wald sind
    wirtschaftsfaktoren[3] ist ein Prozentwert der aussagt wie viel Prozent Festmeter gefällt werden müssen.
                           Dieser Wert ergibt sich aus Pflegung alter Wege, Anschaffung neuer, Pflegung alter Hütten und Bauung neuer
     */
    protected float[] wirtschaftsfaktoren = new float[4];
    protected int altersKlassen;


    //pre: w != null
    public Bewirtschaftungsmodell(int altersKlassen) {
        this.altersKlassen = altersKlassen;
    }

    /*public String toString() {
        return getClass() + ":\n" + w.getClass() + w;
    }
     */

    public float[] plusEinJahr() {
        return wirtschaftsfaktoren;
    }

}