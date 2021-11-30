import java.util.Arrays;

public class Sonne implements Einfluesse {
    //KOMMENTAR: Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/luftsonne.html
    //           Normwerte: [69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f] Betrachtungszeitraum: 1981-2010 in h
    //           Werte Jahr 2020: [69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f]
    //CLIENT-CONSTRAINT: SonneZuRegen() darf nicht vor Plus1Jahr() aufgerufen werden
    //                   Plus1Jahr() muss einmal aufgerufen werden vor SonneZuRegen()
    //SCHLECHT: ERROR: Da Plus1Jahr() & SonneZuRegen() public/protected sind, kann CLIENT-CONSTRAINT gebrochen werden!
    //                 Verbesserung: Methoden private machen und eine public-Methode machen, die Reihenfolge sicherstellt
    //INV: normW.length == 12 & normW[i] bleiben unverändert
    private float[] monatlicheWerte;
    private static final float[] normWerte = new float[]{69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f};

    //VORB: mW.length == 12
    public Sonne(float[] mW){
        monatlicheWerte = mW;
    }

    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float Faktor(){
        float abweichungsgrad = 1.5f;
        float grenzwertAbw = 2.0f;
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

    //VORB: ei != null & ei ist vom Typ Niederschlag
    //NACHB: gibt einen Wert in [0.0,1.0] zurück
    public float VerhältnisZu(Einfluesse ei){
        float[] eiMw = ei.getMonatlicheWerte();
        float min = 2.0f;
        float max = 6.0f;
        float verhSum = 0.0f;
        for (int i = 0; i < monatlicheWerte.length; i++) {
            verhSum += monatlicheWerte[i]/eiMw[i];
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

    //VORB: abweichungen.length == 12 & abweichungen[i] > 0.0f
    //NACHB: verändert die Werte in monatlicheWerte
    //       entsprechend den dazugehörigen Werten in normWerte & in abweichungen
    public void Plus1Jahr(float[] abweichungen){
        for (int i = 0; i < monatlicheWerte.length; i++) {
            monatlicheWerte[i] = normWerte[i] * abweichungen[i];
        }
    }

    //NACHB: gibt das Array mit den monatlichen Werten zurück
    public float[] getMonatlicheWerte(){
        return monatlicheWerte;
    }

    public String toString(){
        return Arrays.toString(monatlicheWerte);
    }
}
