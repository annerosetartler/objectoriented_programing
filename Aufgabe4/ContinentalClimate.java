public interface ContinentalClimate extends Domestic {

    //NACHB: gibt eine positive Zahl zurück, die besagt, wie viel stärker (>= 1) oder schwächer(<= 1) die Baumart unter
    //       kontinentalem Einfluss vertreten ist
    //       die Zahl ist > 0
    //TODO ist erste Nachbedingung nötig? unmöglich zu testen
    float incidence();
}
