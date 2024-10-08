import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        System.out.println("Tests zu Einfluesse:" + "\n");
        float[] sonne = new float[]{69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f};
        Einfluesse s = new Sonne(sonne);
        float[] temp = new float[]{1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f};
        Einfluesse t = new Temperatur(temp);
        float[] regen = new float[]{19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f};
        Einfluesse n = new Niederschlag(regen);
        float[] wind1 = new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f};
        Einfluesse w1 = new Wind(wind1);

        System.out.println("Faktorenberechnung:");
        istWertInBereich(s.VerhaeltnisZu(n),0.0f,1.0f);
        istWertInBereich(t.getFaktor(),0.0f,1.0f);
        float mure = n.getFaktor();
        istWertInBereich(mure,0.0f,1.0f);
        testeWerte(mure,1.0f,0.0000001f);
        istWertInBereich(w1.getFaktor(),0.0f,1.0f);

        Einfluesse w2 = new Wind(new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f});
        float[] abweichungen = new float[]{1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f, 1.5f};
        w2.Plus1Jahr(abweichungen);
        float sturm = w2.getFaktor();
        testeWerte(sturm,1.0f,0.0000001f);
        abweichungen = new float[]{1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f, 1.05f};
        w2.Plus1Jahr(abweichungen);
        sturm = w2.getFaktor();
        testeWerte(sturm,0.0f,0.0000001f);

        Einfluesse s1 = new Sonne(new float[]{100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f});
        Einfluesse n1 = new Niederschlag(new float[]{50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f});
        float verh1 = s1.VerhaeltnisZu(n1);
        testeWerte(verh1,0.0f,0.0000001f);

        Einfluesse s2 = new Sonne(new float[]{200.0f,200.0f,200.0f,200.0f,200.0f,200.0f,200.0f,200.0f,200.0f,200.0f,200.0f,200.0f});
        Niederschlag n2 = new Niederschlag(new float[]{50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f});
        float verh2 = s2.VerhaeltnisZu(n2);
        testeWerte(verh2,1.0f,0.0000001f);

        Einfluesse s3 = new Sonne(new float[]{300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f,300.0f});
        Niederschlag n3 = new Niederschlag(new float[]{50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f,50.0f});
        float verh3 = s3.VerhaeltnisZu(n3);
        testeWerte(verh3,0.0f,0.0000001f);

        System.out.println("----");
        System.out.println("EinflussVerw:");
        System.out.println("----");

        EinflussVerw e = new EinflussVerw(s,n,t,w1);
        System.out.println(e);
        System.out.println("----");
        System.out.println("Testsimulation EinflussVerw:");
        System.out.println("----");
        float[] faktoren;
        for (int i = 1; i <= 5; i++) {
            faktoren = e.Plus1Jahr();
            System.out.println("Jahr: " + i);
            System.out.println(e);
            System.out.println(Arrays.toString(faktoren));
            System.out.println("\n");
        }

        System.out.println("Tests zu Monokulturwald + Bewirtschaftungsmodell:" + "\n");

        float zielBTest = 250.0f;

        float[] einflussTest1 = new float[]{1.0f,1.0f,1.0f,0.0f};
        float[] einflussTest2 = new float[]{0.0f,0.0f,0.0f,1.0f};
        float[] einflussTest3 = new float[]{0.5f,0.5f,0.5f,0.5f};

        float[] naturbelassen = new float[]{0.0f,0.0f,0.0f,0.0f};
        float[] kahlschlag = new float[]{1.0f,0.0f,0.0f,0.0f};
        float[] plenter = new float[]{0.0f,1.0f,0.45f,0.0f};
        float[] erholung = new float[]{0.0f,0.0f,0.0f,0.01145f}; //KOMMENTAR: Bestand: 5 Hütten + 4 Wege Plus: 1 neue Hütte + 1 neuer Weg

        ArrayList<Float> aSTEST = new ArrayList<Float>();
        for (int i = 0; i < 5; i++) {
            aSTEST.add(0.2f);
        }

        ArrayList<Float> aSTESTplenter = new ArrayList<Float>();
        aSTESTplenter.add(0.1f);
        aSTESTplenter.add(0.3f);
        aSTESTplenter.add(0.1f);
        aSTESTplenter.add(0.3f);
        aSTESTplenter.add(0.2f);


        Population buche1 = new Buche(aSTEST, 100.0f, zielBTest);
        float[] bucheZustand_0 = buche1.zustandPop();
        Population buche2 = new Buche(buche1);
        Population buche3 = new Buche(buche1);
        Population buche4 = new Buche(aSTESTplenter,100.0f,zielBTest);
        Population buche5 = new Buche(buche1);

        Population fichte1 = new Fichte(aSTEST, 100.0f, zielBTest);
        float[] fichteZustand_0 = fichte1.zustandPop();
        Population fichte2 = new Buche(fichte1);
        Population fichte3 = new Buche(fichte1);
        Population fichte4 = new Buche(buche4);
        Population fichte5 = new Buche(fichte1);

        System.out.println("Tests zu Buchenwald (= Laubwald):" + "\n");

        Simulation testbuche1 = new Simulation(1, buche1,fichte1);//KOMMENTAR: fichte1 wird nicht verwendet
        testbuche1.testSimLoop(einflussTest1,naturbelassen,zielBTest);
        float[] buche1Zustand = buche1.zustandPop();
        testeWerte(bucheZustand_0[0],buche1Zustand[0]);

        Simulation testbuche2 = new Simulation(1, buche2,fichte1);//KOMMENTAR: fichte1 wird nicht verwendet
        testbuche2.testSimLoop(einflussTest2,naturbelassen,zielBTest);
        float[] buche2Zustand = buche2.zustandPop();
        testeWerte(buche2Zustand[0],bucheZustand_0[0]);

        Simulation testbuche3 = new Simulation(1, buche3,fichte1);//KOMMENTAR: fichte1 wird nicht verwendet
        testbuche3.testSimLoop(einflussTest3,kahlschlag,zielBTest);
        float[] buche3Zustand = buche3.zustandPop();
        testeWerte(bucheZustand_0[0],buche3Zustand[0]);
        testeWerte(buche3Zustand[3],bucheZustand_0[3]);

        Simulation testbuche4 = new Simulation(1, buche4,fichte1);//KOMMENTAR: fichte1 wird nicht verwendet
        testbuche4.testSimLoop(einflussTest3,plenter,zielBTest);
        float[] buche4Zustand = buche4.zustandPop();
        testeWerte(buche4Zustand[3],bucheZustand_0[3]);

        Simulation testbuche5 = new Simulation(1, buche5,fichte1);//KOMMENTAR: fichte1 wird nicht verwendet
        testbuche5.testSimLoop(einflussTest3,erholung,zielBTest);
        float[] buche5Zustand = buche5.zustandPop();
        testeWerte(buche5Zustand[3],bucheZustand_0[3]);
        testeWerte(bucheZustand_0[0] + buche5Zustand[5],buche5Zustand[0]);

        System.out.println("Tests zu Fichtenwald (= Nadelwald):" + "\n");

        Simulation testfichte1 = new Simulation(1, fichte1,buche1);//KOMMENTAR: buche1 wird nicht verwendet
        testfichte1.testSimLoop(einflussTest1,naturbelassen,zielBTest);
        float[] fichte1Zustand = fichte1.zustandPop();
        testeWerte(fichteZustand_0[0],fichte1Zustand[0]);

        Simulation testfichte2 = new Simulation(1, fichte2,buche1);//KOMMENTAR: buche1 wird nicht verwendet
        testfichte2.testSimLoop(einflussTest2,naturbelassen,zielBTest);
        float[] fichte2Zustand = fichte2.zustandPop();
        testeWerte(fichte2Zustand[0],fichteZustand_0[0]);

        Simulation testfichte3 = new Simulation(1, fichte3,buche1);//KOMMENTAR: buche1 wird nicht verwendet
        testfichte3.testSimLoop(einflussTest3,kahlschlag,zielBTest);
        float[] fichte3Zustand = fichte3.zustandPop();
        testeWerte(fichteZustand_0[0],fichte3Zustand[0]);
        testeWerte(fichte3Zustand[3],fichteZustand_0[3]);

        Simulation testfichte4 = new Simulation(1, fichte4,buche1);//KOMMENTAR: buche1 wird nicht verwendet
        testfichte4.testSimLoop(einflussTest3,plenter,zielBTest);
        float[] fichte4Zustand = fichte4.zustandPop();
        testeWerte(fichte4Zustand[3],fichteZustand_0[3]);

        Simulation testfichte5 = new Simulation(1, fichte5,buche1);//KOMMENTAR: buche1 wird nicht verwendet
        testfichte5.testSimLoop(einflussTest3,erholung,zielBTest);
        float[] fichte5Zustand = fichte5.zustandPop();
        testeWerte(fichte5Zustand[3],fichteZustand_0[3]);
        testeWerte(fichteZustand_0[0] + fichte5Zustand[5],fichte5Zustand[0]);

        System.out.println("----\nTestsimulation: Mischwald + Plenterwirtschaft:\n----\n");

        float[] sonneA = new float[]{69.0f,108.0f,202.0f,312.0f,222.0f,193.0f,286.0f,240.0f,224.0f,88.0f,58.0f,29.0f};
        Sonne sA = new Sonne(sonneA);
        float[] tempA = new float[]{1.4f,6.6f,7.3f,12.5f,14.5f,19.0f,21.6f,21.9f,16.9f,11.2f,6.1f,3.3f};
        Temperatur tA = new Temperatur(tempA);
        float[] regenA = new float[]{19.0f,52.0f,21.0f,9.0f,83.0f,94.0f,77.0f,99.0f,75.0f,130.0f,17.0f,23.0f};
        Niederschlag nA = new Niederschlag(regenA);
        float[] windA = new float[]{10.8f,18.0f,14.4f,10.4f,14.0f,14.8f,10.4f,10.8f,11.5f,13.0f,9.7f,12.6f};
        Wind wA = new Wind(windA);

        EinflussVerw eA = new EinflussVerw(sA,nA,tA,wA);

        ArrayList<Float> Uebergabelist = new ArrayList<Float>();
        ArrayList<Float> altersS = new ArrayList<Float>();
        Random rando = new Random();
        float sum = 0;
        for (int i = 0; i < 250; i++) {
            float a = rando.nextFloat();
            sum += a;
            Uebergabelist.add(a);
        }

        for (Float f : Uebergabelist) {
            altersS.add(f / sum);
        }

        //Plenterwirtschaft
        Bewirtschaftungsmodell modellP = new Plenterwirtschaft();

        float zielBestandP = 250.0f;
        Population testWald1P = new Buche(altersS, 100.0f, zielBestandP);
        Population testWald2P = new Fichte(testWald1P);
        Simulation testSimP = new Simulation(1000, testWald1P, testWald2P);
        testSimP.simLoop(eA, modellP, true, zielBestandP);


        System.out.println("----\nTestsimulation: Mischwald + Kahlschlag:\n----\n");
        //Kahlschlag
        Bewirtschaftungsmodell modellK = new Kahlschlag();

        float zielBestandK = 250.0f;
        Population testWald1K = new Buche(altersS, 100.0f, zielBestandK);
        Population testWald2K = new Fichte(testWald1K);
        Simulation testSimK = new Simulation(1000, testWald1K, testWald2K);
        testSimK.simLoop(eA, modellK, true, zielBestandK);

        System.out.println("----\nTestsimulation: Mischwald + Erholungsgebiet:\n----\n");
        //Erholungsgebiet
        Bewirtschaftungsmodell modellE = new Erholungsgebiet();

        float zielBestandE = 250.0f;
        Population testWald1E = new Buche(altersS, 100.0f, zielBestandE);
        Population testWald2E = new Fichte(testWald1E);
        Simulation testSimE = new Simulation(1000, testWald1E, testWald2E);
        testSimE.simLoop(eA, modellE, true, zielBestandE);

        System.out.println("----\nTestsimulation: Mischwald + Naturbelassen:\n----\n");
        //Naturbelassen
        Bewirtschaftungsmodell modellN = new Naturbelassen();

        float zielBestandN = 250.0f;
        Population testWald1N = new Buche(altersS, 100.0f, zielBestandN);
        Population testWald2N = new Fichte(testWald1N);
        Simulation testSimN = new Simulation(1000, testWald1N, testWald2N);
        testSimN.simLoop(eA, modellN, true, zielBestandN);
    }

    //VORB: max > min
    //NACHB: Test erfolgreich wenn erhalten in [min, max], sonst Test nicht erfolgreich
    public static void istWertInBereich(float erhalten, float min, float max) {
        if (erhalten >= min && erhalten <= max) {
            System.out.println("Test erfolgreich! Erhalten:" + erhalten);
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet Wert in: [" + min + ", " + max + "] / Erhalten: " + erhalten);
        }
    }

    //NACHB: Test erfolgreich wenn erhalten in [erwartet - delta, erwartet + delta], sonst Test nicht erfolgreich
    public static void testeWerte(float erhalten, float erwartet, float delta) {
        if (erhalten - delta <= erwartet && erhalten + delta >= erwartet) {
            System.out.println("Test erfolgreich! Erhalten:" + erhalten);
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet: " + erwartet + " / Erhalten: " + erhalten);
        }
    }

    //NACHB: Test erfolgreich wenn erhalten2 < erhalten1, sonst Test nicht erfolgreich
    public static void testeWerte(float erhalten1, float erhalten2) {
        if (erhalten2 < erhalten1) {
            System.out.println("Test erfolgreich!");
        } else {
            System.out.println("Test NICHT erfolgreich! Erwartet: " + erhalten2 + " < " + erhalten1);
        }
    }
}

