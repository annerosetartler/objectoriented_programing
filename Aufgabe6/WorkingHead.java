public interface WorkingHead {
    //KOMMENTAR: Ein Arbeitskopf ist ein für einen Harvester benötigtes Bauteil, das jedoch gewechselt werden kann.
    //           Er beinhaltet eine Number (je nach Implementierung Integer oder Float), die für die maximale länge oder
    //           dicke eines Holzstückes steht
    //INVARIANTE: die jeweiligen Werte der Number sind nicht veränderlich

    //KOMMENTAR: Liest je nach Art des WorkingHead den limitierenden Faktor (die maximalen Stücklänge oder die maximalen Baumdicke) aus
    public Number readMax();

    //KOMMENTAR: stellt eine ausformulierte Erklärung für die Interpretation der Variable maxLength bereit
    //            wird für die Ausgabe verwendet
    public String meaning();

}
