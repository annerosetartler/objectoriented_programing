import java.lang.reflect.Array;
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

    public Wald(ArrayList<Float> as, float bB, float zb) {
        altersStruktur = as;
        baumBestand = bB;
        zielbestand = zb;
        calcGesundheit();
        ernte = 0.0f;
        co2Vorrat = baumBestand;
        ausfall = 0.0f;
        zuwachs = 0.0f;
    }

    public Wald(Wald w) {
        this.baumBestand = w.baumBestand;
        altersStruktur = new ArrayList<Float>();
        for (int i = 0; i < w.altersStruktur.size(); i++) {
            float f = w.altersStruktur.get(i);
            altersStruktur.add(f);
        }
        this.gesundheit = w.gesundheit;
        this.zielbestand = w.zielbestand;
        this.ernte = w.ernte;
        this.co2Vorrat = w.co2Vorrat;
        this.ausfall = w.ausfall;
        this.zuwachs = w.zuwachs;
    }

    public void annualCalcNat(float afaktor, float zfaktor, float maxZielb) {
        calcAusfall(afaktor);
        calcZuwachs(zfaktor);
        updateBaumbestand();
        altersStrukturPlusOneYear();
        calcGesundheit();
        updateZielbestand(maxZielb);
        calcCO2();
    }

    public void annualCalcBew(float afaktor, float zfaktor, float maxZielb, int[] c) {
        annualCalcNat(afaktor, zfaktor, maxZielb);
        ernteBew(afaktor,c,maxZielb);
        calcCO2();
    }

    private void calcGesundheit() {
        int space = 0;
        float idealValue = 1.0f / (altersStruktur.size()*2);
        for (Float f : altersStruktur) {
            if (f < idealValue) {
                space++;
            }
        }
        gesundheit = 0.25f + (0.75f / (float) (altersStruktur.size())) * (float) (space);
    }

    private void calcAusfall(float afaktor) {
        ausfall = afaktor * gesundheit;
    }

    private void calcZuwachs(float zfaktor) {
        zuwachs = zielbestand * zfaktor - ausfall * baumBestand;
    }

    private void updateBaumbestand() {
        baumBestand += zuwachs;
    }

    private void altersStrukturPlusOneYear() { ///
        altersStruktur.remove(altersStruktur.size() - 1);
        float sumBestand = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            float f = altersStruktur.get(i);
            f *= (1.0f - ausfall);
            sumBestand += f;
            altersStruktur.set(i,f);
        }
        altersStruktur.add(0, 1.0f-sumBestand);
    }

    private void updateZielbestand(float maxZielb) {
        if (ausfall >= 0.3) {
            zielbestand *= (1 - ausfall);
        } else if (Float.compare(zielbestand, maxZielb-5) <= 0) {
            zielbestand += 5.0f;
        } else {
            zielbestand = maxZielb;
        }
    }

    private void calcCO2() {
        co2Vorrat += zuwachs;
        if (ausfall < 0.3) {
            co2Vorrat += baumBestand * ausfall / 3;
        } else {
            co2Vorrat *= (1 - (ausfall / 2));
        }
    }

    private void ernteBew(float afaktor,int[] c, float maxZielb) { //counter zählt in der simulation mit
        if (ausfall >= 0.1 && ausfall < 0.3) {
            float sumAnteil = updateAltersstruktur(46);

            ernte += (sumAnteil * baumBestand + ausfall * baumBestand) / 2;

            updateBaumGesAusfall(sumAnteil, afaktor, maxZielb);

            c[0] = 0;
        } else if (ausfall < 0.1 && c[0] == 11) {
            float sumAnteil = updateAltersstruktur(75);

            ernte += (sumAnteil * baumBestand * 2) / 3;

            updateBaumGesAusfall(sumAnteil, afaktor, maxZielb);

            c[0] = 0;
        }
        else if (ausfall < 0.1 && c[0] < 11) {
            c[0]++;
        } else if (ausfall >= 0.3) {
            c[0] = 0;
        }
    }

    private float updateAltersstruktur(int limitAge){ //Name?
        float sA = 0.0f;
        for (int i = 0; i < altersStruktur.size(); i++) {
            if (i >= limitAge) {
                sA += altersStruktur.get(i);
                altersStruktur.set(i, 0.0f);
            }
        }
        return sA;
    }

    private void updateBaumGesAusfall(float sumAnteil, float afaktor, float maxZielb) { //Name????
        baumBestand -= sumAnteil * baumBestand;
        calcGesundheit();
        calcAusfall(afaktor);
        updateZielbestand(maxZielb);
    }

    @Override
    public String toString() {
        String s = "[ Baumbestand: " + baumBestand + " Ausfall: " + ausfall + " Zuwachs: " + zuwachs + " Gesundheit: " + gesundheit + "; Zielbestand: " + zielbestand + "; Ernte: " + ernte + "; CO2-Vorrat: " + co2Vorrat + "; Altersstruktur: [ ";
        for (int i = 0; i < altersStruktur.size(); i++) {
            float fm = altersStruktur.get(i) * baumBestand;
            if (i == altersStruktur.size() - 1) {
                s += "" + fm + " ]; ";
            } else {
                s += "" + fm + ", ";
            }

        }
        s += " ]";
        return s;
    }
}
