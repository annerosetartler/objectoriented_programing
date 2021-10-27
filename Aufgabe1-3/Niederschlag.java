public class Niederschlag extends Einflüsse {
    //Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/niederschlag.html
    //Beispielarray Normalwerte: [37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f]//Länge 12 Betrachtungszeitraum: 1981-2010 in mm Wasserhöhe
    //Beispielarray Werte Jahr 2020: [19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f]
    private static final float[] normW = new float[]{37.9f,40.1f,51.4f,44.7f,69.0f,70.0f,70.0f,72.0f,60.8f,37.8f,48.6f,48.2f};

    public Niederschlag(float[] mW){
        super(mW,normW);
    }

    public float Mure(){
        return Faktor(1.5f,3.0f);
    }

    public float Wasser(){
        return Summe();
    }
}
