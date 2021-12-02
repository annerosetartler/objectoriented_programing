public class Chopper implements  WorkingHead<Float> {
    //KOMMENTAR: Shredder ist eine implementierung eines WorkingHead, der eine Information über die maximale Dicke hat, die ein
    //           verarbeitbarer Baum haben kann
    //INV: maxLength > 0  KOMMENTAR: maximale Länge eines Stücks in Meter
    private float maxLength;


    public Chopper(float maxLength){
        this.maxLength = maxLength;
    }


    @Override
    public Float read() {
        return maxLength;
    }
}
