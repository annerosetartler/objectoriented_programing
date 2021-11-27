import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        //KOMMENTAR: Erzeugung von Trees
        //ToDo: Sollen wir noch mehr Tests machen? Wie findest du die Ausgabe?
        //      Ich weiß noch immer nicht, warum es problemlos funktioniert Untertypen in Obertypgruppen einzufügen
        //      zum Beispiel kann ich in SingleGroup<Quercus> problemlos auch QuercusRobur-Objekte einfügen...dabei
        //      haben wir das bei den Typparametern nicht wirklich bewusst mitbedacht
        Fagus f1 = new Fagus(2, 0.9f);
        Fagus f2 = new Fagus(4, 0.8f);
        Fagus f3 = new Fagus(6, 0.7f);
        Fagus f4 = new Fagus(8, 0.6f);
        Fagus f5 = new Fagus(10, 0.5f);
        Fagus f6 = new Fagus(12, 0.4f);
        Fagus f7 = new Fagus(14, 0.3f);
        Fagus f8 = new Fagus(5, 0.2f);
        Fagus f9 = new Fagus(6, 0.1f);
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
        System.out.println("SingleGroups: " + "\n");
        SingleGroup<Fagus> sFagus1 = new SingleGroup<Fagus>();
        sFagus1.add(f1);
        sFagus1.add(f3);
        sFagus1.add(f5);
        System.out.println("SingleGroup<Fagus>: " + sFagus1);

        SingleGroup<Quercus> sQuercus1 = new SingleGroup<Quercus>();
        sQuercus1.add(q1);
        sQuercus1.add(q3);
        sQuercus1.add(q5);
        System.out.println("SingleGroup<Quercus>: " + sQuercus1);

        SingleGroup<QuercusRobur> sQuercusR1 = new SingleGroup<QuercusRobur>();
        sQuercusR1.add(qr1);
        sQuercusR1.add(qr3);
        sQuercusR1.add(qr5);
        System.out.println("SingleGroup<QuercusRobur>: " + sQuercusR1);

        SingleGroup<Tree> sTree1 = new SingleGroup<Tree>();
        sTree1.add(t1);
        sTree1.add(t3);
        sTree1.add(t5);
        sTree1.add(t9);
        System.out.println("SingleGroup<Tree>: " + sTree1);

        SingleGroup<Integer> sInt1 = new SingleGroup<Integer>();
        sInt1.add(1);
        sInt1.add(3);
        sInt1.add(5);
        System.out.println("SingleGroup<Integer>: " + sInt1);
        System.out.println("\n");

        //KOMMENTAR: Erzeugung von Multigroups
        System.out.println("MultiGroups: " + "\n");
        //fm1 = <Fagus,Fagus>
        Relation<Fagus,Fagus> ffRel1 = Fagus.relation();
        MultiGroup<Fagus,Fagus> fm1 = new MultiGroup<Fagus,Fagus>(sFagus1,ffRel1);
        fm1.add(f9); fm1.add(f8); fm1.add(f1);
        System.out.println("MultiGroup<Fagus,Fagus>: " + fm1);

        //qrf1 = <<QuercusRobur,Fagus>,<Fagus,Fagus>>
        Relation<Quercus,Tree> qrfRel1 = Quercus.relation();
        MultiGroup<QuercusRobur,Fagus> qrf1 = new MultiGroup<QuercusRobur,Fagus>(fm1, qrfRel1);
        qrf1.add(qr2); qrf1.add(qr5); qrf1.add(qr6);
        System.out.println("MultiGroup<QuercusRobur,Fagus>: " + qrf1);

        //qf1 = <<Quercus,Fagus>,<Fagus,Fagus>>
        Relation<Quercus,Tree> qfRel1 = Quercus.relation();
        MultiGroup<Quercus,Fagus> qf1 = new MultiGroup<Quercus,Fagus>(fm1, qfRel1);
        qf1.add(q2); qf1.add(q5); qf1.add(q6);
        System.out.println("MultiGroup<Quercus,Fagus>: " + qf1);

        //qf1 = <<QuercusRobur,QuercusRobur>,<QuercusRobur,Fagus>,<Fagus,Fagus>>
        Relation<Quercus,Tree> qrqrRel1 = Quercus.relation();
        MultiGroup<QuercusRobur,QuercusRobur> qrqr1 = new MultiGroup<QuercusRobur,QuercusRobur>(qrf1, qrqrRel1);
        qrqr1.add(qr2); qrqr1.add(qr8); qrqr1.add(qr10);
        System.out.println("MultiGroup<QuercusRobur,QuercusRobur>: " + qrqr1);

        //qrq1 = <<QuercusRobur,Quercus>,<QuercusRobur,Fagus>,<Fagus,Fagus>>
        Relation<Quercus,Tree> qrqRel1 = Quercus.relation();
        MultiGroup<QuercusRobur,Quercus> qrq1 = new MultiGroup<QuercusRobur,Quercus>(qrf1, qrqRel1);
        qrq1.add(qr3); qrq1.add(qr8); qrq1.add(qr9);
        System.out.println("MultiGroup<QuercusRobur,Quercus>: " + qrq1);

        //qqr1 = <<Quercus,QuercusRobur>,<QuercusRobur,Fagus>,<Fagus,Fagus>>
        Relation<Quercus,Tree> qqrRel1 = Quercus.relation();
        MultiGroup<Quercus,QuercusRobur> qqr1 = new MultiGroup<Quercus,QuercusRobur>(qrf1, qqrRel1);
        qqr1.add(q3); qqr1.add(q8); qqr1.add(q9);
        System.out.println("MultiGroup<Quercus,QuercusRobur>: " + qqr1);

        //qq1 = <<Quercus,Quercus>,<QuercusRobur,Fagus>,<Fagus,Fagus>>
        Relation<Quercus,Tree> qqRel1 = Quercus.relation();
        MultiGroup<Quercus,QuercusRobur> qq1 = new MultiGroup<Quercus,QuercusRobur>(qrf1, qqRel1);
        qq1.add(q3); qq1.add(q8); qq1.add(q9);
        System.out.println("MultiGroup<Quercus,QuercusRobur>: " + qq1);

        //qrt1 = <QuercusRobur,Tree>
        Relation<Quercus,Tree> qrtRel1 = Quercus.relation();
        MultiGroup<QuercusRobur,Tree> qrt1 = new MultiGroup<QuercusRobur,Tree>(sTree1,qrtRel1);
        qrt1.add(qr2); qrt1.add(qr5); qrt1.add(qr6); qrt1.add(qr10);
        System.out.println("MultiGroup<QuercusRobur,Tree>: " + qrt1);

        //qt1 = <Quercus,Tree>
        Relation<Quercus,Tree> qtRel1 = Quercus.relation();
        MultiGroup<Quercus,Tree> qt1 = new MultiGroup<Quercus,Tree>(sTree1,qtRel1);
        qt1.add(q2); qt1.add(q5); qt1.add(q6); qt1.add(q10);
        System.out.println("MultiGroup<Quercus,Tree>: " + qt1);

        //ii1 = <Integer,Integer>
        Relation<Integer,Integer> iRel1 = new Numeric(3);
        MultiGroup<Integer,Integer> ii1 = new MultiGroup<Integer,Integer>(sInt1,iRel1);
        ii1.add(10); ii1.add(1); ii1.add(4); ii1.add(3);
        System.out.println("MultiGroup<Integer,Integer>: " + ii1);

        //ii2 = <Integer,Integer>
        Relation<Integer,Integer> iRel2 = new Numeric(5);
        MultiGroup<Integer,Integer> ii2 = new MultiGroup<Integer,Integer>(ii1,iRel2);
        ii2.add(10); ii2.add(5); ii2.add(2); ii2.add(15);
        System.out.println("MultiGroup<Integer,Integer>: " + ii2);
        System.out.println("\n");

        System.out.println("Tests: Iterator, add() & remove()" + "\n");

        //KOMMENTAR: Test für die "einfache" Multigroup: fm1
        System.out.println("MultiGroup<Fagus,Fagus>: " + "\n" + fm1);
        System.out.println("Iterator: ");
        for (Fagus f: fm1) {
            System.out.print(f.toString() + " ");
        }
        System.out.println("\n" + "REMOVE erstes vom Iterator zurückgegebene Element: ");
        Iterator<Fagus> fIter = fm1.iterator();
        Fagus fRemove = null;
        if(fIter.hasNext()){
            fRemove = fIter.next();
        }
        fIter.remove();
        System.out.println("entfernt: " + fRemove);
        System.out.println("MultiGroup<Fagus,Fagus>: " + fm1);
        System.out.println("ADD entferntes Element wieder einfügen: ");
        fm1.add(fRemove);
        System.out.println("MultiGroup<Fagus,Fagus>: " + fm1);
        System.out.println("Iterator nach REMOVE und ADD fortsetzen: ");
        while(fIter.hasNext()){
            Fagus f = fIter.next();
            System.out.print(f.toString() + " ");
        }

        System.out.println();
        System.out.println();

        //KOMMENTAR: Test für die verschachtelte Multigroup: qqr1
        System.out.println("MultiGroup<Quercus,QuercusRobur>: " + "\n" + qqr1);
        System.out.println("Iterator: ");
        for (Quercus q: qqr1) {
            System.out.print(q.toString() + " ");
        }
        System.out.println("\n" + "REMOVE erstes vom Iterator zurückgegebene Element: ");
        Iterator<Quercus> qIter = qqr1.iterator();
        Quercus qRemove = null;
        if(qIter.hasNext()){
            qRemove = qIter.next();
        }
        qIter.remove();
        System.out.println("entfernt: " + qRemove);
        System.out.println("MultiGroup<Quercus,QuercusRobur>: " + qqr1);
        System.out.println("ADD entferntes Element wieder einfügen: ");
        qqr1.add(qRemove);
        System.out.println("MultiGroup<Quercus,QuercusRobur>: " + qqr1);
        System.out.println("Iterator nach REMOVE und ADD fortsetzen: ");
        while(qIter.hasNext()){
            Quercus q = qIter.next();
            System.out.print(q.toString() + " ");
        }
        System.out.println("\n" + "REMOVE letztes von Iterator zurückgegebene Element: ");
        qIter.remove();
        System.out.println("MultiGroup<Quercus,QuercusRobur>: " + qqr1);

        //KOMMENTAR: Test für die verschachtelte Multigroup: ii2
        System.out.println("MultiGroup<Integer,Integer>: " + "\n" + ii2);
        System.out.println("Iterator: ");
        for (Integer i: ii2) {
            System.out.print(i.toString() + " ");
        }
        System.out.println("\n" + "REMOVE erstes vom Iterator zurückgegebene Element: ");
        Iterator<Integer> iIter = ii2.iterator();
        Integer iRemove = null;
        if(iIter.hasNext()){
            iRemove = iIter.next();
        }
        iIter.remove();
        System.out.println("entfernt: " + iRemove);
        System.out.println("MultiGroup<Integer,Integer>: " + ii2);
        System.out.println("ADD entferntes Element wieder einfügen: ");
        ii2.add(iRemove);
        System.out.println("MultiGroup<Integer,Integer>: " + ii2);
        System.out.println("Iterator nach REMOVE und ADD fortsetzen: ");
        while(iIter.hasNext()){
            Integer i = iIter.next();
            System.out.print(i.toString() + " ");
        }
        System.out.println("\n" + "REMOVE letztes von Iterator zurückgegebene Element: ");
        iIter.remove();
        System.out.println("MultiGroup<Integer,Integer>: " + ii2);
        System.out.println("\n");

        //KOMMENTAR: Ausgabe aller invoked() aller Relation<...>-Objekte
        System.out.println("Test mit QuercusRobur:" + "\n");
        System.out.println("U: MultiGroup<Quercus,Quercus>: " + "\n" + qq1);
        System.out.println("V: SingleGroup<Tree>: " + "\n" + sTree1);
        System.out.println("W: MultiGroup<QuercusRobur,QuercusRobur>: " + "\n" + qrqr1);
        System.out.println("Iterator: ");
        for (QuercusRobur qrW: qrqr1) {
            System.out.print("resistance: " + qrW.resistance() + " --- ");
            System.out.print("Object: " + qrW.toString() + " ");
            System.out.println();
            qq1.add(qrW);
            sTree1.add(qrW);
        }
        System.out.println();
        System.out.println("U: MultiGroup<Quercus,Quercus>: " + "\n" + qq1);
        System.out.println("V: SingleGroup<Tree>: " + "\n" + sTree1);


        //KOMMENTAR: Ausgabe aller invoked() aller Relation<...>-Objekte
        System.out.println();
        System.out.println();
        System.out.println("Ausgabe aller invoked() aller Relation<...>-Objekte:" + "\n");

        System.out.println("Relation von MultiGroup<Fagus,Fagus>: " + ffRel1.invoked());
        System.out.println("Relation von MultiGroup<QuercusRobur,Fagus>: " + qrfRel1.invoked());
        System.out.println("Relation von MultiGroup<Quercus,Fagus>: " + qfRel1.invoked());
        System.out.println("Relation von MultiGroup<QuercusRobur,QuercusRobur>: " + qrqrRel1.invoked());
        System.out.println("Relation von MultiGroup<QuercusRobur,Quercus>: " + qrqRel1.invoked());
        System.out.println("Relation von MultiGroup<Quercus,QuercusRobur>: " + qqrRel1.invoked());
        System.out.println("Relation von MultiGroup<Quercus,Quercus>: " + qqRel1.invoked());
        System.out.println("Relation von MultiGroup<QuercusRobur,Tree>: " + qrtRel1.invoked());
        System.out.println("Relation von MultiGroup<Quercus,Tree>: " + qtRel1.invoked());
        System.out.println("Relation von MultiGroup<Integer,Integer>: " + iRel2.invoked());
    }

}
