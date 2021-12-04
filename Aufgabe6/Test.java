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
        System.out.println("\nCovered distance at beginning: " + (Float) wheelHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Float) wheelHarvester.giveCoveredDistance(), 0.0f));
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
        System.out.println("\nCovered distance at beginning: " + (Integer) strideHarvester.giveCoveredDistance());
        System.out.println("Covered distance check: " + testParameters((Integer) strideHarvester.giveCoveredDistance(), 0));
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
        System.out.println("Covered distance: " + (Integer) strideHarvester.giveCoveredDistance());
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


        System.out.println("\nTests zu Forstbetrieb");
        Forstbetrieb fbOberndorf = new Forstbetrieb("FBOberndorf");
        WheelHarvester WheelOb1 = new WheelHarvester(new Chopper(0.6f));
        WheelHarvester WheelOb2 = new WheelHarvester(new Shredder(56));
        StrideHarvester StrideOb1 = new StrideHarvester(new Chopper(0.7f));
        StrideHarvester StrideOb2 = new StrideHarvester(new Shredder(60));

        Forstbetrieb fbWeidau = new Forstbetrieb("FBWeidau");
        WheelHarvester WheelWe1 = new WheelHarvester(new Chopper(0.3f));
        StrideHarvester StrideWe1 = new StrideHarvester(new Chopper(0.8f));
        StrideHarvester StrideWe2 = new StrideHarvester(new Shredder(2));

        Forstbetrieb fbStJohann = new Forstbetrieb("FBSt.Johann");
        WheelHarvester WheelSt1 = new WheelHarvester(new Chopper(0.4f));
        WheelHarvester WheelSt2 = new WheelHarvester(new Shredder(32));

        System.out.println("Tests zu add in Forstbetrieb: ");
        int f1 = fbOberndorf.getSize();
        System.out.println("Holzvollernter vor dem befüllen: " + f1);
        fbOberndorf.add(WheelOb1);
        fbOberndorf.add(WheelOb2);
        fbOberndorf.add(StrideOb1);
        fbOberndorf.add(StrideOb2);
        fbStJohann.add(WheelSt1);
        fbStJohann.add(WheelSt2);
        fbWeidau.add(StrideWe1);
        fbWeidau.add(StrideWe2);
        fbWeidau.add(WheelWe1);

        System.out.println(fbOberndorf.getName() + "nach dem befüllen: Größe jetzt 4: " + testParameters(4, fbOberndorf.getSize()));
        System.out.println(fbOberndorf.toString());
        System.out.println(fbStJohann.getName() + "nach dem befüllen: Größe jetzt 2: " + testParameters(2, fbStJohann.getSize()));
        System.out.println(fbStJohann.toString());
        System.out.println(fbWeidau.getName() + "nach dem befüllen: Größe jetzt 3: " + testParameters(3, fbWeidau.getSize()));
        System.out.println(fbWeidau.toString());
        fbOberndorf.add(null);
        System.out.println(fbOberndorf.getName() + "nach einfügen von null: Größe immer noch 4: " + testParameters(4, fbOberndorf.getSize()));
        System.out.println(fbStJohann.toString());

        fbStJohann.add(WheelSt1);
        System.out.println("Befüllen mit bereits vorhandenem Element: Größe immer noch 2: " + testParameters(2, fbStJohann.getSize()));
        System.out.println(fbStJohann.toString());

        System.out.println("Tests zu remove in Forstbetrieb: ");
        fbOberndorf.remove(3);
        System.out.println("Entfernen eines Elements: Größe jetzt 3: " + testParameters(3, fbOberndorf.getSize()));
        System.out.println(fbOberndorf.toString());

        System.out.println("Tests zum ändern von Informationen eines Holzvollernters in Forstbetrieb: ");
        System.out.println("Ändern der Information eines Holzvollernters:\nShredder -> Chopper: Davor:  " + WheelOb2.getWorkingHead().getClass());
        fbOberndorf.change(4, new Chopper(0.8f));
        System.out.println("Shredder -> Chopper: Danach: " + WheelOb2.getWorkingHead().getClass());
        System.out.println(fbOberndorf.toString());

        fbOberndorf.add(WheelOb1);
        fbOberndorf.change(4, new Shredder(32));

        WheelOb1.raiseCoveredDistance();
        WheelOb2.raiseCoveredDistance();
        WheelOb2.raiseCoveredDistance();
        WheelOb2.raiseCoveredDistance();
        WheelOb2.raiseCoveredDistance();
        StrideOb1.raiseCoveredDistance();
        StrideOb1.raiseCoveredDistance();
        StrideOb1.raiseCoveredDistance();
        StrideOb2.raiseCoveredDistance();
        StrideOb2.raiseCoveredDistance();
        System.out.println(fbOberndorf.toString());

        System.out.println("\nBerechnung statistischer Werte zu fbOberndorf:\n");
        System.out.println("Berechnung per Hand zur Kontrolle:\n");
        System.out.println("Durchschnittliche Betriebstundenanzahl aller Holzvollernter zusammen und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 0.25\n" +
                "Schneider: 0.2\n" +
                "Hacker: 0.3\n" +
                "\n" +
                "Durchschnittliche Betriebsstundenanzahl aufgeschlüsselt nach Holzvollernterart:\n" +
                "Schreiter: 0.25\n" +
                "Radernter: 0.25\n" +
                "\n" +
                "Durchschnittliche Wegstrecker aller Radernter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 0.75\n" +
                "Schneider: 0.3\n" +
                "Hacker: 1.2\n" +
                "\n" +
                "Durchschnittliche Schritte aller Schreiter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 2.5\n" +
                "Schneider: 3.0\n" +
                "Hacker: 2.0\n" +
                "\n" +
                "Gibt die kleinste und größte maximale Stücklänge aller Holzvollernter mit Schneidearbeitskopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters an: \n" +
                "Alle: \n" +
                "Min: 0.6\n" +
                "Max: 0.7\n" +
                "Radernter: \n" +
                "Min: 0.6\n" +
                "Max: 0.6\n" +
                "Schreiter: \n" +
                "Min: 0.7\n" +
                "Max: 0.7\n" +
                "\n" +
                "Die durchschnittliche Baumdicke aller Holzvollernter mit Hackschnitzelkopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters: \n" +
                "Alle: 46.0\n" +
                "Radernter: 32.0\n" +
                "Schreiter: 60.0\n");
        System.out.println("Berechnung durch Methoden:\n");
        System.out.println(fbOberndorf.statToString());

        WheelSt1.raiseCoveredDistance();
        WheelSt1.raiseCoveredDistance();
        WheelSt2.raiseCoveredDistance();
        WheelSt2.raiseCoveredDistance();
        WheelSt2.raiseCoveredDistance();

        System.out.println("\nBerechnung statistischer Werte zu fbSt.Johann:\n");
        System.out.println("Berechnung per Hand zur Kontrolle:\n");
        System.out.println("Durchschnittliche Betriebstundenanzahl aller Holzvollernter zusammen und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 0.25\n" +
                "Schneider: 0.2\n" +
                "Hacker: 0.3\n" +
                "\n" +
                "Durchschnittliche Betriebsstundenanzahl aufgeschlüsselt nach Holzvollernterart:\n" +
                "Schreiter: 0.0\n" +
                "Radernter: 0.25\n" +
                "\n" +
                "Durchschnittliche Wegstrecker aller Radernter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 0.75\n" +
                "Schneider: 0.6\n" +
                "Hacker: 0.90000004\n" +
                "\n" +
                "Durchschnittliche Schritte aller Schreiter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 0.0\n" +
                "Schneider: 0.0\n" +
                "Hacker: 0.0\n" +
                "\n" +
                "Gibt die kleinste und größte maximale Stücklänge aller Holzvollernter mit Schneidearbeitskopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters an: \n" +
                "Alle: \n" +
                "Min: 0.4\n" +
                "Max: 0.4\n" +
                "Radernter: \n" +
                "Min: 0.4\n" +
                "Max: 0.4\n" +
                "Schreiter: \n" +
                "Min: 3.4028235E38\n" +
                "Max: 0.0\n" +
                "\n" +
                "Die durchschnittliche Baumdicke aller Holzvollernter mit Hackschnitzelkopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters: \n" +
                "Alle: 32.0\n" +
                "Radernter: 32.0\n" +
                "Schreiter: 0.0\n");
        System.out.println("Berechnung durch Methoden:\n");
        System.out.println(fbStJohann.statToString());

        WheelWe1.raiseCoveredDistance();
        WheelWe1.raiseCoveredDistance();
        WheelWe1.raiseCoveredDistance();
        WheelWe1.raiseCoveredDistance();
        WheelWe1.raiseCoveredDistance();
        StrideWe1.raiseCoveredDistance();
        StrideWe1.raiseCoveredDistance();
        StrideWe1.raiseCoveredDistance();
        StrideWe2.raiseCoveredDistance();
        StrideWe2.raiseCoveredDistance();

        System.out.println("\nBerechnung statistischer Werte zu fbWeidau:\n");
        System.out.println("Berechnung per Hand zur Kontrolle:\n");
        System.out.println("Durchschnittliche Betriebstundenanzahl aller Holzvollernter zusammen und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 0.33333334\n" +
                "Schneider: 0.4\n" +
                "Hacker: 0.2\n" +
                "\n" +
                "Durchschnittliche Betriebsstundenanzahl aufgeschlüsselt nach Holzvollernterart:\n" +
                "Schreiter: 0.25\n" +
                "Radernter: 0.5\n" +
                "\n" +
                "Durchschnittliche Wegstrecker aller Radernter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 1.5\n" +
                "Schneider: 1.5\n" +
                "Hacker: 0.0\n" +
                "\n" +
                "Durchschnittliche Schritte aller Schreiter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n" +
                "Alle: 2.5\n" +
                "Schneider: 3.0\n" +
                "Hacker: 2.0\n" +
                "\n" +
                "Gibt die kleinste und größte maximale Stücklänge aller Holzvollernter mit Schneidearbeitskopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters an: \n" +
                "Alle: \n" +
                "Min: 0.3\n" +
                "Max: 0.8\n" +
                "Radernter: \n" +
                "Min: 0.3\n" +
                "Max: 0.3\n" +
                "Schreiter: \n" +
                "Min: 0.8\n" +
                "Max: 0.8\n" +
                "\n" +
                "Die durchschnittliche Baumdicke aller Holzvollernter mit Hackschnitzelkopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters: \n" +
                "Alle: 2.0\n" +
                "Radernter: 0.0\n" +
                "Schreiter: 2.0\n");
        System.out.println("Berechnung durch Methoden:\n");
        System.out.println(fbWeidau.statToString());


        Forstbetrieb exceptionStats = new Forstbetrieb("excepetion");
        try{
            exceptionStats.avgOperationTime(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //KOMMENTAR: Tests zu Regionen
        Forstbetrieb fb1 = new Forstbetrieb("Angerer");
        fb1.add(new WheelHarvester(new Chopper(0.1f)));
        fb1.add(new WheelHarvester(new Shredder(4)));
        fb1.add(new StrideHarvester(new Chopper(0.2f)));
        fb1.add(new StrideHarvester(new Shredder(7)));
        Forstbetrieb fb2 = new Forstbetrieb("Maurer");
        fb2.add(new WheelHarvester(new Chopper(0.3f)));
        fb2.add(new WheelHarvester(new Shredder(4)));
        fb2.add(new WheelHarvester(new Shredder(2)));
        fb2.add(new WheelHarvester(new Shredder(5)));
        Forstbetrieb fb3 = new Forstbetrieb("Holzer");
        fb3.add(new StrideHarvester(new Chopper(0.5f)));
        fb3.add(new StrideHarvester(new Shredder(2)));
        fb3.add(new StrideHarvester(new Chopper(0.1f)));
        fb3.add(new StrideHarvester(new Chopper(0.7f)));
        Forstbetrieb fb4 = new Forstbetrieb("ForstGmbH");
        fb4.add(new StrideHarvester(new Chopper(0.5f)));
        fb4.add(new StrideHarvester(new Chopper(0.2f)));
        Forstbetrieb fb5 = new Forstbetrieb("Quercusse");
        fb5.add(new StrideHarvester(new Shredder(8)));
        fb5.add(new StrideHarvester(new Shredder(4)));
        Forstbetrieb fb6 = new Forstbetrieb("Fagusse");

        System.out.println("Tests zu Regionen:");
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

    //NACHB: Gibt true zurück  wenn float1 und float2 um weniger als 0.00001 voneinander abweichen
    private static boolean testParameters(Float float1, Float float2) {
        return Math.abs(float1 - float2) < 0.00001;
    }

    //NACHB: Gibt true zurück wenn int1 == int2
    private static boolean testParameters(Integer int1, Integer int2) {
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
statistische Methoden in Forstbetrieb: David & (Annerose & Maria)

**************************************************************************************************************************
 */