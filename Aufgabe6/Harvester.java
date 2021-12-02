public abstract class Harvester<X> { //ToDo: Das geht so nicht, weil es ja nciht vom Harvester abhängt, welches Parameter es gibt...
    //ToDo: checken ob das so eingeordnet richtig ist...
    //KOMMENTAR: Ein Harvester hat eine Laufnummer (harvesterNumber) ... genau eine Aufgabe (einen Kopf) ToDo ergänzen
    //Invarianten: nummerusCurrens & harvesterNumber > 0 //ToDo: aber erst sobald der erste initialisiert wird. ist das dann richtig?
    //             inBetrieb >= 0
    //             workingHead != null;
    //HISTORY CONSTRAINT: numerusCurrens wird mit jeder initialisierung eines *Objekts vom Typ* Harvester um eins erhöht.
    //                    Die aktuelle Zahl ist die Laufnummer des letzten initialisierten Harvester
    //                    harvesterNumber wird mit initialisierung des Objekts zugewiesen und ist nicht veränderlich

    private static int numerusCurrens;
    private final int harvesterNumber;
    private float operationTime;
    private WorkingHead workingHead;

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

    //KOMMENTAR: Erhöht die Betriebszeit um einen durch die Eingabe definierten Wert
    //VORBEDINGUNG: additionalTime >= 0;
    public void increaseOpTime(float additionalTime){
        operationTime += additionalTime;
    }

    public float getOperationTime(){
        return operationTime;
    }

    //Erhöht die zurückgelegte Distanz (wird in WheelHarvester und StrideHarvester überschrieben)
    //Die Operation-Time wird einmal pro Aufruf erhöht ToDo: hab ich mir selbst überlegt, passt das so?
    public void raiseCoveredDistance(){
        increaseOpTime();
    }

    //Gibt die zurückgelegte Distanz aus (wird in WheelHarvester und StrideHarvester überschrieben) //ToDo: Schirch und noch falsch
    public X giveCoveredDistance(){
        return null;
    }

    //KOMMENTAR: Rüstet den Arbeitskopf eines Harvesters um, Informationen über frühere Einsatzarten gehen hierbei verloren
    //VORBEDINGUNG: head != null
    public void changeHead(WorkingHead head){
        this.workingHead = head;
    }

    //KOMMENTAR: Liest je nach Art des WorkingHead die maximalen Stücklänge oder die maximalen Baumdicke aus //ToDo: Schirch und noch falsch
    public X readHeadInformation(){
        return (X) workingHead.read();
    }

}
