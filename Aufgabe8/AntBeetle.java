import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AntBeetle implements Beetle {

    private Field currentField;
    private int stepsToStarvation;
    private Simulation thisSim;
    private List<Beetle> antBList;
    private Thread currentThread;
    private boolean running;


    public AntBeetle(Simulation s, int x, int y, List<Beetle> aB) {
        antBList = aB;
        thisSim = s;
        currentField = s.getField(x, y);
        currentField.setBeetle(this);
        stepsToStarvation = 3;
        currentThread = Thread.currentThread();
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while (beetleActive()) {
            long waitTime = (long) (Math.random() * 45 + 5);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ignored) {
            }

            tryToMove();
            spawnChildren();

            stepsToStarvation--;

            if (stepsToStarvation < 0){
                endThread();
            }
        }
    }

    private void tryToMove(){
        List<Field> neighbourFields = getMoveNeighbours();
        if (neighbourFields.size() == 1){
            attackMove(neighbourFields.get(0));
        }
        for (Field f : neighbourFields) {
            f.getLock().unlock();
        }
    }

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

    private void spawnChildren(){
        List<Field> childFields = getFreeNeighbours();
        if (childFields.size() == 1){
            spawnChild(childFields.get(0));
        }
        for (Field f : childFields) {
            f.getLock().unlock();
        }
    }

    private void spawnChild(Field field){
        AntBeetle a = new AntBeetle(thisSim, field.getxPos(), field.getyPos(), antBList);//toDO: theBeetlesLIST!
        antBList.add(a);
        new Thread(a, "AntBeetle").start();
    }

    private List<Field> getFreeNeighbours(){
        List<Field> neighbours = currentField.getNeighbours();
        Collections.shuffle(neighbours);
        List<Field> selection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.hasTree() && f.getBeetle() == null && f.getLock().tryLock()){ //ToDo: barkbeetles
                selection.add(f);
                break;
            }
        }
        return selection;
    }

    private List<Field> getMoveNeighbours(){
        List<Field> neighbours = currentField.getNeighbours();
        Collections.shuffle(neighbours);
        List<Field> selection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.hasTree() && (f.getBeetle() == null || f.getBeetle().isPrey()) && f.getLock().tryLock()){ //ToDo: barkbeetles
                selection.add(f);
                break;
            }
        }
        return selection;
    }

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

    public boolean isPrey(){
        return false;
    }

    @Override
    public String getCharacter() {
        return "+";
    }

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
        return "Ameisenbuntkäfer: Hungrigkeit: " + (stepsToStarvation == 3 ? "satt" : (stepsToStarvation == 2 ? "mäßig hungrig" : "sehr hungrig")) + "Feldkoordinaten: " + currentField.getxPos() + ", " + currentField.getyPos() + "\n";
    }
}
