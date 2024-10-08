import java.util.Iterator;

public class Forstbetrieb {
    //KOMMENTAR: Forstbetrieb verwaltet Informationen über einen Forstbetrieb und wertet diese aus.
    //           Ein Forstbetrieb hat einen unveränderlichen Namen.
    //INVARIANTE: harvester-List enthält keine Nulleinträge
    private final String name;
    private List harvester;

    //KOMMENTAR: Erzeugung und Speichern jeweils einer Instanz zur Verwendung für die statischen Methoden
    private final static WorkingHead referenceShredder = new Shredder(5);
    private final static WorkingHead referenceChopper = new Chopper(0.1f);
    private final static Harvester referenceStrideH = new StrideHarvester(referenceChopper);
    private final static Harvester referenceWheelH = new WheelHarvester(referenceShredder);

    //VORB: name != null
    public Forstbetrieb(String name) {
        harvester = new List();
        this.name = name;
    }

    //VORB: h != null
    //NACHB: fügt einen Holzvollernter h ans Ende der Liste hinzu, wenn dieser nicht in der Liste vorhanden ist
    public void add(Harvester h) {
        if (h == null) {
            return;
        } else {
            for (Iterator it = harvester.iterator(); it.hasNext(); ) {
                Harvester hn = (Harvester) it.next();
                if (h.equals(hn)) {
                    return;
                }
            }
            harvester.add((Harvester) h);
        }
    }

    //VORB: num > 0
    //NACHB: entfernt einen Holzvollernter h aus der Liste, wenn dieser in der Liste vorhanden ist
    public void remove(int num) {
        boolean keepSearching = true;
        for (Iterator it = harvester.iterator(); it.hasNext() && keepSearching; ) {
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
        for (Iterator it = harvester.iterator(); it.hasNext(); ) {
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
        if (harvester.getSize() == 0) {
            throw new ArithmeticException("Division by 0!");
        }
        String result = "";
        if (i == 1) {
            float sumall = 0.0f;
            for (Iterator it = harvester.iterator(); it.hasNext(); ) {
                Harvester hn = (Harvester) it.next();
                sumall += hn.getOperationTime();
            }
            sumall = sumall / harvester.getSize();
            result = "Durchschnittliche Betriebstundenanzahl aller Holzvollernter zusammen und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
            result += "Alle: " + sumall + "\n";
            result += "Schneider: " + avgOperationTimeObj(referenceChopper) + "\n";
            result += "Hacker: " + avgOperationTimeObj(referenceShredder);

        } else {
            result = "Durchschnittliche Betriebsstundenanzahl aufgeschlüsselt nach Holzvollernterart:\n";
            result += "Schreiter: " + avgOperationTimeObj(referenceStrideH) + "\n";
            result += "Radernter: " + avgOperationTimeObj(referenceWheelH);
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
        for (Iterator it = harvester.iterator(); it.hasNext(); ) {
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
        if (harvester.getSize() == 0) {
            throw new ArithmeticException("Division by 0!");
        }
        String result = "";
        if (i == 1) {
            result = "Durchschnittliche Wegstrecker aller Radernter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
            result += "Alle: " + avgWayLengthObj(referenceWheelH, null) + "\n";
            result += "Schneider: " + avgWayLengthObj(referenceWheelH, referenceChopper) + "\n";
            result += "Hacker: " + avgWayLengthObj(referenceWheelH, referenceShredder);
        } else {
            result = "Durchschnittliche Schritte aller Schreiter und zusätzlich aufgeschlüsselt nach den Einsatzarten: \n";
            result += "Alle: " + avgWayLengthObj(referenceStrideH, null) + "\n";
            result += "Schneider: " + avgWayLengthObj(referenceStrideH, referenceChopper) + "\n";
            result += "Hacker: " + avgWayLengthObj(referenceStrideH, referenceShredder);
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
        for (Iterator it = harvester.iterator(); it.hasNext(); ) {
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
        for (Iterator it = harvester.iterator(); it.hasNext(); ) {
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
        if (harvester.getSize() == 0) {
            throw new ArithmeticException("Division by 0!");
        }
        String result = "Die durchschnittliche Baumdicke aller Holzvollernter mit Hackschnitzelkopf eines Forstbetriebs insgesamt und aufgeschlüsselt nach Art des Holzvollernters: \n";
        result += "Alle: " + avgThicknessObj(referenceShredder, null) + "\n";
        result += "Radernter: " + avgThicknessObj(referenceShredder, referenceWheelH) + "\n";
        result += "Schreiter: " +avgThicknessObj(referenceShredder, referenceStrideH) + "\n";
        return result;
    }

    //VORB: o != null
    //NACHB: Wird o2 = null übergeben wird die durchschnittliche Baumdicke für die
    //       jeweilige Holzvollernterart zurückgegeben
    //       Wird für o2 != null übergeben wird die die durchschnittliche Baumdicke für die
    //       jeweilige Holzvollernterart und den jeweiligen Ernter zurückgegeben
    private Float avgThicknessObj(WorkingHead o, Harvester o2) {
        Float sum = 0.0f;
        Integer counter = 0;
        for (Iterator it = harvester.iterator(); it.hasNext(); ) {
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

    //NACHB: Es wird der Inhalt von der Liste holzvollernter zurückgegeben
    public String toString() {
        if (harvester.getSize() == 0) {
            return "\n" + name + ": { }";
        }
        String s = "";
            s += "\n" + name + ": ";
            Iterator it = harvester.iterator();
            Harvester hn = (Harvester) it.next();
            s += hn.toString();
            while (it.hasNext()) {
                hn = (Harvester) it.next();
                s += " " + hn.toString();
            }
        return s;
    }

    //NACHB: Gibt die statistischen Methoden in einem String zurück.
    public String statToString(){
        String s = "";
        s += this.avgOperationTime(1) + "\n";
        s += "\n" +this.avgOperationTime(0) + "\n";
        s += "\n" +this.avgWayLength(1) + "\n";
        s += "\n" +this.avgWayLength(0) + "\n";
        s += "\n" +this.minMaxPiece() + "\n";
        s += "\n" +this.avgThickness();
        return s;
    }

    //NACHB: gibt die Anzahl der Holzvollernter eines Forstbetriebs zurück
    public int getSize(){
        return harvester.getSize();
    }

    //NACHB: gibt den Namen des Forstbetriebs zurück
    public String getName() {
        return name;
    }
}
