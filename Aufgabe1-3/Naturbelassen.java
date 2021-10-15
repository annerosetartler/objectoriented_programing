public class Naturbelassen implements Modell {

    private Wald w;

    public Naturbelassen(Wald w){
        this.w = w;
    }

    @Override
    public void plusOneYear(float afaktor, float zfaktor) {
        w.calcAusfall(afaktor);
        w.calcZuwachs(zfaktor);
        w.updateBaumbestand();
        w.altersStrukturPlusOneYear();
        w.calcGesundheit();
        w.adaptNaturZielbestand();
        w.calcCO2();
    }

    @Override
    public String toString() {
        return "Naturbelassen: " + w;
    }
}
