public class CarpinusBetulus implements ContinentalClimate, Domestic {

    //INV: size > 0
    //INV: Carpinus Betulus ist in Mitteleuropa verbreitet
    //     longitude in [2.0f,25.0f]
    //     latitude in [45.0f,58.0f]
    private float size;
    private float longitude;
    private float latitude;
    private static final float incidence = 3.0f;
    private static final String name = "Carpinus Betulus";

    //VORB: s > 0 & longitude in [2.0f,25.0f] & latitude in [45.0f,58.0f]
    public CarpinusBetulus(float s, float longitude, float latitude){
        this.size = s;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public float incidence() {
        return incidence;
    }

    @Override
    public float longitude() {
        return longitude;
    }

    @Override
    public float latitude() { return latitude; }

    @Override
    public String species() {
        return name;
    }

    @Override
    public float size() { return size; }

    @Override
    //VORB: size + change > 0
    public void changeSize(float change) {
        size += change;
    }
}
