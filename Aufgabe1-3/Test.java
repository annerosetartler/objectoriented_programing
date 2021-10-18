import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        ArrayList<Float> Übergabelist = new ArrayList<Float>();
        ArrayList<Float> altersS = new ArrayList<Float>();
        Random rando = new Random();
        float sum = 0;
        for (int i = 0; i < 250; i++) {
            float a = rando.nextFloat();
            sum += a;
            Übergabelist.add(a);
        }

        for (Float f : Übergabelist) {
            altersS.add(f / sum);
        }
        Wald testWald1 = new Wald(altersS, 100.0f, 250.0f);
        Wald testWald2 = new Wald(testWald1);
        Simulation testSim = new Simulation(1000, testWald1, testWald2);
        testSim.simLoop();
    }
}


/*
Arbeitsteilung:
Konzeption der Faktorisierung und Methoden: Annerose, David und Maria
Implementierung der Klasse Wald: Annerose, David und Maria
Implementierung der Klassen Test, Simulation, Modell, Naturbelassen & Bewirtschaftet: David und Maria
Überarbeitung der Klasse Wald: Annerose
Kommentare und Ausgabe: Maria
Inputgenerierung in Testklasse: David
 */
