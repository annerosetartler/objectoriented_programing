public class Test {


    public static void main(String[] args) {

        Object o = new Object(); //ToDo: Not too happy with that

        System.out.println("********************************************1");
        System.out.println("Kleiner Wald - 3x3 Wald-Grundfläche:"); //5 mit Rand
        Forest forest1 = new Forest(
                new char[][]{
                        {'*', '*', '*'},
                        {'*', '*', '*'},
                        {'*', '*', '*'},
                });

        int[][] barkBeetleInfo1 = new int[][] {
                {2, 2, 1}
        };

        int[][] antBeetleInfo1 = new int[][]{
                {1, 1}
        };

        synchronized (o) {
            Simulation s1 = new Simulation(forest1);
            System.out.println("********************************************2");

            s1.populate(barkBeetleInfo1, antBeetleInfo1);
            s1.startSim();
        }

        System.out.println("********************************************3");
        try {
            System.out.println("********************************************4");
            Thread.sleep(5000);
            System.out.println("********************************************5");
        } catch (InterruptedException ignored) {
            System.out.println("return statement1");
        }



        System.out.println("********************************************6");

        System.out.println("Mittelgroßer Wald - 6x6 Wald Grundfläche:"); //8 mit Rand
        Forest forest2 = new Forest(
                new char[][]{
                        {'*', '*', 'X', 'X', '*', 'X'},
                        {'*', '*', '*', 'X', '*', 'X'},
                        {'*', '*', '*', '*', '*', '*'},
                        {'X', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', 'X', 'X'},
                        {'X', '*', 'X', '*', '*', '*'},
                });


        int[][] barkBeetleInfo2 = new int[][] {
                {3, 2, 1},
                {6, 6, 1},
                {3, 5, 1}
        };

        int[][] antBeetleInfo2 = new int[][] {
                {4, 4}
        };

        synchronized (o) {
            Simulation s2 = new Simulation(forest2);
            s2.populate(barkBeetleInfo2, antBeetleInfo2);
            s2.startSim();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("return statement2");

        }

        System.out.println("********************************************");



        System.out.println("Großer Wald - 27x27 Wald Grundfläche:"); //29 mit Rand
        Forest forest3 = new Forest(
                new char[][]{
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', 'X', 'X', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', 'X', 'X', '*', '*'},
                        {'*', '*', 'X', 'X', '*', '*', '*', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*'},
                        {'*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*','*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*'},
                        {'*', 'X', '*', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', 'X', 'X', '*', '*', 'X', 'X', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', 'X', '*', 'X', '*', 'X', 'X', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', 'X', '*', '*', '*', '*'},
                        {'*', '*', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', '*', '*', 'X', 'X', '*', '*', '*', '*', '*'},
                        {'*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', 'X', '*', '*', '*', 'X', '*', '*'},
                        {'*', '*', 'X', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', 'X', '*', 'X', '*', 'X', '*', '*', '*'},
                        {'*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', 'X', '*', '*', '*', '*', 'X', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', 'X', '*', '*', '*', '*'},
                        {'*', '*', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                });

        int[][] barkBeetleInfo = new int[][] {
                {3, 2, 1},
                //{9, 10, 1},
                //{1, 17, 1},
                //{23, 7, 1},
                //{9, 6, 1},
                //{22, 4, 1},
                //{22, 23, 1},
                //{25, 25, 1}
        };

        int[][] antBeetleInfo = new int[][] { //ToDo: vlt. hier einmal dieselbe Sim mit vielem einmal mit wenigen?
                {7, 12} /*,
                {13, 4},
                {22, 7},
                {27, 25},
                {1, 27}
                */
        };

        synchronized (o) {
            Simulation s3 = new Simulation(forest3);

            s3.populate(barkBeetleInfo, antBeetleInfo);
            s3.startSim();
        }
    }

}
/*
************************************************************************************************************************
Arbeitsaufteilung Aufgabe 8:

Konzeption Programmstruktur: Annerose, Maria
Zusicherungen und Implementierung von Field, Forest, Simulation, BarkBeetle: Maria
Zusicherungen und Implementierung von AntBeetle: Annerose
Schreiben der Tests: David
Synchronisation & Debugging von Field, Forest, Simulation, BarkBeetle, AntBeelte: Annerose, Maria
Debugging Test: Annerose

**************************************************************************************************************************
 */