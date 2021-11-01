public class Plenterwirtschaft extends Bewirtschaftet {
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

    //pre: w != null
    public Plenterwirtschaft(Wald w) {
        super(w);

    }

    public void plusoneyear(){
        wirtschaftsfaktoren[1] = 100.0f/250.0f;//Möglichkeit ohne setter an Alterstrukutr size zu kommen?
        wirtschaftsfaktoren[2] = 0.45f;
    }

}
