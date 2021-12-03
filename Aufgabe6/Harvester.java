public abstract class Harvester {
    //KOMMENTAR: Ein Harvester hat eine eindeutige Laufnummer (harvesterNumber), die bei Initialisierung festgelegt wird.
    //           zusätzlich verfügt er über eine Information, wie lange er aktiv war (operationTime) und genau einen
    //           WorkingHead, der je nach Typ unterschiedliche Funktionen hat
    //HISTORY CONSTRAINT: numerusCurrens wird mit jeder initialisierung eines *Objekts vom Typ* Harvester um eins erhöht.
    //                    Die aktuelle Zahl ist die Laufnummer des letzten initialisierten Harvester
    //                    harvesterNumber wird mit initialisierung des Objekts zugewiesen und ist nicht veränderlich
    //Invarianten: nummerusCurrens & harvesterNumber > 0 //ToDo: aber erst sobald der erste initialisiert wird. ist das dann richtig?
    //             inBetrieb >= 0
    //             workingHead != null;

    private static int numerusCurrens;
    private final int harvesterNumber;
    private float operationTime;
    private WorkingHead workingHead;

    //KOMMENTAR: Erzeugt einen Harvester mit einem bestimmten workingHead, einer individuellen Laufnummer, sowie einer
    //           "leeren" operationTime (operationTime = 0)
    //VORBEDINGUNG: head != null
    public Harvester(WorkingHead head){
        numerusCurrens++;
        harvesterNumber = numerusCurrens;
        operationTime = 0;
        workingHead = head;
    }

    //KOMMENTAR: Erhöht die Betriebszeit um 0.1f
    public void increaseOpTime(){
        operationTime += 0.1f;
    }

    public float getOperationTime(){
        return operationTime;
    }

    //KOMMENTAR: Erhöht die zurückgelegte Distanz (wird in WheelHarvester und StrideHarvester überschrieben)
    //HISTORY CONSTRAINT: Die Operation-Time wird einmal pro Aufruf von raiseCoveredDistance erhöht /TODO: Ist das ein History Constraint?
    public void raiseCoveredDistance(){
        increaseOpTime();
    }

    public int getHarvesterNumber(){
        return harvesterNumber;
    }

    //Gibt die zurückgelegte Distanz aus (wird in WheelHarvester und StrideHarvester überschrieben)
    public abstract Number giveCoveredDistance();

    //KOMMENTAR: Rüstet den Arbeitskopf eines Harvesters um. Informationen über frühere Einsatzarten gehen hierbei verloren
    //VORBEDINGUNG: head != null
    public void changeHead(WorkingHead head){
        this.workingHead = head;
    }

    //KOMMENTAR: Liest je nach Art des WorkingHead die maximalen Stücklänge oder die maximalen Baumdicke aus
    public Number readHeadInformation(){
        return workingHead.readMax();
    }

    //KOMMENTAR: Wird für die Ausgabe verwendet, um in Text auszugeben, was die headInformation bedeutet
    public String getHeadMeaning(){
        return workingHead.meaning();
    }

    @Override
    public String toString() {
        return "Harvester: " +
                "\nharvesterNumber: " + harvesterNumber +
                "\noperationTime: " + operationTime +
                "\nworkingHead: " + workingHead.getClass();
    }

    //David
    public WorkingHead getWorkingHead(){return workingHead;}
}
