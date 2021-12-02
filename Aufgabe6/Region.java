import java.util.Iterator;

public class Region {
    //KOMMENTAR: Eine Region hat einen unveränderlichen Namen und verwaltet Informationen über alle Forstbetriebe einer Region
    //           in Form einer Liste.
    //INV: forstbetriebe enthält keine null-Einträge
    private final String name;
    private List forstbetriebe;

    //VORB: n != null
    public Region(String n){
        forstbetriebe = new List();
        name = n;
    }

    //NACHB: fügt einen Forstbetrieb f ans Ende der Liste hinzu, wenn dieser nicht in der Liste vorhanden ist
    public void add(Forstbetrieb f){
        if(f == null){
            return;
        }else{
            for (Iterator it = forstbetriebe.iterator(); it.hasNext(); ) {
                Forstbetrieb fb = (Forstbetrieb) it.next();
                if (f.equals(fb)){
                    return;
                }
                forstbetriebe.add((Forstbetrieb)f);
            }
        }
    }

    //NACHB: entfernt einen Forstbetrieb f aus der Liste, wenn dieser in der Liste vorhanden ist
    public void remove(Forstbetrieb f){
        if(f == null){
            return;
        }else{
            boolean keepSearching = true;
            for (Iterator it = forstbetriebe.iterator(); it.hasNext() && keepSearching; ) {
                Forstbetrieb fb = (Forstbetrieb) it.next();
                if (f.equals(fb)){
                    it.remove();
                    keepSearching = false;
                }
            }
        }
    }

    //NACHB: gibt den Inhalt von forstbetriebe als String zurück
    public String toString(){
        if(forstbetriebe.getSize() == 0){
            return "{ }";
        }
        String s = "{ ";
        Iterator it = forstbetriebe.iterator();
        Forstbetrieb fb = (Forstbetrieb) it.next();
        s+= fb.toString();
        while (it.hasNext()) {
            fb = (Forstbetrieb) it.next();
            s += ", " + fb.toString();
        }
        s += " }";
        return s;
    }

}
