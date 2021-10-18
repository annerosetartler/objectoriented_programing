public class Bewirtschaftet implements Modell {

    private Wald w;
    private int[] counter;

    //pre: w != null
    public Bewirtschaftet(Wald w) {
        this.w = w;
        counter = new int[]{0};
    }

    @Override
    //pre: afaktor e [0.0,1.0] & zfaktor e [0.0,0.08]
    public void plusOneYear(float afaktor, float zfaktor) {
        w.annualCalcBew(afaktor, zfaktor, 350.0f, counter);
    }

    @Override
    public String toString() {
        return "Bewirtschaftet:\n" + w;
    }
}
