public class WheelHarvester extends Harvester{
    //KOMMENTAR: Ein WheelHarvester ist ein Harvester, der sich auf Rädern bewegt und dementsprechen zusätzlich eine
    //           Information über die von ihm zurückgelegten Meter (als Fließkommazahl) enthält.
    //INVARIANTE: coveredMeters >= 0;
    //ToDo: Fehlt noch was?
    private float coveredMeters;

    //VORBEDINGUNG: head != null //ToDo: Muss ich das hier nochmal hinschreiben?
    public WheelHarvester(WorkingHead head) {
        super(head);
        coveredMeters = 0.0f;
    }

    //KOMMENTAR: Erhöht die zurückgelegten Meter um 0.4f;
    @Override
    public void raiseCoveredDistance(){
        super.raiseCoveredDistance();
        coveredMeters += 0.4f;
    }

    //KOMMENTAR: Gibt die zurückgelegten Meter (als Float) zurück
    @Override
    public Object giveCoveredDistance(){ //ToDo
        return coveredMeters;
    }
}
