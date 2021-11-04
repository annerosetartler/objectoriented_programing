import java.util.ArrayList;
import java.util.Arrays;
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
        float[] wind1 = new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f};
        Wind w1 = new Wind(wind1);

        System.out.println("Faktorenberechnung:");
        istWertInBereich(s.SonneZuRegen(n),0.0f,1.0f);
        istWertInBereich(t.Hitze(),0.0f,1.0f);
        float mure = n.Mure();
        istWertInBereich(mure,0.0f,1.0f);
        testeWerte(mure,1.0f,0.0000001f);
        istWertInBereich(w1.Sturm(),0.0f,1.0f);

        Wind w2 = new Wind(new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f});
        float[] abweichungen = new float[]{1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
        w2.Plus1Jahr(abweichungen);
        float sturm = w2.Sturm();
        testeWerte(sturm,1.0f,0.0000001f);
        abweichungen = new float[]{1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f};
        w2.Plus1Jahr(abweichungen);
        sturm = w2.Sturm();
        testeWerte(sturm,0.0f,0.0000001f);

        Sonne s1 = new Sonne(new float[]{100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f});
        Niederschlag n1 = new Niederschlag(new float[]{50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f});
        float verh1 = s1.SonneZuRegen(n1);
        testeWerte(verh1,0.0f,0.0000001f);

        Sonne s2 = new Sonne(new float[]{225.0f,225.0f,225.0f,225.0f,225.0f,225.0f,225.0f,225.0f,225.0f,225.0f,225.0f,225.0f});
        Niederschlag n2 = new Niederschlag(new float[]{50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f});
        float verh2 = s2.SonneZuRegen(n2);
        testeWerte(verh2,1.0f,0.0000001f);

        Sonne s3 = new Sonne(new float[]{300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f});
        Niederschlag n3 = new Niederschlag(new float[]{50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f});
        float verh3 = s3.SonneZuRegen(n3);
        testeWerte(verh3,0.0f,0.0000001f);

        System.out.println("----");
        System.out.println("EinflussVerw:");
        System.out.println("----");

        EinflussVerw e = new EinflussVerw(s,n,t,w1);
        System.out.println(e);
        System.out.println("----");
        System.out.println("Testsimulation EinflussVerw:");
        System.out.println("----");
        float[] faktoren = new float[4];
        for (int i = 1; i <= 5; i++) {
            faktoren = e.Plus1Jahr();
            System.out.println("Jahr: " + i);
            System.out.println(e);
            System.out.println(Arrays.toString(faktoren));
            System.out.println("\n");
        }

        float[] sonneA = new float[]{69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f};
        Sonne sA = new Sonne(sonneA);
        float[] tempA = new float[]{1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f};
        Temperatur tA = new Temperatur(tempA);
        float[] regenA = new float[]{19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f};
        Niederschlag nA = new Niederschlag(regenA);
        float[] windA = new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f};
        Wind wA = new Wind(windA);

        EinflussVerw eA = new EinflussVerw(sA,nA,tA,wA);

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

        Bewirtschaftungsmodell modell = new Naturbelassen(altersS.size());

        Population testWald1 = new Buche(altersS, 100.0f, 250.0f);
        Population testWald2 = new Fichte(testWald1);
        Simulation testSim = new Simulation(1000, testWald1, testWald2);

        testSim.simLoop(eA, modell, false);
    }

    public static void istWertInBereich(float erhalten, float min, float max) {
        if (erhalten >= min && erhalten <= max) {
            System.out.println("Test erfolgreich! Erhalten:" + erhalten);
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet Wert in: [" + min + ", " + max + "] / Erhalten: " + erhalten);
        }
    }

    public static void testeWerte(float erhalten, float erwartet, float delta) {
        if (erhalten - delta <= erwartet && erhalten + delta >= erwartet) {
            System.out.println("Test erfolgreich! Erhalten:" + erhalten);
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet: " + erwartet + " / Erhalten: " + erhalten);
        }
    }
}