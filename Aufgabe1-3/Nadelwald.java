import java.util.ArrayList;

public class Nadelwald extends Wald {


    public Nadelwald(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    public Nadelwald(Wald w) {
        super(w);
    }

    //ToDo Implementieren
    @Override
    protected float calcAusfallsfaktor(float[] einflussArray, float[] wirtschaftsfaktoren){
        return 0.0f;
    }

    //Einige weitere Funktionen werden hier anders implementiert werden, iat dem/der Implementierenden überlassen
}
