public class StrideHarvester extends Harvester {
    //KOMMENTAR: Ein WheelHarvester ist ein Harvester, der sich auf einer Art Beinen bewegtt und dementsprechen zusätzlich
    //           eine Information über die von ihm zurückgelegten Schritte (int-Wert) enthält.
    //INVARIANTE: steps >= 0;
    private int steps;

    //VORBEDINGUNG: head != null //ToDo: Muss ich das hier nochmal hinschreiben?
    public StrideHarvester(WorkingHead head) {
        super(head);
        steps = 0;
    }

    //KOMMENTAR: Erhöht die zurückgelegten Schritte um 1
    @Override
    public void raiseCoveredDistance(){
        super.raiseCoveredDistance();
        steps++;
    }

    //KOMMENTAR: Gibt die zurückgelegten Schritte aus
    @Override
    public Object giveCoveredDistance(){ //ToDo
        return steps;
    }

}
