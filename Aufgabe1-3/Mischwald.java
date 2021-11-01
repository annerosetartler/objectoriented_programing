import java.util.ArrayList;

public class Mischwald extends Wald {
    private Nadelwald nadelwald;
    private Laubwald laubwald;
    protected float nadelwaldfaktor; //prozent nadelwald

    public Mischwald(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    public Mischwald(Wald w) {
        super(w);
    }

    //ToDo Implementieren
    @Override
    protected float calcAusfallsfaktor(float[] einflussArray, float[] wirtschaftsfaktoren){
        return (nadelwald.calcAusfallsfaktor(einflussArray, wirtschaftsfaktoren)*(1-nadelwaldfaktor) + nadelwald.calcAusfallsfaktor(einflussArray, wirtschaftsfaktoren)*nadelwaldfaktor)/2;
    }

    @Override
    protected boolean isMischwald() {
        return true;
    }

    //Einige weitere Funktionen werden hier anders implementiert werden, iat dem/der Implementierenden überlassen
}
