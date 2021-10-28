public class Erholungsgebiet extends Bewirtschaftet {

    public Erholungsgebiet(Wald w) {
        super(w);
    }

    @Override
    public void plusOneYear(float[] einflussArray) {
        //w.annualCalcErhG(einflussArray, 350.0f, counter); //Implementieren, man könnte noch iwo MaxZb Ändern, wenn man will
    }


}
