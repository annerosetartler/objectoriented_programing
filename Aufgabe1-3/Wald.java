import java.util.ArrayList;

public class Wald {

    //vars
    private float baumBestand;//in Festmetern fm
    private ArrayList<Float> altersStruktur;
    private float gesundheit; //wertebereich [0.25,1.0], klein besser
    private float zielbestand;//in Festmetern fm
    private float ernte; //in Festmetern fm
    private float co2Vorrat; //in Tonnen t
    private float ausfall;// in %
    private float zuwachs;// in Festmetern fm

    public Wald(ArrayList<Float> as, float bB, float zb){
        altersStruktur = as;
        baumBestand = bB;
        zielbestand = zb;
        gesundheit = calcGesundheit();
        ernte = 0.0f;
        co2Vorrat = baumBestand;
        ausfall = 0.0f;
        zuwachs = 0.0f;
    }

    private float calcGesundheit(){
        int counter = 0;
        for (Float f: altersStruktur) {
            if(f <= 0.0000001){
                counter++;
            }
        }
        return 0.25f + (0.75f/251)*counter;
    }

    private void calcAusfall(float afaktor){
        ausfall = afaktor*gesundheit;
    }

    private void calcZuwachs(float zfaktor){
        zuwachs = zielbestand*zfaktor - ausfall*baumBestand;
    }

    private void updateBaumbestand(){
       baumBestand += zuwachs;
    }

    private void altersStrukturPlusOneYear(){
        for (Float f:altersStruktur) {
            f *= (1-ausfall);
        }
        altersStruktur.add(0,ausfall);
        altersStruktur.remove(251);
    }

    private void adaptNaturZielbestand(){
        if(ausfall >= 0.3){
            zielbestand*=(1-ausfall);
        }else if(Float.compare(zielbestand,245.0f) <= 0){
            zielbestand += 5.0f;
        }else{
            zielbestand = 250.0f;
        }
    }

    private void adaptBewZielbestand(){
        if(ausfall >= 0.3){
            zielbestand*=(1-ausfall);
        }else if(Float.compare(zielbestand,345.0f) <= 0){
            zielbestand += 5.0f;
        }else{
            zielbestand = 350.0f;
        }
    }

    private void calcCO2(){
        co2Vorrat+=zuwachs;
        if(ausfall < 0.3){
            co2Vorrat+=baumBestand*ausfall/3;
        }else{
            co2Vorrat*=(1-(ausfall/2));
        }
    }

    private void ernteBew(int[] counter, float afaktor){ //counter zählt in der simulation mit
        if(ausfall >= 0.1 && ausfall < 0.3){
            float sumAnteil = 0.0f;
            for (int i = 0; i < altersStruktur.size() ; i++) {
                if(i >= 46){
                    sumAnteil += altersStruktur.get(i);
                    altersStruktur.set(i,0.0f);
                }
            }
            ernte += (sumAnteil*baumBestand + ausfall*baumBestand)/2;
            baumBestand -= sumAnteil*baumBestand;
            gesundheit = calcGesundheit();
            calcAusfall(afaktor);
        }else if(ausfall < 0.1 && counter[0] == 11){
            float sumAnteil = 0.0f;
            for (int i = 0; i < altersStruktur.size() ; i++) {
                if(i >= 75){
                    sumAnteil += altersStruktur.get(i);
                    altersStruktur.set(i,0.0f);
                }
            }
            ernte += (sumAnteil*baumBestand*2)/3;
            baumBestand -= sumAnteil*baumBestand;
            gesundheit = calcGesundheit();
            calcAusfall(afaktor);
            counter[0] = 0;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "baumBestand=" + baumBestand +
                ", altersStruktur=" + altersStruktur +
                ", gesundheit=" + gesundheit +
                ", zielbestand=" + zielbestand +
                ", ernte=" + ernte +
                ", co2Vorrat=" + co2Vorrat +
                '}';
    }
}
