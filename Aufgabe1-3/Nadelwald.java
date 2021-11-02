import java.util.ArrayList;

public class Nadelwald extends Wald {


    public Nadelwald(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    public Nadelwald(Wald w) {
        super(w);
    }

    //ToDo: Wirtschaftsfaktoren? Derzeit nur Einflüsse...
    //Faktoren evtl. noch anpassen!? derzeit: für Nadelwald ist Hitze schlecht, Muren neutral und Sturm okay
    @Override
    protected float calcAusfallsfaktor(float[] einflussArray, float[] wirtschaftsfaktoren){
        return (einflussArray[0] * 1.1f + einflussArray[1] + einflussArray[2] *0.9f)/3;
    }

    //Einige weitere Funktionen werden hier anders implementiert werden, iat dem/der Implementierenden überlassen
}
