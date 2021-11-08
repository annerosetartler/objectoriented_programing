import java.util.Arrays;

public class Einflüsse {
    protected float[] monatlicheWerte;//hat Länge 12
    protected float[] normWerte;//hat Länge 12, sind Referenzwerte zur Errechnung zukünftiger Werte

    //pre: mw.length == 12 & nW.length == 12
    public Einflüsse(float[] mW, float[] nW){
        monatlicheWerte = mW;
        normWerte = nW;
    }

    //pre: abweichungen.length == 12
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
    }

    //post: gibt einen Wert in [0.0,1.0] zurück
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
        if(sumAbw <= 0.0000001f){
            return 0.0f;
        }
        float totAbw = sumAbw/zähler;
        if(totAbw >= grenzwertAbw){
            return 1.0f;
        }else{
            return (totAbw - abweichungsgrad)/(grenzwertAbw - abweichungsgrad);
        }
    }

    //post: gibt einen Wert in [0.0,1.0] zurück
    protected float VerhältnisZu(Einflüsse ei, float min, float max){
        float verhSum = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            verhSum += monatlicheWerte[i]/ei.monatlicheWerte[i];
        }
        float durchschnittV = verhSum/12.0f;
        float mitte = (min + max)/2.0f;
        if(durchschnittV < min || durchschnittV > max){
            return 0.1f;
        }else if(durchschnittV >= min && durchschnittV < mitte){
            return (durchschnittV - min)/(mitte-min);
        }else if(durchschnittV == mitte){
            return 1.0f;
        }else{
            return 1.0f - (durchschnittV-mitte)/(max-mitte);
        }
    }

    public String toString(){
        return Arrays.toString(monatlicheWerte);
    }
}
