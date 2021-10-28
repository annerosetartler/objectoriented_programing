public class Erholungsgebiet extends Bewirtschaftet {

    public Erholungsgebiet(Wald w) {
        super(w); //wenn wir das mit dem geringeren Baumbestand am Anfang machen, brauchen wir einen "echten" eigenen Konstruktor hier und können es nicht durch den Parent-Konstruktor machen
    }

    @Override
    public void plusOneYear(float[] einflussArray) {
        //w.annualCalcErhG(einflussArray, 350.0f, counter); //Implementieren, man könnte noch iwo MaxZb Ändern, wenn man will
    }


}
