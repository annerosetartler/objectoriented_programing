public class Einflüsse {
    protected float[] monatlicheWerte;//hat Länge 12
    protected float[] normWerte;//hat Länge 12
    //protected float durchschnitt;

    public Einflüsse(float[] mW, float[] nW){
        monatlicheWerte = mW;
        normWerte = nW;
    }

    protected void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
    }

    protected float Faktor(float abweichungsgrad, float grenzwertAbw){
        int zähler = 0;
        float sumAbw = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            float abweichung = monatlicheWerte[i]/normWerte[i];
            if(abweichung >= abweichungsgrad){
                zähler++;
                sumAbw += abweichung;
            }
        }
        float totAbw = sumAbw/zähler;
        if(totAbw >= grenzwertAbw){
            return 1.0f;
        }else{
            return (totAbw - abweichungsgrad)/(grenzwertAbw - abweichungsgrad);
        }
    }

    /*
    protected float Durchschnitt(){
        float sum = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            sum += monatlicheWerte[i];
        }
        sum /= 12.0f;
        return sum;
    }
     */

}
