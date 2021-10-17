public class Naturbelassen implements Modell {

    private Wald w;

    public Naturbelassen(Wald w) {
        this.w = w;
    }

    @Override
    public void plusOneYear(float afaktor, float zfaktor) {
        w.annualCalcNat(afaktor, zfaktor, 250.0f);
    }

    @Override
    public String toString() {
        return "Naturbelassen: " + w;
    }
}
