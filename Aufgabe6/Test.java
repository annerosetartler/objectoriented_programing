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


    }

    private static boolean testParameters(Float float1, Float float2) { //ToDo: float-Vergleiche
        return Math.abs(float1 - float2) < 0.00001;
    }
    private static boolean testParameters(Integer int1, Integer int2) { //ToDo: Die Methoden auch iwie zusammen geben?
        return int1 == int2;
    }

}
