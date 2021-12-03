public class StrideHarvester extends Harvester {
    //KOMMENTAR: Ein WheelHarvester ist ein Harvester, der sich auf einer Art Beinen bewegt und dementsprechen zusätzlich
    //           eine Information über die von ihm zurückgelegten Schritte (Integer-Wert) enthält.
    //INVARIANTE: steps >= 0;
    private Integer steps;
    private final String harvesterType = "stride";

    //KOMMENTAR: Erzeugt einen Harvester, der zusätzlich zu den normalen Variablen und Funktionen noch eine
    //           Integer-Variable speichert, die für die Anzahl der von ihm zurückgelegten Schritte steht
    //VORBEDINGUNG: head != null
    public StrideHarvester(WorkingHead head) {
        super(head);
        steps = 0;
    }

    //KOMMENTAR: Erhöht die zurückgelegten Schritte um 1 und ruft die gleichnamige Methode der Oberklasse auf (erhöht die OperationTime um 0.1f)
    @Override
    public void raiseCoveredDistance(){
        super.raiseCoveredDistance();
        steps++;
    }

    //KOMMENTAR: Gibt die zurückgelegten Schritte aus
    @Override
    public Integer giveCoveredDistance() {
        return steps;
    }

    public String getType(){
        return harvesterType;
    }

    @Override
    public String toString() {
        String s = "\nWheel-";
        s += super.toString();
        s += "\ncovered steps: " + steps;
        return s;
    }
}
