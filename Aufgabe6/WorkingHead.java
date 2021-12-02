public interface WorkingHead<A> {
    //Kommentar: Ein Arbeitskopf ist ein für einen Harvester benötigtes Bauteil, das jedoch gewechselt werden kann

    //KOMMENTAR: Liest je nach Art des WorkingHead den limitierenden Faktor (die maximalen Stücklänge oder die maximalen Baumdicke) aus
    public A read();



}
