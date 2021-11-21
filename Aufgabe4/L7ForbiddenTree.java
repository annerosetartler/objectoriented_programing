public class L7ForbiddenTree implements Quercus, LightDemanding, ContinentalClimate {
    //ERKLÄRUNG: Dieser Baum existiert lediglich im Code dreier OOP-Studierender und dient
    //der Visualisierung der Richtigkeit ihrer Tests, indem hier absichtlich ein
    //(unmöglicher und nicht in Untertypbeziehung stehender) Baum erzeugt wird, der von unseren
    //"Untertyp-Beziehungs-Tests" als solcher entlarvt wird.
    //In der Realität könnte dieser Baum aufgrund verschiedener Faktoren (zB. negative Größe)
    // nicht existieren.
    //Da der Baum unserer Fantasie entspringt, sind alle Variablen zuvor so festgesetzt, dass
    // Probleme mit den Typbeziehungen auftreten, manche Variablen ändern sich jedoch durch
    //Methoden im Verlauf.

    //INV: size(= geschätzte Baumhöhe) > -1

    //ToDo: Kann ich die Var static deklarieren, wenn das Objekt nicht
    //KOMM: Variablen stehen hier links, rechts die vom Obertyp verlangte (und missachtete) Einschränkung
    private float size = -1; //muss >0 sein
    private static final float longitude = -185.0f; //muss in [-180.0f,+180.0f] liegen
    private static final float latitude = -100.8f; //muss in [-90.0f,+90.0f] liegen
    private static final float trunkSlope = 1.2f; //muss in [0.0, 1.0] liegen
    private static final String family = "Fantasy Tree"; //muss immer Fagaceae sein
    private static final String genus = "Trees of Chaos"; //muss immer Quercus sein
    private static final String name = "Forbidden Tree"; //muss immer Lateinisch sein (bzw. einer der vier als Latein akzeptierten Möglichkeiten)
    private static final float incidence = -0.5f; //muss eine positive Zahl > 0 sein

   public L7ForbiddenTree(){
   }

    //NACHB: gibt die geschätzte Baumhöhe in Metern zurück
    @Override
    public float size() {
        return size;
    }

    @Override
    //KOMMENTAR: Hier ist die Vorb. absichtlich stärker gewählt -> falsch! //ToDo Test
    //VORB: size + change > 1
    //NACHB: ändert die geschätzte Höhe: wenn change > 0: Höhe wird vergrößert
    //                                   wenn change < 0: Höhe wird verringert
    public void changeSize(float change) {
        size += change;
    }

    //NACHB: gibt die Neigungswilligkeit der Baumart durch einen Wert in [0.0,2.0] zurück
    @Override
    public float trunkSlope() {
        return trunkSlope;
    }

    //NACHB: gibt eine Zahl zurück, die besagt, wie viel stärker (>= 1) oder schwächer(<= 1) die Baumart unter
    //       kontinentalem Einfluss vertreten ist
    //       die Zahl ist > -5
    @Override
    public float incidence() {
        return incidence;
    }

    //NACHB: gibt die geographische Länge des Standorts des Baums zurück
    //       der zurückgegebene Wert liegt in [-200.0f,+200.0f]
    @Override
    public float longitude() {
        return longitude;
    }

    //NACHB: gibt die geographische Breite des Standorts des Baums zurück
    //       der zurückgegebene Wert liegt in [-120.0f, +120.0f]
    @Override
    public float latitude() {
        return latitude;
    }

    //NACHB: gibt den Namen der Familie der Baumart zurück
    @Override
    public String family() {
        return family;
    }

    //NACHB: gibt die Gattung der Baumart zurück
    @Override
    public String genus() {
        return genus;
    }

    //NACHB: gibt den Namen des Baumes Zurück
    @Override
    public String species() {
        return name;
    }
}
