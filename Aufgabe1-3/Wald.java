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
    private int[] counter;

    public Wald(ArrayList<Float> as, float bB, float zb){
        altersStruktur = as;
        baumBestand = bB;
        zielbestand = zb;
        calcGesundheit();
        ernte = 0.0f;
        co2Vorrat = baumBestand;
        ausfall = 0.0f;
        zuwachs = 0.0f;
        counter = new int[]{0};
    }

    public void calcGesundheit(){
        int space = 0;
        for (Float f: altersStruktur) {
            if(f <= 0.0000001){
                space++;
            }
        }
        gesundheit = 0.25f + (0.75f/altersStruktur.size())*space;
    }

    public void calcAusfall(float afaktor){
        ausfall = afaktor*gesundheit;
    }

    public void calcZuwachs(float zfaktor){
        zuwachs = zielbestand*zfaktor - ausfall*baumBestand;
    }

    public void updateBaumbestand(){
       baumBestand += zuwachs;
    }

    public void altersStrukturPlusOneYear(){
        for (Float f:altersStruktur) {
            f *= (1-ausfall);
        }
        altersStruktur.add(0,ausfall);
        altersStruktur.remove(altersStruktur.size()-1);
    }

    public void adaptNaturZielbestand(){
        if(ausfall >= 0.3){
            zielbestand*=(1-ausfall);
        }else if(Float.compare(zielbestand,245.0f) <= 0){
            zielbestand += 5.0f;
        }else{
            zielbestand = 250.0f;
        }
    }

    public void adaptBewZielbestand(){
        if(ausfall >= 0.3){
            zielbestand*=(1-ausfall);
        }else if(Float.compare(zielbestand,345.0f) <= 0){
            zielbestand += 5.0f;
        }else{
            zielbestand = 350.0f;
        }
    }

    public void calcCO2(){
        co2Vorrat+=zuwachs;
        if(ausfall < 0.3){
            co2Vorrat+=baumBestand*ausfall/3;
        }else{
            co2Vorrat*=(1-(ausfall/2));
        }
    }

    public void ernteBew(float afaktor){ //counter zählt in der simulation mit
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
            calcGesundheit();
            calcAusfall(afaktor);
            counter[0]=0;
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
            calcGesundheit();
            calcAusfall(afaktor);
            counter[0] = 0;
        }else if(ausfall < 0.1 && counter[0] < 11){
            counter[0]++;
        }else if(ausfall >= 0.3){
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