/*
    AUFGABE 3:

    ERLÄUTERUNG ZU MEHR GUT ALS SCHLECHT: Wir haben mehr SCHLECHT-Kommentare, da wir in der Umsetzung der Programmstruktur
    zu sehr mit Vererbung gearbeitet und Subtyping zu wenig angewandt haben. Das führt zu den in "SCHLECHT:" beschriebenen
    Problemen. Zudem haben wir hier abstrakte Obertypen, die aber theoretisch instanzierbar wären.

    ARBEITSAUFTEILUNG AUFGABE 3:
    Kommentare in Einfluesse (samt aller Untertypen & Einflussverw): Maria
    Kommentare in Bewirtschaftungsmodelle (samt aller Untertypen) : David
    Kommentare in Population (samt aller Untertypen): David, Maria
    Kommentare in Forst: Annerose, David, Maria
    Kommentare in Simulation, Test: Annerose, David, Maria

    AUFGABE 2:
    PROGRAMM GROBSTRUKTUR:

    WALD:
    Population ist der Obertyp, von dem Fichte (=Nadelwald) und Buche(=Laubwald) Untertypen sind.
    Population ist also der Wald.
    Forst ist ein Wrapper, der entweder eine Population oder zwei Populationen besitzt.
    Wenn der Forst aus zwei Populationen besteht, dann handelt es sich um einen Mischwald.
    Ein Mischwald hat also 2 Populationen. In unserem Fall wäre das ein Buchenwald und ein Fichtenwald.

    BEWIRTSCHAFTUNGSMODELLE:
    Es gibt Berwirtschaftungsmodelle (Bewirtschaftungsmodell ist der Obertyp), die unterteilt sind in Naturbelassen
    und Bewirtschaftet. Bewirtschaftet ist dann weiter unterteilt in Kahlschlag, Plenterwirtschaft und Erholungsgebiet.
    Alle Bewirtschaftungsmodelle definieren sich über ein Array mit Wirtschaftsfaktoren. Für jede Art von
    Bewirtschaftungsmodell ist das Array entsprechend anders belegt und löst andere Ereignisse aus.

    EINFLÜSSE:
    Dann gibt es noch Einfluesse (= Obertyp), von denen Sonne, Niederschlag, Wind und Temperatur Untertypen sind.
    Wir haben hier Normwerte genommen, die über einen Zeitraum von 19 Jahren beobachtet wurden (Quelle findet sich im
    jeweiligen Untertyp). EinflussVerw verwaltet dann so ein Einflüssepaket. Basierend auf den Normwerten wird unter
    Verwendung eines Klimawandelfaktors und Gaußverteilten Zufallswerte die nächstjährlichen Werte für Sonne, Niederschlag,
    Wind und Temperatur errechnet.
    Aus der Gesamtheit dieser Werte wird ein Einflüssearray generiert, das in die jährlichen Berechnungen der Population
    in Kombination mit dem Bewirtschaftungsmodell einfließt. Die Werte des Einflüssearrays sind folgende Faktoren:
    [Hitze, Mure, Sturm, Zuwachs]. Zuwachs errechnet sich dabei aus dem Verhältnis der Sonnenstunden zu Niederschlag.


    ARBEITSAUFTEILUNG AUFGABE 2:
    Konzept Programmstruktur: Annerose, David & Maria
    Wald: Annerose
    Bewirtschaftungsmodelle, Erntefunktion: David
    Einfluesse: Maria
    Tests und Debugging: Annerose, David & Maria


    Arbeitsteilung Aufgabe 1:
    Konzeption der Faktorisierung und Methoden: Annerose, David und Maria
    Implementierung der Klasse Wald: Annerose, David und Maria
    Implementierung der Klassen Test, Simulation, Modell, Naturbelassen & Bewirtschaftet: David und Maria
    Überarbeitung der Klasse Wald: Annerose
    Kommentare und Ausgabe: Maria
    Inputgenerierung in Testklasse: David
    */