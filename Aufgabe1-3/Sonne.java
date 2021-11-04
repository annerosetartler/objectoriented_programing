public class Sonne extends Einflüsse{
    //Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/luftsonne.html
    //Beispielarray Normalwerte: [69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f]//Länge 12 Betrachtungszeitraum: 1981-2010 in h
    //Beispielarray Werte Jahr 2020: [69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f]
    private static final float[] normW = new float[]{69.9f,100.3f,142.6f,197.1f,238.7f,236.2f,262.6f,251.4f,182.0f,132.6f,66.0f,51.3f};

    public Sonne(float[] mW){
        super(mW,normW);
    }

    public float SonneZuRegen(Einflüsse e){
        return VerhältnisZu(e,2.0f,6.0f);
    }
}
