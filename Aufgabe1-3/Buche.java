import java.util.ArrayList;

public class Buche extends Population {
    public Buche(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    public Buche(Population w) {
        super(w);
    }

    //Faktoren evtl. noch anpassen!? derzeit: für Laubwald ist Hitze okay, aber keine Muren oder Sturm
    @Override
    protected float berAusfallsfaktor(float[] einflussArray){
        float[] einflussArrayKopie = new float[einflussArray.length];
        for (int i = 0; i < einflussArray.length - 1; i++) {
            einflussArrayKopie[i] = einflussArray[i];
        }
        einflussArrayKopie[0] *= 0.9f;
        einflussArrayKopie[1] *= 1.1f;
        if (einflussArrayKopie[1] >= 1.0f){
            einflussArrayKopie[1] = 1.0f;
        }
        einflussArrayKopie[2] *= 1.1f;
        if (einflussArrayKopie[2] >= 1.0f){
            einflussArrayKopie[2] = 1.0f;
        }

        return ausfallHilfe(einflussArrayKopie);
    }

}
