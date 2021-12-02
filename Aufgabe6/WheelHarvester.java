public class WheelHarvester extends Harvester{
    //KOMMENTAR: Ein WheelHarvester ist ein Harvester, der sich auf Rädern bewegt und dementsprechen zusätzlich eine
    //           Information über die von ihm zurückgelegten Meter (als Fließkommazahl) enthält.
    //INVARIANTE: coveredMeters >= 0;
    private Float coveredMeters;

    //KOMMENTAR: Erzeugt einen Harvester, der zusätzlich zu den normalen Variablen und Funktionen noch eine
    //           Float-Variable speichert, die für die Anzahl der von ihm zurückgelegten Meter steht
    //VORBEDINGUNG: head != null
    public WheelHarvester(WorkingHead head) {
        super(head);
        coveredMeters = 0.0f;
    }

    //KOMMENTAR: Erhöht die zurückgelegten Meter um 0.3f //ToDo: soll das dabei stehen: und ruft die gleichnamige Methode der Oberklasse auf (erhöht die OperationTime um 0.1f)
    @Override
    public void raiseCoveredDistance(){
        super.raiseCoveredDistance();
        coveredMeters += 0.3f;
    }

    //KOMMENTAR: Gibt die zurückgelegten Meter (als Float) zurück
    @Override
    public Float giveCoveredDistance(){
        return coveredMeters;
    }
}
