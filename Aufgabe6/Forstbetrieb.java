import java.util.Iterator;

public class Forstbetrieb {
    //KOMMENTAR: Forstbetrieb verwaltet Informationen über einen Forstbetrieb und wertet diese aus.
    //           Ein Forstbetrieb hat einen unveränderlichen Namen.
    //INVARIANTE: holzvollernter enthält keine Nulleinträge
    private final String name;
    private List holzvollernter;


    //VORB: name != null
    public Forstbetrieb(String name) {
        holzvollernter = new List();
        this.name = name;
    }

    //VORB: h != null
    //NACHB: fügt einen Holzvollernter h ans Ende der Liste hinzu, wenn dieser nicht in der Liste vorhanden ist
    public void add(Harvester h) {
        if (h == null) {
            return;
        } else {
            for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
                Harvester hn = (Harvester) it.next();
                if (h.equals(hn)) {
                    return;
                }
            }
            holzvollernter.add((Harvester) h);
        }
    }

    //VORB: num > 0
    //NACHB: entfernt einen Holzvollernter h aus der Liste, wenn dieser in der Liste vorhanden ist
    public void remove(int num) {
        boolean keepSearching = true;
        for (Iterator it = holzvollernter.iterator(); it.hasNext() && keepSearching; ) {
            Harvester hn = (Harvester) it.next();
            if (num == hn.getHarvesterNumber()) {
                it.remove();
                keepSearching = false;
            }
        }
    }

    //KOMMENTAR: Methode zum Ändern der Information von Holzvollerntern
    //VORB: head != null
    //      num >= 0
    //NACHB:ändert eine Information eines Holzvollernters wenn dieser in der Liste vorhanden ist
    //      sonst passiert nichts
    public void change(int num, WorkingHead head) {
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getHarvesterNumber() == num) {
                hn.changeHead(head);
            }
        }
    }

    //NACHB: Bei i == 1 wird die durchschnittliche Betriebstundenanzahl aller Holzvollernter
    //       und aufgeschlüsselt nach Einsatzart zurückgegeben
    //       sonst wird die durchschnittliche Betriebstundenanzahl aufgeschlüsselt nach Holzvollernterart
    //       wenn der Forstbetrieb noch keine Holzvollernter beinhaltet wird eine Exception geworfen
    public String avgOperationTime(int i) {
        if (holzvollernter.getSize() == 0) {
            throw new ArithmeticException("Division by 0!");
        }
        String result = "";
        if (i == 1) {
            float sumall = 0.0f;
            for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
                Harvester hn = (Harvester) it.next();
                sumall += hn.getOperationTime();
            }
            sumall = sumall / holzvollernter.getSize();
            result = "Durchschnittliche Betriebstundenanzahl aller Holzvollernter zusammen und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
            result += "Alle: " + sumall + "\n";
            result += "Schneider: " + avgOperationTimeObj(new Chopper(0.1f)) + "\n";
            result += "Hacker: " + avgOperationTimeObj(new Shredder(1));

        } else {
            result = "Durchschnittliche Betriebsstundenanzahl aufgeschlüsselt nach Holzvollernterart:\n";
            result += "Schreiter: " + avgOperationTimeObj(new StrideHarvester(new Chopper(0.1f))) + "\n";
            result += "Radernter: " + avgOperationTimeObj(new WheelHarvester(new Chopper(0.1f)));
        }
        return result;
    }

    //VORB: o Untertyp von Harvester || o Untertyp von WorkingHead
    //NACHB: Wird ein Untertyp von Harvester übergeben wird der Durchschnitt aller Arbeitsstunden
    //       des jeweiligen Harvesters zurückgegeben
    //       Wird ein Untertyp von Workinghead übergeben wird der Durchschnitt aller Arbeitsstunden
    //       der jeweiligen Holzvollernterart zurückgegeben
    //       sonst wird  0 zurückgegeben
    private float avgOperationTimeObj(Object o) {
        float summe = 0.0f;
        int counter = 0;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getClass().equals(o.getClass())) {
                counter++;
                summe += hn.getOperationTime();
            }
            if (hn.getWorkingHead().getClass().equals(o.getClass())) {
                counter++;
                summe += hn.getOperationTime();
            }
        }
        if (counter == 0) {
            return 0.0f;
        }
        return summe / counter;
    }

    //NACHB: Bei i == 1 wird die durchschnittliche Wegstrecker aller Radernter
    //       und aufgeschlüsselt nach Einsatzart zurückgegeben
    //       sonst wird die durchschnittliche Wegstrecker aller Schreiter
    //       und aufgeschlüsselt nach Einsatzart zurückgegeben
    //       wenn der Forstbetrieb noch keine Holzvollernter beinhaltet wird eine Exception geworfen
    public String avgWayLength(int i) {
        if (holzvollernter.getSize() == 0) {
            throw new ArithmeticException("Division by 0!");
        }
        String result = "";
        if (i == 1) {
            result = "Durchschnittliche Wegstrecker aller Radernter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
            result += "Alle: " + avgWayLengthObj(new WheelHarvester(new Shredder(1)), null) + "\n";
            result += "Schneider: " + avgWayLengthObj(new WheelHarvester(new Shredder(1)), new Chopper(0.1f)) + "\n";
            result += "Hacker: " + avgWayLengthObj(new WheelHarvester(new Shredder(1)), new Shredder(1));
        } else {
            result = "Durchschnittliche Schritte aller Schreiter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
            result += "Alle: " + avgWayLengthObj(new StrideHarvester(new Shredder(1)), null) + "\n";
            result += "Schneider: " + avgWayLengthObj(new StrideHarvester(new Shredder(1)), new Chopper(0.1f)) + "\n";
            result += "Hacker" + avgWayLengthObj(new StrideHarvester(new Shredder(1)), new Shredder(1));
        }
        return result;
    }

    //VORB: o != null
    //NACHB: Wird o2 = null übergeben wird der Durchschnitt
    //       des jeweiligen Harvesters zurückgegeben
    //       Wird für o2 != null übergeben wird der Durchschnitt
    //       des jeweiligen Harvesters und einer Einsatzart zurückgegeben
    //       sonst wird  0 zurückgegeben
    private Number avgWayLengthObj(Harvester o, WorkingHead o2) {
        Number summe;
        Float summef = 0.0f;
        Integer summei = 0;
        Integer counter = 0;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getClass().equals(o.getClass()) && (o2 == null ? true : hn.getWorkingHead().getClass().equals(o2.getClass()))) {
                counter++;
                if (hn instanceof WheelHarvester) {
                    summef += (Float) hn.giveCoveredDistance();
                } else {
                    summei += (Integer) hn.giveCoveredDistance();
                }
            }
        }
        if (counter == 0) {
            return 0.0f;
        }
        summe = (Number) (Math.max(summef, summei) / counter);
        return summe;
    }

    //NACHB: Gibt die kleinste und größte maximale Stücklänge aller
    //       Holzvollernter mit Schneidearbeitskopf eines Forstbetriebs
    //       insgesamt und aufgeschlüsselt nach Art des Holzvollernters an
    public String minMaxPiece() {
        String result = "";
        result = "Gibt die kleinste und größte maximale Stücklänge aller Holzvollernter mit Schneidearbeitskopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters an: \n";
        result += "Alle: \nMin: " + minMaxPieceObj(new Chopper(0.1f), null, 1) + "\n";
        result += "Max: " + minMaxPieceObj(new Chopper(0.1f), null, 0) + "\n";
        result += "Radernter: \nMin: " + minMaxPieceObj(new Chopper(0.1f), new WheelHarvester(new Chopper(0.1f)), 1) + "\n";
        result += "Max: " + minMaxPieceObj(new Chopper(0.1f), new WheelHarvester(new Chopper(0.1f)), 0) + "\n";
        result += "Schreiter: \nMin: " + minMaxPieceObj(new Chopper(0.1f), new StrideHarvester(new Chopper(0.1f)), 1) + "\n";
        result += "Max: " + minMaxPieceObj(new Chopper(0.1f), new StrideHarvester(new Chopper(0.1f)), 0);
        return result;
    }

    //VORB: o != null
    //NACHB: Bei i = 0 wird jeweils das Maximum berechnet
    //       sonst das Minimum
    //       Wird o2 = null übergeben wird das Maximum/Minimum für die
    //       jeweilige Holzvollernterart zurückgegeben
    //       Wird für o2 != null übergeben wird das Maximum/Minimum für
    //       jeweilige Holzvollernterart und den jeweiligen Ernter zurückgegeben
    private Float minMaxPieceObj(WorkingHead o, Harvester o2, int i) {
        Float minmax = 0.0f;
        minmax = i == 0 ? 0.0f : Float.MAX_VALUE;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead().getClass().equals(o.getClass()) && (o2 == null ? true : hn.getClass().equals(o2.getClass()))) {
                if (i == 0) {
                    minmax = Math.max(minmax, (Float) hn.getWorkingHead().readMax());
                } else {
                    minmax = Math.min(minmax, (Float) hn.getWorkingHead().readMax());
                }
            }
        }
        return minmax;
    }

    //NACHB: Gibt die durchschnittliche Baumdicke aller Holzvollernter
    //       mit Hackschnitzelkopf eines Forstbetriebs insgesamt
    //       und aufgeschlüsselt nach Art des Holzvollernter zurück
    public String avgThickness(){
        if (holzvollernter.getSize() == 0) {
            throw new ArithmeticException("Division by 0!");
        }
        String result = "Die durchschnittliche Baumdicke aller Holzvollernter mit Hackschnitzelkopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters: \n";
        result += "Alle: " + avgThicknessObj(new Shredder(1), null) + "\n";
        result += "Radernter: " + avgThicknessObj(new Shredder(1), new WheelHarvester(new Shredder(1))) + "\n";
        result += "Schreiter: " +avgThicknessObj(new Shredder(1), new StrideHarvester(new Shredder(1))) + "\n";
        return result;
    }

    //VORB: o != null
    //NACHB: Bei i = 0 wird jeweils das Maximum berechnet
    //       sonst das Minimum
    //       Wird o2 = null übergeben wird das Maximum/Minimum für die
    //       jeweilige Holzvollernterart zurückgegeben
    //       Wird für o2 != null übergeben wird das Maximum/Minimum für
    //       jeweilige Holzvollernterart und den jeweiligen Ernter zurückgegeben
    private Float avgThicknessObj(WorkingHead o, Harvester o2) {
        Float sum = 0.0f;
        Integer counter = 0;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead().getClass().equals(o.getClass()) && (o2 == null ? true : hn.getClass().equals(o2.getClass()))) {
                counter++;
                sum += (Integer) hn.getWorkingHead().readMax();
            }
        }
        if (counter == 0){
            return 0.0f;
        }
        return (Float)(sum / counter);
    }

    public String toString(int i) {
        if (holzvollernter.getSize() == 0) {
            return name + ": { }";
        }
        if (i != 1) {
            String s = "\n" + name + ": ";
            Iterator it = holzvollernter.iterator();
            Harvester hn = (Harvester) it.next();
            s += hn.toString();
            while (it.hasNext()) {
                hn = (Harvester) it.next();
                s += " " + hn.toString();
            }
            return s;
        }else{
            System.out.println("" + this.avgOperationTime(1));
            System.out.println("\n" + this.avgOperationTime(0));

            System.out.println("\n" + this.avgWayLength(1));
            System.out.println("\n" + this.avgWayLength(0));

            System.out.println("\n" + this.minMaxPiece());

            System.out.println("\n" + this.avgThickness());
            return "";
        }

    }

    //NACHB: gibt die Anzahl der Holzvollernter eines Forstbetriebs zurück
    public int getSize(){
        return holzvollernter.getSize();
    }

    public String getName() {
        return name;
    }
}
