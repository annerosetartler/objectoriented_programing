import java.util.ArrayList;

public class Laubwald extends Wald {
    public Laubwald(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    public Laubwald(Wald w) {
        super(w);
    }

    //Faktoren evtl. noch anpassen!? derzeit: für Laubwald ist Hitze okay, aber keine Muren oder Sturm
    @Override
    protected float calcAusfallsfaktor(float[] einflussArray, float[] wirtschaftsfaktoren){
        return (einflussArray[0] * 0.9f + einflussArray[1] * 1.1f + einflussArray[2] * 1.1f)/3;
    }

    //Einige weitere Funktionen werden hier anders implementiert werden, iat dem/der Implementierenden überlassen

}
