public class Bewirtschaftet implements Modell {

    private Wald w;
    private int[] counter;

    public Bewirtschaftet(Wald w) {
        this.w = w;
        counter = new int[]{0};
    }

    @Override
    public void plusOneYear(float afaktor, float zfaktor) {
        w.annualCalcBew(afaktor, zfaktor, 350.0f,counter);
    }

    @Override
    public String toString() {
        return "Bewirtschaftet: " + w;
    }
}
