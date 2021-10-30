public class Temperatur extends Einflüsse{
    //Quelle: https://www.wien.gv.at/statistik/lebensraum/tabellen/lufttemperatur.html
    //Beispielarray Normalwerte: [0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f]//Länge 12 Betrachtungszeitraum: 1981-2010 in °C
    //Beispielarray Werte Jahr 2020: [1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f]
    private static final float[] normW = new float[]{0.3f,1.5f,5.7f,10.7f,15.7f,18.7f,20.8f,20.2f,15.4f,10.2f,5.1f,1.1f};

    public Temperatur(float[] mW){
        super(mW,normW);
    }

    public float Hitze(){
        return Faktor(1.2f,3.5f);
    }
}
