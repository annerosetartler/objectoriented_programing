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

    //VORB: h != null
    //NACHB: entfernt einen Holzvollernter h aus der Liste, wenn dieser in der Liste vorhanden ist
    public void remove(Harvester h) {
        if (h == null) {
            return;
        } else {
            boolean keepSearching = true;
            for (Iterator it = holzvollernter.iterator(); it.hasNext() && keepSearching; ) {
                Harvester hn = (Harvester) it.next();
                if (h.equals(hn)) {
                    it.remove();
                    keepSearching = false;
                }
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

    //KOMMENTAR: Durchschnitt Betriebstunden alle Holzvollernter
    public float durchschnittStundenalleHolzvollernter() {
        float summe = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            summe += hn.getOperationTime();
        }
        return summe / holzvollernter.getSize();
    }

    //KOMMENTAR: Durchscnitt Betriebstunden aller Holzvollernter die Holz in stücke schneiden
    public float durchschnittStundenalleStücke() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper) {
                counter++;
                summe += hn.getOperationTime();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Betriebstunden aller Holzvollernter die Holz in Hack schneiden
    public float durchschnittStundenalleHack() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Shredder) {
                counter++;
                summe += hn.getOperationTime();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Betriebstunden aller Holzvollernter mit Rädern
    public float durchschnittStundenalleRäder() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof WheelHarvester) {
                counter++;
                summe += hn.getOperationTime();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Betriebstunden aller Holzvollernter mit schreitbeinen
    public float durchschnittStundenalleSchreitbeine() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof StrideHarvester) {
                counter++;
                summe += hn.getOperationTime();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Wegstrecker aller Holzvollernter mit Räder
    public float durchschnittWegalle() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof WheelHarvester) {
                counter++;
                summe += ((WheelHarvester) hn).giveCoveredDistance();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Wegstrecker aller Holzvollernter mit Räder die Holz in Stücke schneiden
    public float durchschnittWegalleStücke() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof WheelHarvester && hn.getWorkingHead() instanceof Chopper) {
                counter++;
                summe += ((WheelHarvester) hn).giveCoveredDistance();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Wegstrecker aller Holzvollernter mit Räder die Holz in Stücke schneiden
    public float durchschnittWegalleHack() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof WheelHarvester && hn.getWorkingHead() instanceof Shredder) {
                counter++;
                summe += ((WheelHarvester) hn).giveCoveredDistance();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Schritte aller Holzvollernter mit Beinen
    public float durchschnittSchritte() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof StrideHarvester) {
                counter++;
                summe += ((StrideHarvester) hn).giveCoveredDistance();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Schritte aller Holzvollernter mit Beinen die Holz in Stücke schneiden
    public float durchschnittSchritteStücke() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof StrideHarvester && hn.getWorkingHead() instanceof Chopper) {
                counter++;
                summe += ((StrideHarvester) hn).giveCoveredDistance();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnitt Schritte aller Holzvollernter mit Beinen die Holz in Stücke schneiden
    public float durchschnittSchritteHack() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof StrideHarvester && hn.getWorkingHead() instanceof Shredder) {
                counter++;
                summe += ((StrideHarvester) hn).giveCoveredDistance();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Größte maximale Stücklänge insgesamt
    public float maxStücklänge() {
        float max = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper) {
                if (max < ((Chopper) hn.getWorkingHead()).readMax()) {
                    max = ((Chopper) hn.getWorkingHead()).readMax();
                }
            }
        }
        return max;
    }

    //KOMMENTAR: Größte maximale Stücklänge Räder
    public float maxStücklängeRäder() {
        float max = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper && hn instanceof WheelHarvester) {
                if (max < ((Chopper) hn.getWorkingHead()).readMax()) {
                    max = ((Chopper) hn.getWorkingHead()).readMax();
                }
            }
        }
        return max;
    }

    //KOMMENTAR: Größte maximale Stücklänge Schreiter
    public float maxStücklängeSchreiter() {
        float max = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper && hn instanceof StrideHarvester) {
                if (max < ((Chopper) hn.getWorkingHead()).readMax()) {
                    max = ((Chopper) hn.getWorkingHead()).readMax();
                }
            }
        }
        return max;
    }


    //KOMMENTAR: Kleinste maximale Stücklänge insgesamt
    public float minStücklänge() {
        float min = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper) {
                if (min == 0.0f) {
                    min = ((Chopper) hn.getWorkingHead()).readMax();
                } else if (min > ((Chopper) hn.getWorkingHead()).readMax()) {
                    min = ((Chopper) hn.getWorkingHead()).readMax();
                }
            }
        }
        return min;
    }

    //KOMMENTAR: Kleinste maximale Stücklänge Räder
    public float minStücklängeRäder() {
        float min = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper && hn instanceof WheelHarvester) {
                if (min == 0.0f) {
                    min = ((Chopper) hn.getWorkingHead()).readMax();
                } else if (min > ((Chopper) hn.getWorkingHead()).readMax()) {
                    min = ((Chopper) hn.getWorkingHead()).readMax();
                }
            }
        }
        return min;
    }

    //KOMMENTAR: Kleinste maximale Stücklänge Schreiter
    public float minStücklängeSchreiter() {
        float min = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Chopper && hn instanceof StrideHarvester) {
                if (min == 0.0f) {
                    min = ((Chopper) hn.getWorkingHead()).readMax();
                } else if (min > ((Chopper) hn.getWorkingHead()).readMax()) {
                    min = ((Chopper) hn.getWorkingHead()).readMax();
                }
            }
        }
        return min;
    }

    //KOMMENTAR: Durchschnittliche Baumdicke aller Holzvollernter
    public float durchschnittDicke() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Shredder) {
                counter++;
                summe += ((Shredder) hn.getWorkingHead()).readMax();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnittliche Baumdicke aller Holzvollernter mit Rädern
    public float durchschnittDickeRäder() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Shredder && hn instanceof WheelHarvester) {
                counter++;
                summe += ((Shredder) hn.getWorkingHead()).readMax();
            }
        }
        return summe / counter;
    }

    //KOMMENTAR: Durchschnittliche Baumdicke aller Holzvollernter mit Rädern
    public float durchschnittDickeSchreiter() {
        float summe = 0.0f;
        float counter = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn.getWorkingHead() instanceof Shredder && hn instanceof StrideHarvester) {
                counter++;
                summe += ((Shredder) hn.getWorkingHead()).readMax();
            }
        }
        return summe / counter;
    }

    public String toString() {
        if (holzvollernter.getSize() == 0) {
            return "{ }";
        }
        String s = "{ ";
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
}
