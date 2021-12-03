public class Chopper implements  WorkingHead {
    //KOMMENTAR: Chopper ist eine implementierung eines WorkingHead, der eine Information über die maximale Länge hat,
    //           die verarbeitbares Stück Holz haben darf
    //INV: maxLength > 0
    private final Float maxLength;
    private final String headType = "chopper";

    //VORBEDINGUNG: maxLength > 0
    public Chopper(Float maxLength){
        this.maxLength = maxLength;
    }

    //NACHBEDINGUNG: gibt die maximale Länge eines Stücks in Meter aus
    @Override
    public Float readMax() {
        return maxLength;
    }

    @Override
    public String meaning(){
        return "meter maximum trunk length";
    }

    public String getHeadType(){
        return headType;
    }
}
