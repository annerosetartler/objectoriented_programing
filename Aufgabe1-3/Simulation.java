public class Simulation {

    //INV: years >= 0
    private float[] einflussfaktoren; //KOMMENTAR: [Hitze, Mure, Sturm, Zuwachs]
    private float[] wirtschaftsfaktoren;
    private Population w1, w2;
    private int years;

    //VORB: y >= 0 & w1 != null & w2 != null
    public Simulation(int y, Population w1, Population w2) {
        this.w1 = w1;
        this.w2 = w2;
        years = y;
    }

    //VORB: e != null & m != null & zB > 0
    //NACHB: einflussfaktoren != null & wirtschaftsfaktoren != null & (einflussfaktoren.length == 4 & Werte in [0.0,1.0])
    //       & (wirtschaftsfaktoren.length == 4 & Werte in [0.0,1.0])
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
            einflussfaktoren =  e.Plus1Jahr();
            wirtschaftsfaktoren = m.plusEinJahr();

            testForst.plusEinJahr(einflussfaktoren, wirtschaftsfaktoren, zB);

            if (i % 100 == 0) {
                System.out.println("Year: " + i + "\n----------------------------------------" + "\n" + testForst.toString() + "\n----------------------------------------"
                        + toString()) ;
            }
        }
    }


    public String toString(){
        return "\n" + "Relevante Komponenten auf das Modell in diesem Jahr: " + "\n" + "Bewirtschaftungsmodell: " + modellName()  + "\n" +
                "Witterungseinflüsse: " + starkeEinflüsse() + "\n---------------------------------------- \n";
    }

    //VORB: wirtschaftsfaktorn != null & wirtschaftsfaktorn.length == 4 & Werte in [0.0,1.0]
    private String modellName(){
        String s = "";
        if (wirtschaftsfaktoren[0] > 0.8f){
            s = "Kahlschlag-";
        }
        else if (wirtschaftsfaktoren[1] > 0.8f){
            s = "Plenterwirtschafts-";
        }
        else {
            s = "Naturbelassenes ";
        }
        return s + "Modell";
    }

    //VORB: einflussfaktorn != null & einflussfaktoren.length == 4 & Werte in [0.0,1.0]
    private String starkeEinflüsse(){
        String s = "";
        if (einflussfaktoren[0] > 0.6f) {
            s += "Hitze ";
        }
        if (einflussfaktoren[1] > 0.6f) {
            s += "Mure ";
        }
        if (einflussfaktoren[2] > 0.6f) {
            s += "Sturm ";
        }
        else {
            s = "Keine ausschlaggebenden Faktoren";
        }
        return s;
    }

    //VORB: einflussArray.length == 4 & Werte in einflussArray in [0.0,1.0]
    //      wirtschaftsFaktoren.length == 4 & Werte in wirtschaftsFaktoren in [0.0,1.0]
    //      zB > 0
    public void testSimLoop(float[] einflussArray, float[] wirtschaftsFaktoren,float zB) {
        Forst testForst = new Forst(w1);
        System.out.println("Year: " + 0 + ": " + testForst.toString());
        testForst.plusEinJahr(einflussArray, wirtschaftsFaktoren, zB);
        System.out.println("Year: " + 1 + ": " + testForst.toString());
    }
}