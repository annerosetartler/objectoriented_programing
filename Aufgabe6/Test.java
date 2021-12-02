public class Test {
    public static void main(String[] args) {
        //KOMMENTAR: Erzeugung von WorkingHeads
        WorkingHead chopper1 = new Chopper(0.6f);
        WorkingHead chopper2 = new Chopper(0.7f);
        WorkingHead chopper3 = new Chopper(0.8f);
        WorkingHead chopper4 = new Chopper(0.9f);
        WorkingHead chopper5 = new Chopper(0.5f);
        WorkingHead chopper6 = new Chopper(0.93f);

        WorkingHead shredder1 = new Shredder(56);
        WorkingHead shredder2 = new Shredder(60);
        WorkingHead shredder3 = new Shredder(70);
        WorkingHead shredder4 = new Shredder(72);
        WorkingHead shredder5 = new Shredder(71);
        WorkingHead shredder6 = new Shredder(52);

        //KOMMENTAR: Erzeugung von Harvesters
        WheelHarvester wheelHarvester = new WheelHarvester(chopper1);

        StrideHarvester strideHarvester = new StrideHarvester(shredder2);

        //KOMMENTAR: Testen der Funktionalität der Harvester //ToDo: Noch Tests, die True oder False ausgeben einbauen (Methode unten)
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
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime() );
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
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime() );
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
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime() );
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
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime() );
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
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime() );
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 0.8f));

        System.out.println("\nChange Head: shredder -> shredder ");
        strideHarvester.changeHead(shredder3);
        System.out.println("Applied Head: " + shredder3.getClass());
        System.out.println("\nHead information: " + (Integer) strideHarvester.readHeadInformation()+ " " + strideHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Integer)strideHarvester.giveCoveredDistance());
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
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime() );
        System.out.println("Operation time check: " + testParameters(strideHarvester.getOperationTime(), 1.2f));

    }

    private static boolean testParameters(Float float1, Float float2){ //ToDo: float-Vergleiche
        return Math.abs(float1 - float2) < 0.00001;
    }
    private static boolean testParameters(Integer int1, Integer int2){ //ToDo: Die Methoden auch iwie zusammen geben?
        return int1 == int2;
    }

}
