import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        char[][] testArr = new char[][]{
                {'*','*','*','*','X'},
                {'X','*','*','*','*'},
                {'*','*','*','*','*'},
                {'*','*','*','*','*'},
                {'*','*','*','*','X'}
        };

        Forest f1 = new Forest(testArr);
        System.out.println(f1.printWald());


        System.out.println("Kleiner Wald:");
        Forest forest1 = new Forest(
                new char[][]{
                        {'*', '*', '*'},
                        {'*', '*', '*'},
                        {'*', '*', '*'},
                });

        Simulation s1 = new Simulation(forest1);
        BarkBeetle b1t1 = new BarkBeetle(s1, 1, 1, 1);
        AntBeetle a1t1 = new AntBeetle(s1, 2,1);
        LinkedList<BarkBeetle> BBlist1 = new LinkedList<BarkBeetle>();
        LinkedList<AntBeetle> ABlist1 = new LinkedList<AntBeetle>();
        BBlist1.add(b1t1);
        ABlist1.add(a1t1);
        s1.startSim(BBlist1, ABlist1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }

        System.out.println("Mittelgroßer Wald:");
        Forest forest2 = new Forest(
                new char[][]{
                        {'*', '*', 'X', 'X', '*', 'X'},
                        {'*', '*', '*', '*', '*', 'X'},
                        {'*', 'X', '*', '*', '*', '*'},
                        {'X', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', 'X', 'X'},
                        {'X', '*', 'X', '*', '*', '*'},
                });

        Simulation s2 = new Simulation(forest2);
        BarkBeetle b1t2 = new BarkBeetle(s2, 1, 1, 1);
        BarkBeetle b2t2 = new BarkBeetle(s2, 4, 1, 1);
        BarkBeetle b3t2 = new BarkBeetle(s2, 2, 4, 1);
        AntBeetle a1t2 = new AntBeetle(s2, 3,3);
        LinkedList<BarkBeetle> BBlist2 = new LinkedList<BarkBeetle>();
        LinkedList<AntBeetle> ABlist2 = new LinkedList<AntBeetle>();
        BBlist2.add(b1t2);
        BBlist2.add(b2t2);
        BBlist2.add(b3t2);
        ABlist2.add(a1t2);
        s2.startSim(BBlist2, ABlist2);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }

        System.out.println("Großer Wald:");
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
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', '*'},
                        {'*', 'X', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', 'X', 'X', '*', '*', 'X', 'X', '*', '*'},
                        {'*', '*', 'X', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', '*', '*', 'X', 'X', 'X', '*', '*', 'X', '*', 'X', '*', 'X', 'X', '*', '*'},
                        {'*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*'},
                        {'*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', 'X', 'X', '*', '*', '*', '*', '*', '*', 'X', '*', '*', '*', '*'},
                        {'*', '*', 'X', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'X', 'X', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},

                });

        Simulation s3 = new Simulation(forest3);
        BarkBeetle b1t3 = new BarkBeetle(s3, 3, 2, 1);
        BarkBeetle b2t3 = new BarkBeetle(s3, 4, 11, 1);
        BarkBeetle b3t3 = new BarkBeetle(s3, 5, 4, 1);
        BarkBeetle b4t3 = new BarkBeetle(s3, 8, 5, 1);
        BarkBeetle b5t3 = new BarkBeetle(s3, 10, 13, 1);
        BarkBeetle b6t3 = new BarkBeetle(s3, 15, 14, 1);
        BarkBeetle b7t3 = new BarkBeetle(s3, 19, 3, 1);
        BarkBeetle b8t3 = new BarkBeetle(s3, 21, 10, 1);
        BarkBeetle b9t3 = new BarkBeetle(s3, 24, 14, 1);
        AntBeetle a1t3 = new AntBeetle(s3, 7,12);
        AntBeetle a2t3 = new AntBeetle(s3, 13,4);
        AntBeetle a3t3 = new AntBeetle(s3, 22,7);
        LinkedList<BarkBeetle> BBlist3 = new LinkedList<BarkBeetle>();
        LinkedList<AntBeetle> ABlist3 = new LinkedList<AntBeetle>();
        BBlist3.add(b1t3);
        BBlist3.add(b2t3);
        BBlist3.add(b3t3);
        BBlist3.add(b4t3);
        BBlist3.add(b5t3);
        BBlist3.add(b6t3);
        BBlist3.add(b7t3);
        BBlist3.add(b8t3);
        BBlist3.add(b9t3);
        ABlist3.add(a1t3);
        ABlist3.add(a2t3);
        ABlist3.add(a3t3);
        s3.startSim(BBlist3, ABlist3);
    }
}
