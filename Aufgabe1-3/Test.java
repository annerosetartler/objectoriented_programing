import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        //Tests zu Einflüsse und EinflussVerw
        System.out.println("Tests zu Einflüsse:" + "\n");
        float[] sonne = new float[]{69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f};
        Sonne s = new Sonne(sonne);
        float[] temp = new float[]{1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f};
        Temperatur t = new Temperatur(temp);
        float[] regen = new float[]{19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f};
        Niederschlag n = new Niederschlag(regen);
        float[] wind = new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f};
        Wind w = new Wind(wind);

        istWertInBereich(s.SonneZuRegen(n),0.0f,1.0f);
        istWertInBereich(t.Hitze(),0.0f,1.0f);
        istWertInBereich(n.Mure(),0.0f,1.0f);
        istWertInBereich(w.Sturm(),0.0f,1.0f);

        /*
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
         */
    }

    public static void istWertInBereich(float erhalten, float min, float max) {
        if (erhalten >= min && erhalten <= max) {
            System.out.println("Test erfolgreich!");
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet Wert in: [" + min + ", " + max + "] / Erhalten: " + erhalten);
        }
    }

    public static void testeWerte(float erhalten, float erwartet, float delta) {
        if (erhalten - delta <= erwartet && erhalten + delta >= erwartet) {
            System.out.println("Test erfolgreich!");
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet: " + erwartet + " / Erhalten: " + erhalten);
        }
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
