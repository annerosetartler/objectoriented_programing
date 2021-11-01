public class Bewirtschaftungsmodell {
    //Input für plusOneYear: ein Array mit [Hitze,Mure,Sturm,Zuwachs] mit jeweils Werten zwischen [0.0,1.0]
    //vll ist das eine Objekteigenschaft von dem jeweiligen Modell?

    private Wald w;

    //pre: w != null
    public Bewirtschaftungsmodell(Wald w) {
        this.w = w;

    }

    public String toString() {
        return getClass() + ":\n" + w.getClass() + w;
    }

    public float[] plusOneYear(int Modell) {
        if (Modell == 1){
            Kahlschlag modellK = new Kahlschlag(w);
            modellK.plusOneYear();
            return modellK.getWirtschaftsfaktoren();
        }else if (Modell == 2){
            Erholungsgebiet modellE = new Erholungsgebiet(w);
            modellE.plusOneYear();
            return modellE.getWirtschaftsfaktoren();
        }else if (Modell == 3){
            Plenterwirtschaft modellP = new Plenterwirtschaft(w);
            modellP.plusoneyear();
            return modellP.getWirtschaftsfaktoren();
        }else{
            return null;
        }
    }

    protected Wald getWald() {
        return w;
    }
}