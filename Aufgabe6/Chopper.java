public class Chopper implements  WorkingHead {
    //KOMMENTAR: Chopper ist eine implementierung eines WorkingHead, der eine Information über die maximale Länge hat,
    //           die verarbeitbares Stück Holz haben darf
    //INV: maxLength > 0
    private final Float maxLength;

    //VORBEDINGUNG: maxLength > 0
    public Chopper(Float maxLength){
        this.maxLength = maxLength;
    }

    //NACHBEDINGUNG: gibt die maximale Länge eines Stücks in Meter (als Float) aus //ToDo
    @Override
    public Number readMax() {
        return (Number) maxLength;
    }

    @Override
    public String meaning(){
        return "meter maximum trunk length";
    }
}
