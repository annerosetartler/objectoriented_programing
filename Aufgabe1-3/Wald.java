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

    public Wald(ArrayList<Float> as, float bB, float zb) {
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

    public Wald(Wald w) {
        this.baumBestand = w.baumBestand;
        this.altersStruktur = w.altersStruktur;
        this.gesundheit = w.gesundheit;
        this.zielbestand = w.zielbestand;
        this.ernte = w.ernte;
        this.co2Vorrat = w.co2Vorrat;
        this.ausfall = w.ausfall;
        this.zuwachs = w.zuwachs;
        this.counter = w.counter;
    }

    public void annualCalcNat(float afaktor, float zfaktor, float systemVar) {
        calcAusfall(afaktor);
        calcZuwachs(zfaktor);
        updateBaumbestand();
        altersStrukturPlusOneYear();
        calcGesundheit();
        adaptZielbestand(systemVar);
        calcCO2();
    }

    public void annualCalcBew(float afaktor, float zfaktor, float systemVar) {
        annualCalcNat(afaktor, zfaktor, systemVar);
        ernteBew(afaktor);
        adaptZielbestand(systemVar); //Nochmal durchdenken, kanns nicht sein, dass wir dann den einfach zweimal hochsetzen? Sollte nicht passieren...
        calcCO2();
    }

    private void calcGesundheit() {
        int space = 0;
        float idealValue = 1.0f / altersStruktur.size();
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
        for (Float f : altersStruktur) {
            f *= (1 - ausfall);
        }
        altersStruktur.add(0, ausfall);
        altersStruktur.remove(altersStruktur.size() - 1);
    }

    private void adaptZielbestand(float systemVar) {
        if (ausfall >= 0.3) {
            zielbestand *= (1 - ausfall);
        } else if (Float.compare(zielbestand, systemVar-5) <= 0) {
            zielbestand += 5.0f;
        } else {
            zielbestand = systemVar;
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

    private void ernteBew(float afaktor) { //counter zählt in der simulation mit
        if (ausfall >= 0.1 && ausfall < 0.3) {
            float sumAnteil = 0.0f;

            updateAltersstruktur(46, sumAnteil);

            ernte += (sumAnteil * baumBestand + ausfall * baumBestand) / 2;

            adapt(sumAnteil, afaktor);

            resetCounter();
        } else if (ausfall < 0.1 && counter[0] == 11) {
            float sumAnteil = 0.0f;

            updateAltersstruktur(75, sumAnteil);

            ernte += (sumAnteil * baumBestand * 2) / 3;

            adapt(sumAnteil, afaktor);

            resetCounter();
        }
        else if (ausfall < 0.1 && counter[0] < 11) {
            counter[0]++;
        } else if (ausfall >= 0.3) {
            resetCounter();
        }
    }

    private void updateAltersstruktur(int limitAge, float sumAnteil){ //Name?
        for (int i = 0; i < altersStruktur.size(); i++) {
            if (i >= limitAge) {
                sumAnteil += altersStruktur.get(i);
                altersStruktur.set(i, 0.0f);
            }
        }
    }

    private void adapt(float sumAnteil, float afaktor) { //Name????
        baumBestand -= sumAnteil * baumBestand;
        calcGesundheit();
        calcAusfall(afaktor);
    }

    private void resetCounter(){
        counter[0] = 0;
    }

    @Override
    public String toString() {
        String s = "[ Baumbestand: " + baumBestand + "; Altersstruktur: [ ";
        for (int i = 0; i < altersStruktur.size(); i++) {
            float fm = altersStruktur.get(i) * baumBestand;
            if (i == altersStruktur.size() - 1) {
                s += "" + fm + " ]; ";
            } else {
                s += "" + fm + ", ";
            }

        }
        s += "Gesundheit: " + gesundheit + "; Zielbestand: " + zielbestand + "; Ernte: " + ernte + "; CO2-Vorrat: " + co2Vorrat + " ]";
        return s;
    }
}
