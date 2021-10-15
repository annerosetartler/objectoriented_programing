import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<Float> altersS = new ArrayList<Float>();

        for (int i = 0; i < 10; i++) {
            altersS.add(0.1f);
        }
        //System.out.println(altersS.toString());
        Wald testWald1 = new Wald(altersS,100.0f,250.0f);
        Wald testWald2 = new Wald(testWald1);
        Simulation testSim = new Simulation(1000,testWald1,testWald2);
        testSim.simLoop();
    }
}
