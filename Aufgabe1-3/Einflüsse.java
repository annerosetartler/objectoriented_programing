import java.util.Arrays;

public class Einflüsse {
    //INV: monatlicheWerte.length == 12 & normWerte.length == 12
    //SCHLECHT: protected Objektvariablen: können von überall verändert werden, dadurch Bedingung der Invariante gefährdet!
    //          Verbesserung: aus Einflüsse ein Interface machen und in den Untertypen private Objektvariablen anlegen
    //KOMMENTAR: normWerte beinhaltet Referenzwerte zur Errechnung zukünftiger Werte
    //CLIENT-CONSTRAINT: Faktor() || VerhältnisZu() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor entweder Faktor() oder VerhältnisZu()
    //SCHLECHT: ERROR: Da Plus1Jahr(), Faktor() & VehältnisZu() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //          ERROR: In den Untertypen wird das gleiche Problem entstehen. Verbesserung: aus Einflüsse Interface machen
    protected float[] monatlicheWerte;
    protected float[] normWerte;

    //VORB: mw.length == 12 & nW.length == 12
    public Einflüsse(float[] mW, float[] nW){
        monatlicheWerte = mW;
        normWerte = nW;
    }

    //VORB: abweichungen.length == 12 & abweichungen[i] > 0.0f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
    }

    //VORB: abweichungsgrad >= 0.0f & grenzwertAbw > abweichungsgrad
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
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

    //VORB: min >= 0.0f & max > min & ei != null
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
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
