public class Plenterwirtschaft extends Bewirtschaftet {
    public Plenterwirtschaft(Wald w) {
        super(w);
    }

    @Override
    public void plusOneYear(float[] einflussArray) {
        //w.annualCalcPlW(einflussArray, 350.0f, counter); //Implementieren, man könnte noch iwo MaxZb Ändern, wenn man will
    }

}
