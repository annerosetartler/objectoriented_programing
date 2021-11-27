public class Test {
    public static void main(String[] args) {
        //KOMMENTAR: Erzeugung von Trees
        Fagus f1 = new Fagus(2, 0.9f);
        Fagus f2 = new Fagus(4, 0.8f);
        Fagus f3 = new Fagus(6, 0.7f);
        Fagus f4 = new Fagus(8, 0.6f);
        Fagus f5 = new Fagus(10, 0.5f);
        Fagus f6 = new Fagus(12, 0.4f);
        Fagus f7 = new Fagus(14, 0.3f);
        Fagus f8 = new Fagus(16, 0.2f);
        Fagus f9 = new Fagus(18, 0.1f);
        Fagus f10 = new Fagus(20, 0.5f);

        Quercus q1 = new Quercus(2, 1);
        Quercus q2 = new Quercus(3, 2);
        Quercus q3 = new Quercus(4, 3);
        Quercus q4 = new Quercus(5, 4);
        Quercus q5 = new Quercus(6, 5);
        Quercus q6 = new Quercus(14, 10);
        Quercus q7 = new Quercus(15, 12);
        Quercus q8 = new Quercus(16, 14);
        Quercus q9 = new Quercus(17, 16);
        Quercus q10 = new Quercus(20, 14);

        QuercusRobur qr1 = new QuercusRobur(2, 1, "medium");
        QuercusRobur qr2 = new QuercusRobur(3, 2,"low");
        QuercusRobur qr3 = new QuercusRobur(4, 3,"high");
        QuercusRobur qr4 = new QuercusRobur(5, 4,"low");
        QuercusRobur qr5 = new QuercusRobur(6, 5, "medium");
        QuercusRobur qr6 = new QuercusRobur(14, 10, "high");
        QuercusRobur qr7 = new QuercusRobur(15, 12, "low");
        QuercusRobur qr8 = new QuercusRobur(16, 14,"high");
        QuercusRobur qr9 = new QuercusRobur(17, 16,"medium");
        QuercusRobur qr10 = new QuercusRobur(18, 14,"medium");

        Tree t1 = f1;
        Tree t2 = q2;
        Tree t3 = qr3;
        Tree t4 = f4;
        Tree t5 = q5;
        Tree t6 = qr6;
        Tree t7 = f7;
        Tree t8 = q8;
        Tree t9 = qr9;
        Tree t10 = f10;

        //KOMMENTAR: Erzeugung von SingleGroups
        SingleGroup<Fagus> sFagus1 = new SingleGroup<Fagus>();
        sFagus1.add(f1);
        sFagus1.add(f3);
        sFagus1.add(f5);

        SingleGroup<Quercus> sQuercus1 = new SingleGroup<Quercus>();
        sQuercus1.add(q1);
        sQuercus1.add(q3);
        sQuercus1.add(q5);

        SingleGroup<QuercusRobur> sQuercusR1 = new SingleGroup<QuercusRobur>();
        sQuercus1.add(qr1);
        sQuercus1.add(qr3);
        sQuercus1.add(qr5);

        SingleGroup<Tree> sTree1 = new SingleGroup<Tree>();
        sTree1.add(t1);
        sTree1.add(t3);
        sTree1.add(f5);

        SingleGroup<Integer> sInt1 = new SingleGroup<Integer>();
        sInt1.add(1);
        sInt1.add(3);
        sInt1.add(5);

        //KOMMENTAR: Erzeugung von Multigroups
        Relation<Fagus,Fagus> ffRel1 = Fagus.relation();
        MultiGroup<Fagus,Fagus> fm1 = new MultiGroup<Fagus,Fagus>(sFagus1,ffRel1);
        fm1.add(f2); fm1.add(f5); fm1.add(f6);

        Relation<Quercus,Tree> qrRel1 = Quercus.relation();
        MultiGroup<QuercusRobur,Fagus> qrf1 = new MultiGroup<QuercusRobur,Fagus>(fm1, qrRel1);
        qrf1.add(qr2); qrf1.add(qr5); qrf1.add(qr6);


        //Relation<Quercus,Fagus> qfRel = Relation<Quercus, Fagus> Quercus.relation();
        Relation<? extends Quercus, ? extends Tree> qfRel = Quercus.relation();
        Relation<Fagus,Fagus> fRel2 = Fagus.relation();







        System.out.println("Hello!");
    }
}
