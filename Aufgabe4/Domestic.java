public interface Domestic extends Tree{

    //NACHB: gibt die geographische Länge des Standorts des Baums zurück
    //       der zurückgegebene Wert liegt in [-180.0f,+180.0f]
    float longitude();

    //NACHB: gibt die geographische Breite des Standorts des Baums zurück
    //       der zurückgegebene Wert liegt in [-90.0f,+90.0f]
    float latitude();
}
