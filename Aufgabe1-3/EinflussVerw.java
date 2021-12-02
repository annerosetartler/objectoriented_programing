import java.util.Arrays;
import java.util.Random;

public class EinflussVerw {
    //KOMMENTAR: verwaltet Werte aus 4 Einfluessen in einem Array faktoren[]
    //           faktoren[] steht für [Hitze,Mure,Sturm,Zuwachs]
    //INV:  faktoren.length == 4 & alle Werte in faktoren in [0.0,1.0]
    //      abweichungen.length == 12 & abweichungen[i] >= 0.5f
    //      klimawandel >= 1.0f
    private Einfluesse sonne;
    private Einfluesse regen;
    private Einfluesse temp;
    private Einfluesse wind;
    private float klimawandel;

    private float[] faktoren = new float[4];
    private float[] abweichungen = new float[12];

    //VORB: s != null & n != null & t != null & w != null
    public EinflussVerw(Einfluesse sonne, Einfluesse niederschlag, Einfluesse temperatur, Einfluesse wind){
        this.sonne = sonne;
        regen = niederschlag;
        temp = temperatur;
        this.wind = wind;
        klimawandel = 1.01f;
        faktoren[0] = temp.getFaktor();
        faktoren[1] = regen.getFaktor();
        faktoren[2] = this.wind.getFaktor();
        faktoren[3] = this.sonne.VerhaeltnisZu(regen);
    }

    //NACHB: abweichungen[i] >= 0.5f
    private void GeneriereAbweichungen(){
        Random r = new Random();
        for (int i = 0; i < abweichungen.length; i++) {
            abweichungen[i] = (float) (r.nextGaussian() * 0.5 + klimawandel);
            if(abweichungen[i] <= 0.5f){
                abweichungen[i] = 0.5f;
            }
        }
    }

    //NACHB: faktoren[i] in [0.0,1.0]
    private void AktualisiereFaktoren(){
        faktoren[0] = temp.getFaktor();
        faktoren[1] = regen.getFaktor();
        faktoren[2] = wind.getFaktor();
        faktoren[3] = sonne.VerhaeltnisZu(regen);
    }

    //NACHB: simuliert das Vergehen eines Jahres
    //       dabei werden für jeden Einfluss Abweichungen per Zufall(Gaußverteilt) generiert und mit diesen
    //       errechneten Abweichungen die jeweiligen Einfluesse aktualisiert
    //       die Werte in faktoren[] werden anschließend auch aktualisiert
    //       zurückgegeben wird faktoren (entsprechend der Invariante)
    //SERVER-CONSTRAINTS: klimawandel wird mit jedem Jahr um 0.0001f erhöht
    public float[] Plus1Jahr(){
        GeneriereAbweichungen();
        sonne.Plus1Jahr(abweichungen);
        GeneriereAbweichungen();
        regen.Plus1Jahr(abweichungen);
        GeneriereAbweichungen();
        temp.Plus1Jahr(abweichungen);
        GeneriereAbweichungen();
        wind.Plus1Jahr(abweichungen);
        klimawandel += 0.0001f;
        AktualisiereFaktoren();
        return faktoren;
    }

    //VORB: sonne != null & regen != null & temp != null & wind != null
    public String toString(){
        return "Sonne: " + sonne + "\n" + "Niederschlag: " + regen + "\n" + "Temperatur: " + temp + "\n" + "Wind: " + wind + "\n" + "Faktoren [Hitze, Mure, Sturm, Zuwachs]: " + Arrays.toString(faktoren);
    }

}
