public class Erholungsgebiet extends Bewirtschaftet {
    private int[] counter;

    public Erholungsgebiet(Wald w) {
        super(w); //wenn wir das mit dem geringeren Baumbestand am Anfang machen, brauchen wir einen "echten" eigenen Konstruktor hier und können es nicht durch den Parent-Konstruktor machen
    }
}
