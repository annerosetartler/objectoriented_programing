import java.util.ArrayList;

public class Fichte extends Population {

    //INV: Werte in altersStruktur in [0.0,1.0] & Summe aller Werte in altersStruktur ergibt 1.0 & altersStruktur.size > 0
    //     Wert für gesundheit in [0.25,1.0]
    //     baumBestand >= 0
    //     zielbestand >= 0
    //     ernte >= 0
    //     ausfall in [0.0,1.0]

    //VORB: as != null & as.size() > 0 & bB > 0 & zb > 0
    //      Summe aller Werte in as ergibt 1.0 & Werte in as liegen in [0.0,1.0]
    public Fichte(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    //VORB: w != null
    public Fichte(Population w) {
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
        einflussArrayKopie[0] *= 1.05f;
        if (einflussArrayKopie[0] >= 1.0f){
            einflussArrayKopie[0] = 1.0f;
        }
        einflussArrayKopie[2] *= 0.8f;

        return ausfallHilfe(einflussArrayKopie);
    }

    @Override
    //VORB: Wert von zfaktor in [0.0,1.0]
    protected void berZuwachs(float zFaktor){
        zuwachs = zielbestand * zFaktor * 0.08f * (istMischwald? 0.95f : 1) - ausfall * baumBestand;
    }

    @Override
    protected void berCO2() {
        co2Vorrat += zuwachs;
        if (ausfall < 0.3) {
            co2Vorrat += baumBestand * ausfall / 3;
        } else {
            co2Vorrat *= (1 - (ausfall / 2));
        }
        co2Vorrat *= 0.98; //KOMMENTAR: Nadelwald-Penalty
    }

    @Override
    public String getName(){
        return "Fichten";
    }
}
