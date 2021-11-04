import java.util.ArrayList;

public class Forst {
    private Population wald1, wald2;

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
        }
    }

    public String toString() {
        boolean b = wald2 != null;
        return "Ihr Waldbestand: " + (b ? "2 " : "1 ") + "Komponente" + (b ? "n:" : ":") + wald1.getClass() + (b ? wald2.getClass() + "." : ".") + "\n" + wald1.toString() + (b ? "\n" + wald2.toString() + "." : ".");

    }


}
