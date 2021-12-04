public class Test {
    public static void main(String[] args) {
        //KOMMENTAR: Erzeugung von WorkingHeads
        WorkingHead chopper1 = new Chopper(0.6f);
        WorkingHead chopper2 = new Chopper(0.7f);
        WorkingHead chopper3 = new Chopper(0.8f);

        WorkingHead shredder1 = new Shredder(56);
        WorkingHead shredder2 = new Shredder(60);
        WorkingHead shredder3 = new Shredder(70);

        //KOMMENTAR: Erzeugung von Harvesters
        Harvester wheelHarvester = new WheelHarvester(chopper1);

        Harvester strideHarvester = new StrideHarvester(shredder2);

        //KOMMENTAR: Testen der Funktionalität der Harvester
        System.out.println("Harvester Checks:");
        System.out.println("\n1. Wheel Harvester Checks");
        System.out.println("\ntoString:" + wheelHarvester.toString());
        System.out.println("\nCovered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 0.0f));
        System.out.println("Head information: " + (Float) wheelHarvester.readHeadInformation() + " " + wheelHarvester.getHeadMeaning());
        System.out.println("Operation time at start: " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 0.0f));
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 0.9f));
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + wheelHarvester.getOperationTime());
        System.out.println("Covered distance check: " + testParameters(wheelHarvester.getOperationTime(), 0.3f));
        wheelHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 0.4f));

        System.out.println("\nChange Head: chopper -> chopper ");
        wheelHarvester.changeHead(chopper3);
        System.out.println("Applied Head: " + chopper3.getClass());
        System.out.println("\nHead information: " + (Float) wheelHarvester.readHeadInformation() + " " + wheelHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 0.9f));
        System.out.println("Operation time at start: " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 0.4f));
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 1.8f));
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 0.7f));
        wheelHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 0.8f));

        System.out.println("\nChange Head: chopper -> shredder ");
        wheelHarvester.changeHead(shredder1);
        System.out.println("Applied Head: " + shredder1.getClass());
        System.out.println("\nHead information: " + (Integer) wheelHarvester.readHeadInformation() + " " + wheelHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 1.8f));
        System.out.println("Operation time at start: " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 0.8f));
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 2.7f));
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 1.1f));
        wheelHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(wheelHarvester.getOperationTime(), 1.2f));


        System.out.println("\n2. Stride Harvester Checks:");
        System.out.println("toString:" + strideHarvester.toString());
        System.out.println("\nCovered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 0));
        System.out.println("Operation time at start: " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.0f));
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 3));
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.3f));
        strideHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.4f));

        System.out.println("\nChange Head: chopper -> shredder ");
        strideHarvester.changeHead(chopper2);
        System.out.println("Applied Head: " + chopper2.getClass());
        System.out.println("\nHead information: " + (Float) strideHarvester.readHeadInformation() + " " + strideHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Integer) strideHarvester.giveCoveredDistance()); //ToDo: wenn ich nicht dynamisch Binde hier überall die Casts weg
        System.out.println("Covered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 3));
        System.out.println("Operation time at start: " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.4f));
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 6));
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.7f));
        strideHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.8f));

        System.out.println("\nChange Head: shredder -> shredder ");
        strideHarvester.changeHead(shredder3);
        System.out.println("Applied Head: " + shredder3.getClass());
        System.out.println("\nHead information: " + (Integer) strideHarvester.readHeadInformation() + " " + strideHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 6));
        System.out.println("Operation time at start: " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.8f));
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 9));
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 1.1f));
        strideHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime());
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 1.2f));


        //TODO Forsbetriebe anlegen
        //TODO to string abändern sodass ich nicht 5000 mal den selben code schreibe
        System.out.println("\nTests zu Forstbetrieb");
        Forstbetrieb ForstTest1 = new Forstbetrieb("Test");
        WorkingHead Forstchopper1 = new Chopper(0.6f);
        WorkingHead Forstchopper2 = new Chopper(0.7f);
        WorkingHead Forstchopper3 = new Chopper(0.8f);

        WorkingHead Forstshredder1 = new Shredder(56);
        WorkingHead Forstshredder2 = new Shredder(60);

        WheelHarvester WheelTest1 = new WheelHarvester(Forstchopper1);
        WheelHarvester WheelTest2 = new WheelHarvester(Forstshredder1);
        StrideHarvester StrideTest1 = new StrideHarvester(Forstchopper2);
        StrideHarvester StrideTest2 = new StrideHarvester(Forstshredder2);

        ForstTest1.add(WheelTest1);
        ForstTest1.add(WheelTest2);
        ForstTest1.add(StrideTest1);
        ForstTest1.add(StrideTest2);
        WheelTest1.raiseCoveredDistance();
        WheelTest2.raiseCoveredDistance();
        WheelTest2.raiseCoveredDistance();
        WheelTest2.raiseCoveredDistance();
        StrideTest1.raiseCoveredDistance();
        StrideTest1.raiseCoveredDistance();
        StrideTest1.raiseCoveredDistance();
        StrideTest2.raiseCoveredDistance();


        System.out.println("Tests zur Berechnung statistischer Werte:\n");
        System.out.println("\n" + ForstTest1.avgOperationTime(1));
        System.out.println("\n" + ForstTest1.avgOperationTime(0));

        System.out.println("\n" + ForstTest1.avgWayLength(1));
        System.out.println("\n" + ForstTest1.avgWayLength(0));

        System.out.println("\n" + ForstTest1.minMaxPiece());

        System.out.println("\n" + ForstTest1.avgThickness());

        System.out.println(ForstTest1);
        ForstTest1.remove(3);
        System.out.println(ForstTest1);
        ForstTest1.change(4, Forstchopper3);
        System.out.println(ForstTest1);


        //KOMMENTAR: Erstellen von Forstbetrieben für Regionen
        Forstbetrieb ForstTest2 = new Forstbetrieb("Test");
        WorkingHead Forst2chopper1 = new Chopper(0.6f);
        WorkingHead Forst2chopper2 = new Chopper(0.7f);

        WorkingHead Forst2shredder1 = new Shredder(56);
        WorkingHead Forst2shredder2 = new Shredder(60);

        WheelHarvester Forst2WheelTest1 = new WheelHarvester(Forst2chopper1);
        WheelHarvester Forst2WheelTest2 = new WheelHarvester(Forst2shredder1);
        StrideHarvester Forst2StrideTest1 = new StrideHarvester(Forst2chopper2);
        StrideHarvester Forst2StrideTest2 = new StrideHarvester(Forst2shredder2);

        ForstTest2.add(Forst2WheelTest1);
        ForstTest2.add(Forst2WheelTest2);
        ForstTest2.add(Forst2StrideTest1);
        ForstTest2.add(Forst2StrideTest2);
        Forst2WheelTest1.raiseCoveredDistance();
        Forst2WheelTest2.raiseCoveredDistance();
        Forst2WheelTest2.raiseCoveredDistance();
        Forst2WheelTest2.raiseCoveredDistance();
        Forst2StrideTest1.raiseCoveredDistance();
        Forst2StrideTest1.raiseCoveredDistance();
        Forst2StrideTest1.raiseCoveredDistance();
        Forst2StrideTest2.raiseCoveredDistance();

        //TODO: ein statistischer Test, der eine Exception wirft > also auf einem leeren Forstbetrieb mit try catch
        //      schaut es euch an, ob es so passt für euch, hab mal die exception angelegt
        Forstbetrieb exceptionStats = new Forstbetrieb("excepetion");
        try{
            exceptionStats.avgOperationTime(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO: Forstebtriebe befüllen
        Forstbetrieb fb1 = new Forstbetrieb("Angerer");
        Forstbetrieb fb2 = new Forstbetrieb("Maurer");
        Forstbetrieb fb3 = new Forstbetrieb("Holzer");
        Forstbetrieb fb4 = new Forstbetrieb("ForstGmbH");
        Forstbetrieb fb5 = new Forstbetrieb("Quercusse");
        Forstbetrieb fb6 = new Forstbetrieb("Fagusse");

        //KOMMENTAR: Tests zu Regionen
        System.out.println("Tests zu Regionen:\n");
        Region r1 = new Region("Waldviertel");
        Region r2 = new Region("Mostviertel");
        Region r3 = new Region("Auvergne");

        r1.add(fb1);r1.add(fb2);r1.add(fb3);
        r2.add(fb4);
        r3.add(fb5);r3.add(fb6);

        System.out.println(r1.toString());
        System.out.println(r2.toString());
        System.out.println(r3.toString());

        System.out.println();

        System.out.println("Tests zu add() in Regionen:\n");
        int r1Size = r1.getSize();
        r1.add(fb1);
        int r1SizeAfterAdd = r1.getSize();
        System.out.println(r1.toString());
        System.out.println("Bereits vorhandenes Element noch einmal hinzugefügt: Size bleibt gleich: " + testParameters(r1Size,r1SizeAfterAdd));

        System.out.println();

        int r2Size = r2.getSize();
        r2.add(fb3);
        int r2SizeAfterAdd = r2.getSize();
        System.out.println(r2.toString());
        System.out.println("Neues Element hinzugefügt: Size erhöht: " + testParameters(r2Size,r2SizeAfterAdd,0));

        System.out.println();

        int r3Size = r3.getSize();
        r3.add(null);
        int r3SizeAfterAdd = r3.getSize();
        System.out.println(r3.toString());
        System.out.println("Null hinzufügen: Size bleibt gleich: " + testParameters(r3Size,r3SizeAfterAdd));

        System.out.println();
        System.out.println("Tests zu remove() in Regionen:\n");

        System.out.println("Element am Anfang der Liste entfernen:");
        System.out.println("Region vor remove(): " + r1.toString());
        r1.remove(fb1.getName());
        System.out.println("Region nach remove(): " + r1.toString());
        int r1AfterRemove = r1.getSize();
        System.out.println("Element entfernt: Size verringert: " + testParameters(r1SizeAfterAdd,r1AfterRemove,1));

        System.out.println();
        System.out.println("Element am Ende der Liste entfernen:");
        System.out.println("Region vor remove(): " + r2.toString());
        r2.remove(fb3.getName());
        System.out.println("Region nach remove(): " + r2.toString());
        int r2AfterRemove = r2.getSize();
        System.out.println("Element entfernt: Size verringert: " + testParameters(r2SizeAfterAdd,r2AfterRemove,1));

        System.out.println();
        System.out.println("Element innerhalb der Liste entfernen:");
        System.out.println("Vorher hinzufügen eines Elements zur Region 'Waldviertel':");
        r1.add(fb1);
        r1SizeAfterAdd = r1.getSize();
        System.out.println(r1.toString());
        System.out.println("Region vor remove(): " + r1.toString());
        r1.remove(fb3.getName());
        r1AfterRemove = r1.getSize();
        System.out.println("Region nach remove(): " + r1.toString());
        System.out.println("Element entfernt: Size verringert: " + testParameters(r1SizeAfterAdd,r1AfterRemove,1));

        System.out.println();
        System.out.println("Ein Element aus der Liste entfernen, das nicht in der Liste vorkommt:");
        System.out.println("Region vor remove(): " + r2.toString());
        r2.remove("RoburHack");
        System.out.println("Region nach remove(): " + r2.toString());
        int r2ARem = r2.getSize();
        System.out.println("Nicht existierendes Element entfernt: Size bleibt gleich: " + testParameters(r2AfterRemove,r2ARem));


    }

    private static boolean testParameters(Float float1, Float float2) { //ToDo: float-Vergleiche
        return Math.abs(float1 - float2) < 0.00001;
    }
    private static boolean testParameters(Integer int1, Integer int2) { //ToDo: Die Methoden auch iwie zusammen geben?
        return int1 == int2;
    }

    //NACHB: wenn var == 0, dann wird überprüft, ob i1 < i2 ist
    //       sonst wird überprüft, ob i1 > i2 ist
    //       Wenn Überprüfung true ist wird der String "Test erfolgreich!" zurückgegeben
    //       sonst ist Rückgabestring: "Test fehlgeschlagen!"
    private static String testParameters(int i1, int i2, int var){
        if(var == 0){
            return i1 < i2? "Test erfolgreich!": "Test fehlgeschlagen!";
        }else{
            return i1 > i2? "Test erfolgreich!": "Test fehlgeschlagen!";
        }
    }

}

/*
************************************************************************************************************************
Arbeitsaufteilung Aufgabe 6:

Konzeption Programmstruktur: Annerose, David, Maria
Zusicherungen und Implementierung von Harvester und WorkingHead (samt aller Untertypen): Annerose
Tests zu Harvester und WorkingHead: Annerose
Zusicherungen und Implementierung von Forstbetrieb: David
Tests zu Forstbetrieb: David
Zusicherungen und Implementierung von Region und List: Maria
Tests zu Region: Maria
//TODO ist es ok, wenn wir es so aufschreiben?
statistische Methoden in Forstbetrieb: David & (Annerose & Maria)

**************************************************************************************************************************
 */