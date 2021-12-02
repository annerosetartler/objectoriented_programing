public class Shredder implements WorkingHead<Integer> {
    //KOMMENTAR: Shredder ist eine implementierung eines WorkingHead, der eine Information über die maximale Dicke hat, die ein
    //           verarbeitbarer Baum haben kann
    //INVARIANTE: maxWidth > 0  KOMMENTAR: maximale Dicke eines zu Verarbeitenden Baumes in cm;
    private int maxWidth;

    public Shredder(int maxWidth){
        this.maxWidth = maxWidth;
    }

    @Override
    public Integer read() {
        return maxWidth;
    }
}
