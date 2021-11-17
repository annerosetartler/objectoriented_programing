public class CarpinusBetulus implements ContinentalClimate {

    //INV: size > 0
    private float size;
    private float longitude;
    private float latitude;
    private float incidence;
    private static final String name = "Carpinus Betulus";

    public CarpinusBetulus(float s, float longitude, float latitude, float incidence){
        this.size = s;
        this.longitude = longitude;
        this.latitude = latitude;
        this.incidence = incidence;
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
}
