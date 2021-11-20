public class Fantasytree implements LightDemanding {

    //INV: size > 0
    private float size;
    private static final float trunkSlope = 0.7f;
    private static final String name = "Eucalyptus";

    //VORB: s > 0
    public Fantasytree(float s) {
        this.size = s;
    }


    @Override
    public String species() {
        return name;
    }

    @Override
    public float size() {
        return size;
    }

    @Override
    //VORB: size + change > 0
    public void changeSize(float change) {
        size += change;
    }

    @Override
    public float trunkSlope() {
        return trunkSlope;
    }
}
