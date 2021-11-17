public class FagusSylvatica implements Fagaceae, Domestic {

    //INV: size > 0
    private float size;
    private float longitude;
    private float latitude;
    private static final String family = "Fagaceae";
    private static final String name = "Fagus Sylvatica";

    public FagusSylvatica(float s, float longitude, float latitude){
        this.size = s;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public float longitude() {
        return longitude;
    }

    @Override
    public float latitude() {
        return latitude;
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
    public void changeSize(float change) {
        size += change;
    }

    @Override
    public String family() {
        return family;
    }
}
