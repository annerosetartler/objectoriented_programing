public class Erholungsgebiet extends Bewirtschaftet {
    /*
    wirtschaftsfaktoren[0] ist 1 wenn durch einen Kahlschlag geerntet werden soll ansonsten ist er 0
    wirtschaftsfaktoren[1] ist ein Faktor in Prozent der sagt wie hoch der Anteil an Festmetern pro Altersstruktur sein darf
    wirtschaftsfaktoren[2] ist ein Faktor in Prozent der bestimmt ab wann im Mischwald begonnen werden muss Baumarten zu fällen
                            also bei Faktor 0,4 würde man sobald eine Baumart weniger als 40% der Festmeter im Wald ausmacht beginnen
                            die andere Baumart zu fällen sodass nach der Fällung wieder 50% Laub- sowie Nadelbäume im Wald sind
    wirtschaftsfaktoren[3] ist ein Prozentwert der aussagt wie viel Prozent Festmeter gefällt werden müssen.
                           Dieser Wert ergibt sich aus Pflegung alter Wege, Anschaffung neuer, Pflegung alter Hütten und Bauung neuer
     */
    private float[] wirtschaftsfaktoren;
    private int AnzahlWege;
    private int AnzahlHütten;
    private int neuH;
    private int neuW;

    public Erholungsgebiet(Wald w) {
        super(w); //wenn wir das mit dem geringeren Baumbestand am Anfang machen, brauchen wir einen "echten" eigenen Konstruktor hier und können es nicht durch den Parent-Konstruktor machen
    }

    public void plusOneYear() {
        if (AnzahlWege == 0){
            AnzahlWege++;
            neuW++;
        }else if (Math.random() > 0.95){
            wirtschaftsfaktoren[3] = 1;
            AnzahlWege++;
            neuW++;
        }
        if (Math.random() > 0.98){
            AnzahlHütten++;
            neuH++;
        }

        wirtschaftsfaktoren[3] = neuH * 0.0001f + neuW + 0.001f + AnzahlHütten * 0.00001f + AnzahlWege * 0.00001f;
    }

}
