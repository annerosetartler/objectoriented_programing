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
        System.out.println("WheelHarvester: Harvester Number " + wheelHarvester.getHarvesterNumber());
        System.out.println("Applied Head: " + chopper1.getClass());
        System.out.println("\nHead information: " + (Float) wheelHarvester.readHeadInformation() + " " + wheelHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Float)  wheelHarvester.giveCoveredDistance()); //ToDo: Wenn ich nicht dynamisch binde hier überall die Casts weg
        System.out.println("Operation time at start: " + wheelHarvester.getOperationTime());
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + wheelHarvester.getOperationTime());
        wheelHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime() );

        System.out.println("\nChange Head: chopper -> chopper ");
        wheelHarvester.changeHead(chopper3);
        System.out.println("Applied Head: " + chopper3.getClass());
        System.out.println("\nHead information: " + (Float) wheelHarvester.readHeadInformation() + " " + wheelHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Operation time at start: " + wheelHarvester.getOperationTime());
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + wheelHarvester.getOperationTime());
        wheelHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime() );

        System.out.println("\nChange Head: chopper -> shredder ");
        wheelHarvester.changeHead(shredder1);
        System.out.println("Applied Head: " + shredder1.getClass());
        System.out.println("\nHead information: " + (Integer) wheelHarvester.readHeadInformation() + " " + wheelHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Operation time at start: " + wheelHarvester.getOperationTime());
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        wheelHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + wheelHarvester.getOperationTime());
        wheelHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + wheelHarvester.getOperationTime() );


        System.out.println("\n\nStrideHarvester: Harvester Number " + strideHarvester.getHarvesterNumber());
        System.out.println("Applied Head: " + shredder2.getClass());
        System.out.println("\nHead information: " + (Integer) strideHarvester.readHeadInformation() + " " + strideHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Operation time at start: " + strideHarvester.getOperationTime());
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + strideHarvester.getOperationTime());
        strideHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime() );

        System.out.println("\nChange Head: chopper -> shredder ");
        strideHarvester.changeHead(chopper2);
        System.out.println("Applied Head: " + chopper2.getClass());
        System.out.println("\nHead information: " + (Float) strideHarvester.readHeadInformation() + " " + strideHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Integer) strideHarvester.giveCoveredDistance()); //ToDo: wenn ich nicht dynamisch Binde hier überall die Casts weg
        System.out.println("Operation time at start: " + strideHarvester.getOperationTime());
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + strideHarvester.getOperationTime());
        strideHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime() );

        System.out.println("\nChange Head: shredder -> shredder ");
        strideHarvester.changeHead(shredder3);
        System.out.println("Applied Head: " + shredder3.getClass());
        System.out.println("\nHead information: " + (Integer) strideHarvester.readHeadInformation()+ " " + strideHarvester.getHeadMeaning());
        System.out.println("Covered distance: " + (Integer)strideHarvester.giveCoveredDistance());
        System.out.println("Operation time at start: " + strideHarvester.getOperationTime());
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        strideHarvester.raiseCoveredDistance();
        System.out.println("Covered distance after 3x .raiseCoveredDistance(): " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Operation time after 3 uses (3x .raiseCoveredDistance()s) " + strideHarvester.getOperationTime());
        strideHarvester.increaseOpTime();
        System.out.println("Operation time after one additional increase: " + strideHarvester.getOperationTime() );

    }

    private static boolean testParameters(Float float1, Float float2){ //ToDo: float-Vergleiche
       return Math.abs(float1 - float2) < 0.00001;
    }
    private static boolean testParameters(Integer int1, Integer int2){ //ToDo: Die Methoden auch iwie zusammen geben?
        return int1 == int2;
    }

}
