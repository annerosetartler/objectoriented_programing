public class QuercusRobur extends Quercus {
    //KOMMENTAR: QuercusRobur ist ein Untertyp von Quercus
    //           Zusätzlich zur Baumhöhe und Stammweite gibt es hier noch einen
    //           String (resistance), der eine textuelle Beschreibung der Wiederstandsfähigkeit
    //           gegenüber Klimaschwankungen beinhaltet.
    //INV: height > 0
    //     trunkHeight > 0
    //     trunkHeight < height
    private Integer height;
    private Integer trunkHeight;
    private String resistance;

    //VORB: height > 0
    //      trunkHeight > 0
    //     trunkHeight < height
    public QuercusRobur(Integer height, Integer trunkHeight, String resistance) {
        super(height, trunkHeight);
        this.height = height;
        this.trunkHeight = trunkHeight;
        this.resistance = resistance;
    }

    //NACHB: gibt den String resistance zurück
    public String resistance() {
        return resistance;
    }

    public String toString() {
        return "QuercusRobur(" +
                "height=" + height +
                ", trunkHeight=" + trunkHeight +
                ", resistance=" + resistance + ')';
    }
}
