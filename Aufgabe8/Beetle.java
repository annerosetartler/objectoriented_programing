public interface Beetle extends Runnable {
    //KOMMENTAR: Beetle repräsentiert eine Käferpopulation, die einen Wald (Forest) bewohnt

    //NACHB: beendet den Thread dieser Käferpopulation
    void endThread();

    //NACHB: gibt true zurück, wenn diese Käferpopulation die Leibspeise von Ameisenbuntkäfern ist//TODO: zu humorvoll? vlt. nicht 100% treffend. Eher: Wenn die ABK sich daran nciht die zähne ausbeißen^^
    boolean isPrey();

    //NACHB: gibt den für die Käferpopulationsart charakteristischen String zurück
    //       die Länge des zurückgegebenen Strings beträgt 1
    String getValueAsString();
}
