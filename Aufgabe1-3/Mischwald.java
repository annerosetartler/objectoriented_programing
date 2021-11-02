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

        return nadelwaldfaktor*((einflussArray[0] * 1.1f + einflussArray[1] + einflussArray[2] *0.9f)/3) +
                ((1-nadelwaldfaktor)* (einflussArray[0] * 0.9f + einflussArray[1] * 1.1f + einflussArray[2] * 1.1f)/3);


        //return (nadelwald.calcAusfallsfaktor(einflussArray, wirtschaftsfaktoren)*(1-nadelwaldfaktor) + laubwald.calcAusfallsfaktor(einflussArray, wirtschaftsfaktoren)*nadelwaldfaktor)/2;
    }

    @Override
    protected boolean isMischwald() {
        return true;
    }


    //ToDo in Ernte:
    /*
    if (wirtschaftsfaktoren[2] > 0.0f){ //ToDo in alle
            if (nadelwaldfaktor < wirtschaftsfaktoren[2] || (1 - nadelwaldfaktor) < wirtschaftsfaktoren[2]){
                //größe des waldes - ...

            }

        }
     */

    //Einige weitere Funktionen werden hier anders implementiert werden, iat dem/der Implementierenden überlassen
}
