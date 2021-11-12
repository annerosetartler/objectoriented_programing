import java.util.ArrayList;

public class Buche extends Population {
    //INV: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //     Wert für gesundheit in [0.25,1.0]
    //     baumBestand >= 0
    //     zielbestand >= 0
    //     ernte >= 0
    //     ausfall in [0.0,1.0]

    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    public Buche(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    //VORB: w != null
    public Buche(Population w) {
        super(w);
    }

    @Override
    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    protected float berAusfallsfaktor(float[] einflussArray){
        float[] einflussArrayKopie = new float[einflussArray.length];
        for (int i = 0; i < einflussArray.length - 1; i++) {
            einflussArrayKopie[i] = einflussArray[i];
        }
        einflussArrayKopie[0] *= 0.8f;
        einflussArrayKopie[1] *= 1.05f;
        if (einflussArrayKopie[1] >= 1.0f){
            einflussArrayKopie[1] = 1.0f;
        }
        einflussArrayKopie[2] *= 1.05f;
        if (einflussArrayKopie[2] >= 1.0f){
            einflussArrayKopie[2] = 1.0f;
        }

        return ausfallHilfe(einflussArrayKopie);
    }

    @Override
    public String getName(){
        return "Buchen";
    }
}
