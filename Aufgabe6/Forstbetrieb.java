import java.util.Iterator;

//ToDo: Lieber David, könntest Du Dir überlegen, ob Du lieber die Methode "getWorkingHead" oder "getHeadType" von Harvester
// verwenden willst, und die andere(n) dann löschen? (alle drei toDo: Weg? - Methoden)

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
            result = "Durchschnitt aller Holzvollernter zusammen und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
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

    //KOMMENTAR: Durchschnitt Betriebstunden alle Holzvollernter
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

    public String minMaxPiece() {
        String result = "";
        result = "Durchschnittliche Wegstrecker aller Radernter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
        result += "Alle: \nMin: " + minMaxPieceObj(new Chopper(0.1f), null, 1) + "\n";
        result += "Max: " + minMaxPieceObj(new Chopper(0.1f), null, 0) + "\n";
        result += "Radernter: \nMin: " + minMaxPieceObj(new Chopper(0.1f), new WheelHarvester(new Chopper(0.1f)), 1) + "\n";
        result += "Max: " + minMaxPieceObj(new Chopper(0.1f), new WheelHarvester(new Chopper(0.1f)), 0) + "\n";
        result += "Schreiter: \nMin: " + minMaxPieceObj(new Chopper(0.1f), new StrideHarvester(new Chopper(0.1f)), 1) + "\n";
        result += "Max: " + minMaxPieceObj(new Chopper(0.1f), new StrideHarvester(new Chopper(0.1f)), 0) + "\n";
        return result;
    }

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

    public String toString() {
        if (holzvollernter.getSize() == 0) {
            return name + ": { }";
        }
        String s = name + ": { ";
        Iterator it = holzvollernter.iterator();
        Harvester hn = (Harvester) it.next();
        s += hn.toString();
        while (it.hasNext()) {
            hn = (Harvester) it.next();
            s += ", " + hn.toString();
        }
        s += " }";
        return s;
    }

    public String getName() {
        return name;
    }
}
