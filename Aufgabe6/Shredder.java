public class Shredder implements WorkingHead {
    //KOMMENTAR: Shredder ist eine implementierung eines WorkingHead, der eine Information über die maximale Dicke hat,
    //           die ein verarbeitbares Stück Holz haben darf
    //INVARIANTE: maxWidth > 0
    private final Integer maxWidth;

    //VORBEDINGUNG: maxWidth > 0
    public Shredder(Integer maxWidth){
        this.maxWidth = maxWidth;
    }

    //NACHBEDINGUNG: gibt die maximale Dicke eines zu Verarbeitenden Baumes in cm (als Integer) aus //ToDo
    @Override
    public Number readMax() {
        return (Number) maxWidth;
    }

    @Override
    public String meaning(){
        return "cm maximum trunk thickness";
    }
}
