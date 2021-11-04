import java.util.ArrayList;

public class Forst {
    private Population wald1, wald2;
    private ArrayList<Float> gesAS;
    private float baumGes;

    //Initialisiert einen Forst, der eine Waldart hat
    public Forst(ArrayList<Float> as, float bB, float zB, int baumart){
        if (baumart == 0){
            wald1 = new Fichte(as, bB, zB);
        }
        else {
            wald1 = new Buche(as, bB, zB);
        }

        wald2 = null;
    }

    //Initialisiert einen Forst, der zwei Waldarten hat
    public Forst(ArrayList<Float> as1, float bB1, float zB1, int baumart1, ArrayList<Float> as2, float bB2, float zB2, int baumart2){
        if (baumart1 == 0){
            wald1 = new Fichte(as1, bB1, zB1);
        }
        else {
            wald1 = new Buche(as1, bB1, zB1);
        }
        if (baumart2 == 0){
            wald2 = new Fichte(as2, bB2, zB2);
        }
        else {
            wald2 = new Buche(as2, bB2, zB2);
        }
    }

    //Initialisiert einen Forst, der eine Waldart hat
    public Forst(Population w1){
        wald1 = w1;
        wald2 = null;
    }

    //Initialisiert einen Forst, der zwei Waldarten hat
    public Forst(Population w1, Population w2){
        wald1 = w1;
        wald2 = w2;
    }

    //ich gebe derzeit hier jedem den halben zielbestand, wenn es zwei Waldteile gibt... => evtl. ändern?
    public void plusEinJahr(float[] einflussArray, float[] wirtschaftsfaktoren,float maxZielb){

        wald1.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb / (wald2 != null? 1 : 2), (wald2 != null));

        if (wald2 != null){
            wald2.plusEinJahr(einflussArray, wirtschaftsfaktoren, maxZielb/2, true);
            plenter(wirtschaftsfaktoren);

            berGesamtAS();
            setzeGesamtGesundheit(); //Gesundheit, die für die einzelnen Teile gelten würde, wird hier überschrieben!!!
        }
    }

    public String toString() {
        boolean b = wald2 != null;
        /*
        return "Ihr Waldbestand: " + (b ? "2 " : "1 ") + "Komponente" + (b ? "n:" : ":") + wald1.getClass() + (b ? wald2.getClass() + "." : ".") + "\n" + wald1.toString() + (b ? "\n" + wald2.toString() + "." : ".");
       */
        //Oder, wenn ich den Gesamtwald ausgeben will:
        String s = "Ihr Waldbestand:" + (b ? "2 " : "1 ") + "Komponente" + (b ? "n:" : ":") + wald1.getClass() +
                (b ? "und " + wald2.getClass() + "." : ".");
        if (!b){
            s += wald1.toString();
        }
        else {
            s += "Baumbestand: " + String.format("%6.2f", wald1.baumBestand + wald2.baumBestand) + "\t\tGesundheit: " + String.format("%6.2f", baumGes) +
                    "\t\tZielbestand: " + String.format("%6.2f", wald1.zielbestand + wald2.zielbestand) + "\t\tErnte: " + String.format("%6.2f", wald1.ernte + wald2. ernte) +
                    "\t\tCO2-Vorrat: " + String.format("%6.2f", wald1.co2Vorrat + wald2.co2Vorrat);
            /*
            s += "; Altersstruktur: [ ";
            for (int i = 0; i < gesAS.size() - 1; i++) {
                float fm = gesAS.get(i) * wald1.baumBestand + wald2.baumBestand;
                if (i == gesAS.size() - 1) {
                    s += "" + fm + " ]; ";
                } else {
                    s += "" + fm + ", ";
                }
            }
            s += " ]";
             */
        }
        return s;
    }

    private void berGesamtAS(){
        for (int i = 0; i < wald1.altersStruktur.size(); i++) {
            gesAS.set(i, (wald1.altersStruktur.get(i) * wald1.baumBestand + wald2.altersStruktur.get(i) * wald2.baumBestand) / (wald1.baumBestand + wald2.baumBestand));
        }
    }

    private void setzeGesamtGesundheit(){
        int space = 0;
        float idealwert = 1.0f / (gesAS.size() * 2);
        for (Float f : gesAS) {
            if (f < idealwert) {
                space++;
            }
        }
        baumGes = 0.25f + ((0.75f / (float) (gesAS.size())) * (float) (space));

        wald1.setGesundheit(baumGes);
        wald2.setGesundheit(baumGes);
    }

    private void plenter(float[] wirtschaftsfaktoren){
        if (wald1.baumBestand < wald1.baumBestand + wald2.baumBestand * wirtschaftsfaktoren[2]){
            wald1.plenterernte((wald1.baumBestand + wald2.baumBestand) /2);
            wald2.plenterernte((wald1.baumBestand + wald2.baumBestand) /2);
        }else if (wald2.baumBestand < wald1.baumBestand + wald2.baumBestand * wirtschaftsfaktoren[2]){
            wald1.plenterernte((wald1.baumBestand + wald2.baumBestand) /2);
            wald2.plenterernte((wald1.baumBestand + wald2.baumBestand) /2);
        }
    }

}
