public class QuercusRobur extends Quercus {
    private Integer height;
    private Integer trunkHeight;
    private String resistance;

    public QuercusRobur(Integer height, Integer trunkHeight, String resistance) {
        super(height, trunkHeight);
        this.resistance = resistance;
    }

    public String resistance(){
        return resistance;
    }
}
//kommentar