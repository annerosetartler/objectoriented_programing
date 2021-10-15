public class Simulation {

    private float ausfallsFaktor;
    private float zuwachsFaktor;
    private Wald w;
    private int years;

    public Simulation(int y, Wald w){
        this.w = w;
        years = y;
    }

    private void calcAFaktor (){
        if(Math.random()<0.98){
            ausfallsFaktor = (float) (Math.random()*0.08);
        }else{
            ausfallsFaktor = 0.08f + (float) (Math.random()*(1-0.08));
        }
    }

    private void calcZFaktor (){
        zuwachsFaktor = (float) (Math.random()*0.08);
    }

    public void simLoop(){
        Modell natur = new Naturbelassen(w);
        Modell bew = new Bewirtschaftet(w);
        System.out.println(natur.toString());
        System.out.println(bew.toString());
        for (int i = 1; i <= years; i++) {
            calcAFaktor();
            calcZFaktor();
            natur.plusOneYear(ausfallsFaktor,zuwachsFaktor);
            bew.plusOneYear(ausfallsFaktor,zuwachsFaktor);
            if(i%100 == 0){
                System.out.println(natur.toString());
                System.out.println(bew.toString());
            }
        }
    }
}
