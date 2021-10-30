public class Wind extends Einflüsse{
    //Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/wind-monat.html
    //Beispielarray Normalwerte: [13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f]//Länge 12 Betrachtungszeitraum: 1981-2010 in km/h
    //Beispielarray Werte Jahr 2020: [10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f]
    private static final float[] normW = new float[]{13.7f,14.0f,14.0f,13.3f,13.0f,13.0f,12.6f,11.5f,11.9f,11.5f,13.0f,13.7f};

    public Wind(float[] mW){
        super(mW,normW);
    }

    public float Sturm(){
        return Faktor(1.15f,1.5f);
    }
}
