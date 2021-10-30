public class Plenterwirtschaft extends Bewirtschaftet {
    private int[] counter;

    //pre: w != null
    public Plenterwirtschaft(Wald w) {
        super(w);
    }

    @Override
    public void plusOneYear(float[] einflussArray) {
        getWald().annualCalcPlW(einflussArray, 350.0f, counter); //Implementieren, man könnte noch iwo MaxZb Ändern, wenn man will
    }

}
