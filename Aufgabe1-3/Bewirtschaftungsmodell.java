public class Bewirtschaftungsmodell {
    //Input für plusOneYear: ein Array mit [Hitze,Mure,Sturm,Zuwachs] mit jeweils Werten zwischen [0.0,1.0]
    //vll ist das eine Objekteigenschaft von dem jeweiligen Modell?

    private Wald w;

    //pre: w != null
    public Bewirtschaftungsmodell(Wald w) {
        this.w = w;
    }

    public void plusOneYear(float[] einflussArray) {
        w.annualCalcNat(einflussArray, 250.0f);
    }

    public String toString() {
        return getClass() + ":\n" + w.getClass() + w;
    }


    public Wald getWald() {
        return w;
    }
}
