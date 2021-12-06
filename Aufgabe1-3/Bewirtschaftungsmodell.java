public interface Bewirtschaftungsmodell {
    //KOMMENTAR: Bewirtschaftungsmodell ist ein Interface, welches verschiedene Modelle implementiert
    //           Funktionsweise der Faktoren im Array Wirtschaftsfaktoren:
    //           wirtschaftsfaktoren[0] ist 1 wenn durch einen Kahlschlag geerntet werden soll ansonsten ist er 0
    //           wirtschaftsfaktoren[1] 1 wenn Plenterwirtschaft betrieben wird sonst 0
    //           wirtschaftsfaktoren[2] ist ein Faktor in Prozent der bestimmt ab wann im Mischwald begonnen werden muss Baumarten zu fällen
    //           also bei Faktor 0,4 würde man sobald eine Baumart weniger als 40% der Festmeter im Wald ausmacht beginnen
    //           die andere Baumart zu fällen sodass nach der Fällung wieder 50% Laub- sowie Nadelbäume im Wald sind
    //           wirtschaftsfaktoren[3] ist ein Prozentwert der aussagt wie viel Prozent Festmeter gefällt werden müssen.
    //           Dieser Wert ergibt sich aus Pflegung alter Wege, Anschaffung neuer, Pflegung alter Hütten und Bauung neuer
    //INV:  wirtschaftsfaktoren.length == 4 & Werte in wirtschaftsfaktoren in [0.0,1.0]

    //NACHB: gibt ein Array mit Faktoren zurück
    float [] plusEinJahr();

}