import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BarkBeetle implements Beetle {
    //KOMMENTAR: BarkBeetle repräsentiert eine Borkenkäferpopulation. Diese knabbert über drei Generationen an einem
    //           Baum. Dann stirbt dieser, genauso wie die darauf fressende Borkenkäferpopulation, ab. Zu Lebzeiten der
    //           Population vermehrt sich diese. Dabei erzeugt sie in jeder Generation zwei weitere Generationen auf
    //           Nachbarsbäumen. Borkenkäfer stehen als Leibspeise von Ameisenbuntkäfern aber unter Gefahr gefressen
    //           werden zu können.

    //INV:  currentField != null
    //      generation >= 1
    //      thisSim != null
    //      barkBList != null
    private Field currentField;
    private int generation;
    private final Simulation thisSim;
    private List<Beetle> theBeetlesList;
    private boolean running;
    private Thread currentThread;

    //VORB: s != null & x >= 1 & y >= 1 & generation >= 1 & bB != null
    //HISTORY-CONSTRAINT: bei jedem Aufruf des Konstruktors wird die Anzahl aller Borkenkäfer in der Simulation erhöht
    public BarkBeetle(Simulation s, int x, int y, int generation, List<Beetle> bB) {
        theBeetlesList = bB;
        thisSim = s;
        currentField = s.getField(x, y);
        currentField.setBeetle(this);
        this.generation = generation;
        synchronized (thisSim) {//TODO: findest du es macht Sinn hier die Simulation zu locken? was anderes sinnvolles fällt mir nicht ein
            thisSim.changeNrOfThread(1);
        }
        currentThread = Thread.currentThread();
        running = false;
    }

    //NACHB: verwaltet den Lebenszyklus einer Borkenkäferpopulation, bis dieser terminiert, weil etnweder ein BarkBeetle
    //       die Generation 32 erreicht hat, der Baum von diesem BarkBeetle zu Tode gefressen wurde, dieser BarkBeetle von
    //       einem AntBeetle gefressen wurde oder das Programm aufgrund keiner lebenden Borkenkäferpopulationen beendet wurde
    //       innerhalb eines Lebenszyklus knabbert eine Borkenkäferpopulation an ihre Baum und vermehrt sich über den Aufruf
    //       von spawnChildren() auf zwei Nachbarsbäume, auf denen wiederum neue BarkBeetles gestartet werden
    @Override
    public void run() {
        running = true;
        while (beetleActive()){

            long waitTime = (long) (Math.random() * 45 + 5);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ignored) {
            }
                thisSim.print("Borkenkäfer haben gewartet: ");

            spawnChildren();

            try {
                if (currentField.getLock().tryLock(100, TimeUnit.MILLISECONDS)){
                    if (running) {
                        currentField.damageTree();
                    }
                    currentField.getLock().unlock();
                }
            } catch (InterruptedException ignored) {}

            generation++;

            synchronized (thisSim) {//TODO: findest du es macht Sinn hier die Simulation zu locken? was anderes sinnvolles fällt mir nicht ein
                if (thisSim.getNrOfBarkThreads() <= 0 || generation >= 32) {
                    thisSim.interruptGlobally();
                    thisSim.endAll();
                }
            }
        }
    }

    //NACHB: gibt true zurück, da Borkenkäfer von Ameisenbuntkäfern gerne gefressen werden
    public boolean isPrey(){
        return true;
    }

    //NACHB: gibt den für eine Borkenkäferpopulation charakteristischen String "0" zurück
    @Override
    public String getValueAsString() {
        return "0";
    }

    //NACHB: gibt true zurück, wenn keine Abbruchbedingung erfüllt wird
    //       folgende Abbruchbedingungen werden geprüft:
    //       - ob der Thread dieses BarkBeetle abgebrochen wurde
    //       - ob das Programm in der Simulation beendet wurde(= globale Interrupt)
    //       - ob dieser BarkBeetle-Thread noch läuft (mit running abgefragt)
    //       wenn eine der Abbruchbedingungen erfüllt wird, wird false zurückgegeben
    private boolean beetleActive(){
        if (Thread.currentThread().isInterrupted()){
            return false;
        }
        if (thisSim.getGlobalInterrupt()){
            return false;
        }
        if (!running){
            return false;
        }
        return true;
    }

    //NACHB: erzeugt zwei neue BarkBeetle-Threads auf zwei Nachbarsfeldern, wenn genau zwei solche freie (nicht gelockte)
    //       Nachbarsfelder mit Bäumen gefunden werden und gibt die Felder anschließend wieder frei (unlock)
    private void spawnChildren(){
        List<Field> childFields = getCFields();
        if (childFields.size() == 2){
            spawnChild(childFields.get(0));
            spawnChild(childFields.get(1));
        }
        for (Field f : childFields) {
            f.getLock().unlock();
        }
    }

    //VORB: field != null & field ist gelockt
    //NACHB: erzeugt ein neues BarkBeetle auf einem Feld, fügt dieses der Liste aller Borkenkäferpopulationen hinzu und
    //       startet den Thread des soeben erzeugten BarkBeetles
    private void spawnChild(Field field){
        BarkBeetle b = new BarkBeetle(thisSim, field.getxPos(), field.getyPos(), generation + 1, theBeetlesList);
        theBeetlesList.add(b);
        new Thread(b, "BarkBeetle").start();
    }

    //NACHB: gibt eine Liste mit Nachbarsfeldern zurück, auf denen ein Baum steht und
    //       die frei(nicht gelockt) sind
    //       werden zwei solcher Felder gefunden, dann wird früher abgebrochen und eine Liste mit diesen zwei zurückgegeben
    private List<Field> getCFields(){
        List<Field> neighbours = currentField.getNeighbours();
        List<Field> selection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.hasTree() && f.getBeetle() == null && f.getLock().tryLock()){
                selection.add(f);
            }
            if (selection.size() >= 2){
                return selection;
            }
        }
        return selection;
    }

    //NACHB: beendet diesen Thread(= currentThread), wenn dieser nicht schon beendet wurde
    //       beim ersten Aufruf wird auch der Beetle des currentFields auf null gesetzt
    //       & running auf false gesetzt
    //HISTORY-CONSTRAINT: verringert beim ersten Aufruf die Anzahl der Borkenkäferpopulationen in der Simulation um 1
    public void endThread() {
        if (!running){
            return;
        }
        running = false;
        currentThread.interrupt();
        synchronized (thisSim) {//TODO: findest du es macht Sinn hier die Simulation zu locken? was anderes sinnvolles fällt mir nicht ein
            thisSim.changeNrOfThread(-1);
        }

        if (currentField.getLock().tryLock()) {
            currentField.setBeetle(null);
            currentField.getLock().unlock();
        }
    }

    public String toString() {
        return "Borkenkäfer: Generation: " + generation + "; Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
