import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AntBeetle implements Beetle {
    //KOMMENTAR: AntBeetles sind Beetles, die andere Beetles fressen. Als solche können sie sich selbst von einem Baum zum
    //           nächsten Bewegen. Treffen sie auf Beute, so vertilgen sie diese. Im Gegensatz zu anderen Käfern beruht
    //           ihre Populationsstrategie demnach nur zum Teil auf Fortpflanzung und sie pflanzen sich pro Jahr nur mit
    //           einmal fort (eine Population pro Ausführung der while-Schleife in der Methode run). Hat ein Ameisenbuntkäfer zu
    //           lange nichts gefressen (stepsToStarvation < 0), so stirbt er ab
    //           Ameisenbuntkäfer leben immer auf einem Feld mit Baum. Sie laufen von einem Baum zum nächsten und pflanzen sich
    //           so lange fort, bis ihr currentThread interrupted wird (wird zur Vermeidung von synchronisierungsproblemen
    //           auch mit dem boolean running gesichert). Anschließend haben sie keine Funktionalität mehr, das AntBeetle-Objekt dient
    //           nur mehr der Ausgabe der Informationen bei Beendigung der Simulation

    //INV: currentField != null && currentField.hasTree()
    //     3 >= stepsToStarvation >= 0
    //     thisSim != null && antBList != null & currentThread != null
    private Field currentField;
    private int stepsToStarvation;
    private Simulation thisSim;
    private List<Beetle> theBeetlesList;
    private Thread currentThread;
    private boolean running;


    public AntBeetle(Simulation s, int x, int y, List<Beetle> aB) {
        theBeetlesList = aB;
        thisSim = s;
        currentField = s.getField(x, y);
        currentField.setBeetle(this);
        stepsToStarvation = 3;
        currentThread = Thread.currentThread();
        running = false;
    }

    //NACHB: Verwaltet den Lebenszyklus eines AntBeetle so lange, bis dieser terminiert, weil er entweder verhungert ist,
    //       oder das Programm beendet wurde
    //       Bei jedem Durchlauf der while-Schleife bewegt sich der AntBeetle ein Feld weiter, wenn ihm das möglich ist
    //       (= es gibt einen Platz mit Baum und ohne AntBeetle im Umkreis)
    //       Trifft der AntBeetle auf dem neuen Platz auf ein BarkBeetle, so frisst er dieses (= Beendigung des
    //       BarkBeetle-Threads auf dem entsprechenden Platz)
    //       Zusätzlich vermehrt er sich jeweils einmal (= Erzeugung eines neuen AntBeetle-Objekts)
    @Override
    public void run() {
        running = true;
        while (beetleActive()) {
            long waitTime = (long) (Math.random() * 45 + 5);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ignored) {
            }

            if(beetleActive()){
                tryToMove();
                spawnChildren();
            }

            stepsToStarvation--;
            if (stepsToStarvation < 0){
                endThread();
            }
        }
    }

    //NACHB: Versetzt dieses Objekt auf ein anderes Feld, falls ein passendes (= mit Baum, aber ohne AntBeetle) verfügbar ist
    //       currentField wird aufs entsprechende neue Feld gesetzt
    //       Falls sich auf dem Feld ein BarkBeetle befand, so wird dieser gefressen (= entsprechender Thread beendet)
    private void tryToMove(){
        List<Field> neighbourFields = getFittingNeighbours(true);
        if (neighbourFields.size() == 1){
            attackMove(neighbourFields.get(0));
        }
        for (Field f : neighbourFields) {
            f.getLock().unlock();
        }
    }

    //VORB: field != null && field.hasTree() && (field.getBeetle == null || field.getBeetle().isPrey())
    //NACHB: Setzt diesen AntBeetle-Thread vom Feld, auf dem es gerade sitzt auf field und setzt auch die currentField Referenz um
    //       Befand sich auf dem neuen Feld ein BarkBeetle, so wird dieser gefressen
    private void attackMove(Field field){
        if (field.getBeetle() != null){
            field.getBeetle().endThread();
            stepsToStarvation = 3;
        }
        currentField.getLock().lock();
        field.setBeetle(this);
        currentField.setBeetle(null);
        currentField.getLock().unlock();
        currentField = field;
    }

    //NACHB: Setzt ein neues AntBeetle-Objekt auf einen geeigneten (= freien) Platz, falls ein solcher vorhanden ist
    private void spawnChildren(){
        List<Field> childFields = getFittingNeighbours(false);
        if (childFields.size() == 1){
            spawnChild(childFields.get(0));
        }
        for (Field f : childFields) {
            f.getLock().unlock();
        }
    }

    //VORB: field != null && field.hasTree() && field.getBeetle == null
    //NACHB: Setzt einen neuen AntBeetle-Thread auf field, fügt ihn in die antBList ein und startet seinen Thread
    private void spawnChild(Field field){
        AntBeetle a = new AntBeetle(thisSim, field.getxPos(), field.getyPos(), theBeetlesList);
        theBeetlesList.add(a);
        new Thread(a, "AntBeetle").start();
    }

    //VORB: canEat ist false, wenn die Methode fürs Erzeugen eines neuen Threads (= spawnChildren) aufgerufen wird, true sonst
    //NACHB: Gibt eine List mit entweder keinem Nachbarfeld, oder genau einem Nachbarfeld, das einen Baum beinhaltet, aus.
    //       Wenn can eat true ist, darf sich auf dem gewählten Nachbarfeld ein BarkBeetle befinden, ansonsten muss der Baum
    //       unbesiedelt sein
    private List<Field> getFittingNeighbours(boolean canEat){
        List<Field> neighbours = currentField.getNeighbours();
        Collections.shuffle(neighbours);
        List<Field> selection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.hasTree() && (f.getBeetle() == null || (canEat && f.getBeetle().isPrey())) && f.getLock().tryLock()){
                selection.add(f);
                break;
            }
        }
        return selection;
    }

    //NACHB: gibt true aus, wenn der in diesem Objekt beinhaltete Thread weiter laufen soll, false sonst
    //       false kann entweder durch "eigene" Objektzustände erreicht werden (wenn der Thread beendet wurde oder gerade
    //       in Beendigung ist, wenn der Käfer verhungert), aber auch durch einen globalen Interrupt
    private boolean beetleActive(){
        if (Thread.currentThread().isInterrupted()){
            return false;
        }
        if (stepsToStarvation < 0){
            return false;
        }
        if (!running){
            return false;
        }
        if (thisSim.getGlobalInterrupt()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isPrey(){
        return false;
    }

    //NACHB: Gibt das für unsere Simulation gewählte charakteristische Zeichen des Ameisenbuntkäfers "+" aus
    @Override
    public String getValueAsString() {
        return "+";
    }

    //NACHB: Beendet currentThread, wenn dieser nicht gerade schon beendet wird
    //       Der Beetle-Thread auf currentField wird in Zuge dessen auf "null" zurückgesetzt
    public void endThread() {
        if (!running){
            return;
        }
        running = false;
        currentThread.interrupt();
        if (currentField.getLock().tryLock()) {
            currentField.setBeetle(null);
            currentField.getLock().unlock();
        }
    }

    public String toString() {
        return "Ameisenbuntkäfer: Hungrigkeit: " + (stepsToStarvation == 3 ? "satt. " : (stepsToStarvation == 2 ? "mäßig hungrig. " : "sehr hungrig. ")) + "Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
