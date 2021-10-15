public class Bewirtschaftet implements Modell {

    private Wald w;

    public Bewirtschaftet(Wald w){
        this.w = w;
    }

    @Override
    public void plusOneYear(float afaktor, float zfaktor) {
        w.calcAusfall(afaktor);
        w.calcZuwachs(zfaktor);
        w.updateBaumbestand();
        w.altersStrukturPlusOneYear();
        w.calcGesundheit();
        w.adaptBewZielbestand();
        w.calcCO2();
        w.ernteBew(afaktor);
        w.adaptBewZielbestand();
        w.calcCO2();
    }

    @Override
    public String toString() {
        return "Bewirtschaftet: " + w;
    }
}
