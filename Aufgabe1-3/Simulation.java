public class Simulation {


    private float[] einflussArray; //[Hitze, Mure, Sturm, Zuwachs], jeweils Faktoren zwischen [0.0, 1.0]
    private float[] wirtschaftsArray; //
    private Population w1, w2, w3;
    private int years;
    private int Modell; // 0 steht für Naturbelassen, 1 steht für Kahlschlag, 2 steht für Erholungsgebiet und 3 für PLenterwirtschaft

    //pre: y >= 0 & w1 != null & w2 != null
    public Simulation(int y, Population w1, Population w2) {
        this.w1 = w1;
        this.w2 = w2;
        years = y;
    }

    public void simLoop() {
        Population population1 = new Buche(w1); //Derzeit kann der Wald seine Art ändern!?
        Population population2 = new Fichte(w2);
        Forst testForst = new Forst(w1);

        System.out.println("Year: 0" + "\n----------------------------------------" + "\n" + testForst.toString() + "\n----------------------------------------"); //Bewirtschaftungsmodell-Name?
        for (int i = 1; i <= years; i++) {
            float[] einflussfaktoren = einflussArray; //stattdessen Berechnung von Maria;
            float[] wirtschaftsfaktoren = wirtschaftsArray; //stattdessen Berechnung von David;

            testForst.plusEinJahr(einflussfaktoren, wirtschaftsfaktoren, wirtschaftsfaktoren[3]); //ISt wirtschaftsfaktoren[3] zielbestand?
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

    public String toString(){
        return "Relevante Komponenten auf das Modell in diesem Jahr: " + "\n" + "Bewirtschaftungsmodell: " + modellName()  + "\n" + "Witterungseinflüsse: " + starkeEinflüsse();
    }

    private String modellName(){
        String s = "";
        /* ToDo Noch schreiben, welches Modell es wann ist, und das hier füttern
        if (wirtschaftsArray[0] == 0 && wirtschaftsArray[3] > 0){

        }
        */
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

    // Methode nur zum testen!
    public void testSimLoop(float[] einflussArray) {
        Bewirtschaftungsmodell natur = new Naturbelassen(w1);
        Bewirtschaftungsmodell bew = new Bewirtschaftet(w2);
        System.out.println("Year: " + 0 + ": " + natur.toString());
        System.out.println("Year: " + 0 + ": " + bew.toString());
        natur.plusEinJahr();
        bew.plusEinJahr();
        System.out.println("Year: " + 1 + ": " + natur.toString());
        System.out.println("Year: " + 1 + ": " + bew.toString());
    }
}