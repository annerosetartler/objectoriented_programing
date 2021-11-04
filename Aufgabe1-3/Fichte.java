import java.util.ArrayList;

public class Fichte extends Population {


    public Fichte(ArrayList<Float> as, float bB, float zb) {
        super(as, bB, zb);
    }

    public Fichte(Population w) {
        super(w);
    }

    @Override
    protected float berAusfallsfaktor(float[] einflussArray, float[] wirtschaftsfaktoren){
        return (einflussArray[0] * 1.1f + einflussArray[1] + einflussArray[2] *0.9f)/3;
    }

    @Override
    //Zuwachs gemindert, wenn es ein Mischwald ist
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
        co2Vorrat *= 0.98; //Nadelwald-Penalty
    }
}
