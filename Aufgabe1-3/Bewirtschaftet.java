public class Bewirtschaftet implements Modell {

    private Wald w;

    public Bewirtschaftet(Wald w) {
        this.w = w;
    }

    @Override
    public void plusOneYear(float afaktor, float zfaktor) {
        w.annualCalcBew(afaktor, zfaktor, 350.0f);
    }

    @Override
    public String toString() {
        return "Bewirtschaftet: " + w;
    }
}
