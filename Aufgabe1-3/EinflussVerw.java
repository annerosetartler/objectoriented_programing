import java.util.Arrays;
import java.util.Random;

public class EinflussVerw {
    private Sonne sonne;
    private Niederschlag regen;
    private Temperatur temp;
    private Wind wind;
    private float klimawandel;
    private float[] faktoren = new float[4]; //[Hitze,Mure,Sturm,Zuwachs]
    private float[] abweichungen = new float[12];

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

    private void GeneriereAbweichungen(float kw){
        Random r = new Random();
        for (int i = 0; i < abweichungen.length; i++) {
            abweichungen[i] = (float) (r.nextGaussian() * 0.5 + kw);
            if(abweichungen[i] <= 0.5f){
                abweichungen[i] = 0.5f;
            }
        }
    }

    private void AktualisiereFaktoren(){
        faktoren[0] = temp.Hitze();
        faktoren[1] = regen.Mure();
        faktoren[2] = wind.Sturm();
        faktoren[3] = sonne.SonneZuRegen(regen);
    }

    public float[] Plus1Jahr(){
        GeneriereAbweichungen(klimawandel);
        sonne.Plus1Jahr(abweichungen);
        GeneriereAbweichungen(klimawandel);
        regen.Plus1Jahr(abweichungen);
        GeneriereAbweichungen(klimawandel);
        temp.Plus1Jahr(abweichungen);
        GeneriereAbweichungen(klimawandel);
        wind.Plus1Jahr(abweichungen);
        klimawandel += 0.001f;
        AktualisiereFaktoren();
        return faktoren;
    }

    public String toString(){
        return "Sonne: " + sonne + "\n" + "Niederschlag: " + regen + "\n" + "Temperatur: " + regen + "\n" + "Wind: " + wind + "\n" + "Faktoren [Hitze, Mure, Sturm, Zuwachs]: " + Arrays.toString(faktoren);
    }

}
