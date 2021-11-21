public interface ContinentalClimate extends Tree {

    //NACHB: gibt eine positive Zahl zurück, die besagt, wie viel stärker (>= 1) oder schwächer(<= 1) die Baumart unter
    //       kontinentalem Einfluss vertreten ist
    //       die Zahl ist > 0
    float incidence();
}
