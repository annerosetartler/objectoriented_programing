public class Bewirtschaftet implements Modell {

    private Wald w;
    private int[] counter;

    //pre: w != null
    public Bewirtschaftet(Wald w) {
        this.w = w;
        counter = new int[]{0};
    }

    //!!!!!!!!!!!!
    //LÖSCHEN, SOBALD WIR WAS TUN UND FIXEN, WAS DANN NICHT MEHR GEHT
    //!!!!!!!!!!!!
    @Override
    //pre: afaktor e [0.0,1.0] & zfaktor e [0.0,0.08]
    public void plusOneYear(float afaktor, float zfaktor) {
        w.annualCalcBew(afaktor, zfaktor, 350.0f, counter);
    }

    @Override
    public void plusOneYear(float[] einflussArray) {
        w.annualCalcBew(einflussArray, 350.0f, counter); //Implementieren
    }

    @Override
    public String toString() {
        return getClass() + ":\n" + w.getClass() + w;
        //return "Bewirtschaftet:\n" + w.getClass() + w;
    }
}
