public class QuercusRobur implements Quercus, LightDemanding, ContinentalClimate {

    //INV: size > 0
    private float size;
    private float longitude;
    private float latitude;
    private float incidence;
    private static float trunkSlope = 0.75f;
    private static final String genus = "Quercus";
    private static final String family = "Fagaceae";
    private static final String name = "Quercus Robur";

    public QuercusRobur(float s, float longitude, float latitude, float incidence){
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

    @Override
    public float trunkSlope() {
        return trunkSlope;
    }

    @Override
    public String genus() {
        return genus;
    }

    @Override
    public String family() {
        return family;
    }
}
