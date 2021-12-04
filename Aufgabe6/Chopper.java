public class Chopper implements  WorkingHead {
    //KOMMENTAR: Chopper ist eine implementierung eines WorkingHead, der eine Information über die maximale Länge hat,
    //           die verarbeitbares Stück Holz haben darf
    //INV: maxLength > 0
    private final Float maxLength;

    //VORBEDINGUNG: maxLength > 0
    public Chopper(Float maxLength){
        this.maxLength = maxLength;
    }

    //NACHBEDINGUNG: gibt die maximale Länge eines Stücks aus
    @Override
    public Number readMax() {
        return (Number) maxLength;
    }

    //KOMMENTAR: stellt eine ausformulierte Erklärung für die Interpretation der Variable maxLength bereit
    @Override
    public String meaning(){
        return "meter maximum trunk length";
    }
}
