public class Simulation {


    private float[] einflussArray; //[Hitze, Mure, Sturm, Zuwachs], jeweils Faktoren zwischen [0.0, 1.0]
    private float[] wirtschaftsArray; //
    private Population w1, w2;
    private int years;
    private int Modell; // 0 steht für Naturbelassen, 1 steht für Kahlschlag, 2 steht für Erholungsgebiet und 3 für PLenterwirtschaft

    //pre: y >= 0 & w1 != null & w2 != null
    public Simulation(int y, Population w1, Population w2) {
        this.w1 = w1;
        this.w2 = w2;
        years = y;
    }

    public void simLoop(EinflussVerw e, Bewirtschaftungsmodell m, boolean mischwald, float zB) {
        Forst testForst;
        if (mischwald){
            testForst = new Forst(w1, w2);
        }
        else {
             testForst = new Forst(w1);
        }


        System.out.println("Year: 0" + "\n----------------------------------------" + "\n" + testForst.toString() + "\n----------------------------------------"); //Bewirtschaftungsmodell-Name?
        for (int i = 1; i <= years; i++) {
            float[] einflussfaktoren =  e.Plus1Jahr();
            float[] wirtschaftsfaktoren = m.plusEinJahr();

            testForst.plusEinJahr(einflussfaktoren, wirtschaftsfaktoren, zB); //ISt wirtschaftsfaktoren[3] zielbestand?

            if (i % 100 == 0) {
                System.out.println("Year: " + i + "\n----------------------------------------" + "\n" + testForst.toString() + "\n----------------------------------------"
                        + toString()) ;
            }
        }

        /*
        Bewirtschaftungsmodell natur = new Naturbelassen(w1);
        Kahlschlag bew = new Kahlschlag(w2);
        System.out.println("Year: 0" + "\n----------------------------------------" + "\n" + natur.toString() + "\n" + bew.toString() + "\n----------------------------------------");
        for (int i = 1; i <= years; i++) {

            //einfluss-Array-Übergabe einbauen (Maria)
            natur.plusEinJahr();
            bew.plusEinJahr();
            if (i % 100 == 0) {
                System.out.println("Year: " + i + "\n----------------------------------------" + "\n" + natur.toString() + "\n" + bew.toString() + "\n----------------------------------------");
            }
        }
         */
    }

    /*
    public String toString(){
        return "Relevante Komponenten auf das Modell in diesem Jahr: " + "\n" + "Bewirtschaftungsmodell: " + modellName()  + "\n" + "Witterungseinflüsse: " + starkeEinflüsse();
    }

    private String modellName(){
        String s = "";

        return s;
    }

    private String starkeEinflüsse(){
        String s = "";
        if (einflussArray[0] > 0.6) { //ToDo: welchen Wert?
            s += "Hitze ";
        }
        if (einflussArray[0] > 0.6) { //ToDo: welchen Wert?
            s += "Mure ";
        }
        if (einflussArray[0] > 0.6) { //ToDo: welchen Wert?
            s += "Sturm ";
        }
        else {
            s = "Keine ausschlaggebenden Faktoren";
        }
        return s;
    }
   */

    // Methode nur zum testen!
    public void testSimLoop(float[] einflussArray, float[] wirtschaftsFaktoren,float zB) {
        Forst testForst = new Forst(w1);
        System.out.println("Year: " + 0 + ": " + testForst.toString());
        testForst.plusEinJahr(einflussArray, wirtschaftsFaktoren, zB);
        System.out.println("Year: " + 1 + ": " + testForst.toString());
    }
}