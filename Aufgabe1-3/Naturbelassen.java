public class Naturbelassen implements Modell {

    private Wald w;

    //pre: w != null
    public Naturbelassen(Wald w) {
        this.w = w;
    }

    @Override
    //pre: afaktor e [0.0,1.0] & zfaktor e [0.0,0.08]
    public void plusOneYear(float afaktor, float zfaktor) {
        w.annualCalcNat(afaktor, zfaktor, 250.0f);
    }

    @Override
    public String toString() {
        return "Naturbelassen:\n" + w;
    }
}
