public class FagusSylvatica implements Fagaceae, Domestic {

    //INV: size > 0
    //INV: FagusSylvatica ist in Europa verbreitet
    //     longitude in [-8.0f,30.0f]
    //     latitude in [38.0f,60.0f]
    private float size;
    private float longitude;
    private float latitude;
    private static final String family = "Fagaceae";
    private static final String name = "Fagus Sylvatica";

    //VORB: s > 0 & longitude in [-8.0f,30.0f] & latitude in [38.0f,60.0f]
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
    //VORB: size + change > 0
    public void changeSize(float change) { size += change; }

    @Override
    public String family() {
        return family;
    }
}
