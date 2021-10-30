public class Simulation {


    private float[] einflussArray; //[Hitze, Mure, Sturm, Zuwachs], jeweils Faktoren zwischen [0.0, 1.0]
    private Wald w1, w2;
    private int years;

    //pre: y >= 0 & w1 != null & w2 != null
    public Simulation(int y, Wald w1, Wald w2) {
        this.w1 = w1;
        this.w2 = w2;
        years = y;
    }

    public void simLoop() {
        Bewirtschaftungsmodell natur = new Naturbelassen(w1);
        Bewirtschaftungsmodell bew = new Bewirtschaftet(w2);
        System.out.println("Year: 0" + "\n----------------------------------------" + "\n" + natur.toString() + "\n" + bew.toString() + "\n----------------------------------------");
        for (int i = 1; i <= years; i++) {

            //einfluss-Array-Übergabe einbauen (Maria)
            //ToDo: herauskommende Arrays müssen gespeichert werden und der Wald-OneYear jährlich übergeben werden, ebenso die Einflüsse im Einfluss-Array
            natur.plusOneYear(w1);
            bew.plusOneYear(w2);
            if (i % 100 == 0) {
                System.out.println("Year: " + i + "\n----------------------------------------" + "\n" + natur.toString() + "\n" + bew.toString() + "\n----------------------------------------");
            }
        }
    }

    // Methode nur zum testen!
    public void testSimLoop(float[] einflussArray) {
        Bewirtschaftungsmodell natur = new Naturbelassen(w1);
        Bewirtschaftungsmodell bew = new Bewirtschaftet(w2);
        System.out.println("Year: " + 0 + ": " + natur.toString());
        System.out.println("Year: " + 0 + ": " + bew.toString());
        natur.plusOneYear(w1);
        bew.plusOneYear(w2);
        System.out.println("Year: " + 1 + ": " + natur.toString());
        System.out.println("Year: " + 1 + ": " + bew.toString());
    }
}