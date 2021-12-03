import java.util.Iterator;

public class Forstbetrieb {
    //KOMMENTAR: Forstbetrieb verwaltet Informationen über einen Forstbetrieb und wertet diese aus.
    //           Ein Forstbetrieb hat einen unveränderlichen Namen.
    //INVARIANTE:
    private final String name;
    private List holzvollernter;


    //VORB: name != null
    public Forstbetrieb(String name){
        holzvollernter = new List();
        this.name = name;
    }

    //NACHB: fügt einen Holzvollernter h ans Ende der Liste hinzu, wenn dieser nicht in der Liste vorhanden ist
    public void add(Harvester h){
        if(h == null){
            return;
        }else{
            for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
                Harvester hn = (Harvester) it.next();
                if (h.equals(hn)){
                    return;
                }
                holzvollernter.add((Harvester)h);
            }
        }
    }

    //NACHB: entfernt einen Holzvollernter h aus der Liste, wenn dieser in der Liste vorhanden ist
    public void remove(Harvester h){
        if(h == null){
            return;
        }else{
            boolean keepSearching = true;
            for (Iterator it = holzvollernter.iterator(); it.hasNext() && keepSearching; ) {
                Harvester hn = (Harvester) it.next();
                if (h.equals(hn)){
                    it.remove();
                    keepSearching = false;
                }
            }
        }
    }

    //KOMMENTAR: alle Holzvollernter
    public float durschnittStundenalleHolzvollernter(){
        float summe = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            summe += hn.getOperationTime();
        }
        return summe;
    }

    //TODO
    //KOMMENTAR: alle Holzvollernter die Holz in stücke schneiden
    public float durschnittStundenalleStücke(){
        float summe = 0.0f;

        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            summe += hn.getOperationTime();
        }
        return summe;
    }

    //TODO
    //KOMMENTAR: alle Holzvollernter die Holz in Hack schneiden
    public float durschnittStundenalleHack(){
        float summe = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if ()
        }
        return summe;
    }

    //KOMMENTAR: alle Holzvollernter mit Rädern
    public float durschnittStundenalleRäder(){
        float summe = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof WheelHarvester) {
                summe += hn.getOperationTime();
            }
        }
        return summe;
    }

    //KOMMENTAR: alle Holzvollernter mit schreitbeinen
    public float durschnittStundenalleSchreitbeine(){
        float summe = 0.0f;
        for (Iterator it = holzvollernter.iterator(); it.hasNext(); ) {
            Harvester hn = (Harvester) it.next();
            if (hn instanceof StrideHarvester) {
                summe += hn.getOperationTime();
            }
        }
        return summe;
    }

}
