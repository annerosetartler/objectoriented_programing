public class Simulation {

    private float ausfallsFaktor;
    private float zuwachsFaktor;
    private Wald w1, w2;
    private int years;

    public Simulation(int y, Wald w1, Wald w2){
        this.w1 = w1;
        this.w2 = w2;
        years = y;
    }

    private void calcAFaktor (){
        if(Math.random()<0.95){
            ausfallsFaktor = (float) (Math.random()*0.08);
        }else{
            ausfallsFaktor = 0.08f + (float) (Math.random()*(1-0.08));
        }
    }

    private void calcZFaktor (){
        zuwachsFaktor = (float) (Math.random()*0.08);
    }

    public void simLoop(){
        Modell natur = new Naturbelassen(w1);
        Modell bew = new Bewirtschaftet(w2);
        System.out.println("Year: "+ 0 + ": " + natur.toString());
        System.out.println("Year: "+ 0 + ": " + bew.toString());
        for (int i = 1; i <= years; i++) {
            calcAFaktor();
            calcZFaktor();
            natur.plusOneYear(ausfallsFaktor,zuwachsFaktor);
            bew.plusOneYear(ausfallsFaktor,zuwachsFaktor);
            System.out.println("Year: "+ i + ": " + natur.toString());
            System.out.println("Year: "+ i + ": " + bew.toString());
            if(i%100 == 0){
                System.out.println("Year: "+ i + ": " + natur.toString());
                System.out.println("Year: "+ i + ": " + bew.toString());
            }
        }
    }

    public void testSimLoop(float aFaktor, float zFaktor){
        Modell natur = new Naturbelassen(w1);
        Modell bew = new Bewirtschaftet(w2);
        System.out.println("Year: "+ 0 + ": " + natur.toString());
        System.out.println("Year: "+ 0 + ": " + bew.toString());
        natur.plusOneYear(ausfallsFaktor,aFaktor);
        bew.plusOneYear(ausfallsFaktor,zFaktor);
        System.out.println("Year: "+ 1 + ": " + natur.toString());
        System.out.println("Year: "+ 1 + ": " + bew.toString());
    }
}
