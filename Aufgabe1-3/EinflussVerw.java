import java.util.Arrays;
import java.util.Random;

public class EinflussVerw {
    //SCHLECHT: kein dynamisches Binden der Einfluesse, Verbesserung: aus Einfluesse ein Interface machen, das von Sonne,
    //          Wind, Temperatur & Niederschlag implementiert wird
    //INV: faktoren.length == 4 & abweichungen.length == 12 & klimawandel >= 1.0f & abweichungen[i] >= 0.5f
    private Sonne sonne;
    private Niederschlag regen;
    private Temperatur temp;
    private Wind wind;
    private float klimawandel;
    //KOMMENTAR: faktoren[] steht für [Hitze,Mure,Sturm,Zuwachs]
    private float[] faktoren = new float[4];
    private float[] abweichungen = new float[12];

    //VORB: s != null & n != null & t != null & w != null
    public EinflussVerw(Sonne s, Niederschlag n, Temperatur t, Wind w){
        sonne = s;
        regen = n;
        temp = t;
        wind = w;
        klimawandel = 1.01f;
        faktoren[0] = temp.Hitze();
        faktoren[1] = regen.Mure();
        faktoren[2] = wind.Sturm();
        faktoren[3] = sonne.SonneZuRegen(regen);
    }

    //VORB: kw >= 1.0f
    //NACHB: abweichungen[i] >= 0.5f
    //ERROR: kw ist redundant, könnte direkt auf die Objektvariable klimawandel zugreifen
    private void GeneriereAbweichungen(float kw){
        Random r = new Random();
        for (int i = 0; i < abweichungen.length; i++) {
            abweichungen[i] = (float) (r.nextGaussian() * 0.5 + kw);
            if(abweichungen[i] <= 0.5f){
                abweichungen[i] = 0.5f;
            }
        }
    }

    //NACHB: faktoren[i] in [0.0,1.0]
    private void AktualisiereFaktoren(){
        faktoren[0] = temp.Hitze();
        faktoren[1] = regen.Mure();
        faktoren[2] = wind.Sturm();
        faktoren[3] = sonne.SonneZuRegen(regen);
    }

    //SERVER-CONSTRAINTS: klimawandel wird mit jedem Jahr um 0.0001f erhöht
    public float[] Plus1Jahr(){
        GeneriereAbweichungen(klimawandel);
        sonne.Plus1Jahr(abweichungen);
        GeneriereAbweichungen(klimawandel);
        regen.Plus1Jahr(abweichungen);
        GeneriereAbweichungen(klimawandel);
        temp.Plus1Jahr(abweichungen);
        GeneriereAbweichungen(klimawandel);
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
