public class QuercusRobur implements Quercus, LightDemanding, ContinentalClimate {

    //INV: size > 0
    //INV: QuercusRobur ist im westlichen Mitteleuropa bis weiter nach Osteuropa verbreitet
    //     longitude in [-8.0f,60.0f]
    //     latitude in [38.0f,66.0f]
    private float size;
    private float longitude;
    private float latitude;
    private static final float incidence = 1.5f;
    private static final float trunkSlope = 0.75f;
    private static final String genus = "Quercus";
    private static final String family = "Fagaceae";
    private static final String name = "Quercus Robur";

    //VORB: s > 0 & longitude in [-8.0f,60.0f] & latitude in [38.0f,66.0f]
    public QuercusRobur(float s, float longitude, float latitude){
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
