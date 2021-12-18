import java.util.LinkedList;
import java.util.List;

public class AntBeetle implements Runnable {

    private Field occupiedField;
    private int stepsToStarvation;
    private int reproductionCount;
    private Simulation simRef;

    private Field nextField;
    private Field childField;
    private Thread aBeetle;


    public AntBeetle(Simulation s, int x, int y) {
        simRef = s;
        occupiedField = s.getField(x, y);
        reproductionCount = 0;
        stepsToStarvation = 3;
        setContent();
    }


    @Override
    public void run() {
        aBeetle = Thread.currentThread();

        while (!aBeetle.isInterrupted() && stepsToStarvation > 0) {
            boolean gotFood = false;

            nextField = getFreeField(stepsToStarvation <= 2); //Evtl. <=, wenn es nicht genug "jagd auf die Bark beetles macht"

            if (nextField != null) {
                synchronized (occupiedField) {
                    synchronized (nextField) { //Was passiert eigentlich, wenn es das nicht gibt?
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        gotFood = nextField.antBeetleMove();
                        occupiedField.setContent('*');
                        occupiedField = nextField;
                        nextField = null;

                        if (reproductionCount * Math.random() >= 2) {  //Evtl andere Zahl, wenn es sich nicht oft genug vermehrt
                            childField = getFreeField(false); //Hier müsste jetzt ja eh auch das ehemals occupied field gehen, denn das ist ja bereits frei und ich bin innerhalb des synch?
                            synchronized (childField) {
                                if (Thread.currentThread().isInterrupted()) {
                                    break;
                                }
                                if (childField != null) { //ToDo: müsste ich eigentlich nicht fragen, denn es ist immer zumindest das alte Eltern-Feld frei gewesen! Soll ichs weglassen?
                                    childField.setContent('+');
                                    AntBeetle child = new AntBeetle(simRef, childField.getxPos(), childField.getyPos());
                                    simRef.addABeetleAndStart(child);
                                    childField = null;
                                }
                            }
                            reproductionCount = 0;
                        }

                    }
                }
            }

            long waitTime = (long) (Math.random() * 45 + 5);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                return;
            }


            if (gotFood) {
                stepsToStarvation = 3;
            } else stepsToStarvation--;

            reproductionCount++;
        }

        if (stepsToStarvation <= 0) {
            occupiedField.setContent('*');
            this.endThread(); //aBeetle.interrupt() wird ja nicht richtig sein vermute ich
        }
    }

    private void setContent() {
        occupiedField.setContent('+');
    }

    private synchronized Field getFreeField(boolean hungryBeetle) { //ToDo: Hab grad beim Debugging anschauen gemerkt, dass ja hier die ergebnisse nicht mehr stimmen, wenn wir es verwenden (muss man wohl auch "sperren") - mein Bug hat sich grad auf ein X gesetzt...
        List<Field> neighbours = occupiedField.getNeighbours();
        List<Field> firstSelection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.getContent() == '*' || f.getContent() == '0') {
                firstSelection.add(f);
            }
        }
        if (firstSelection.size() > 0) {
            //if (hungryBeetle) {
                for (Field field : firstSelection) {
                    if (field.getContent() == '0') {
                        return field;
                    }
                }
            //} else {
                return firstSelection.get(0);
            //}
        }
        return null;
    }

    public void endThread() {
        if (!aBeetle.isInterrupted()) {
            aBeetle.interrupt();
        }
    }

    public String toString() {
        return "Ameisenbuntkäfer: Hungrigkeit: " + (stepsToStarvation == 3 ? "satt" : (stepsToStarvation == 2 ? "mäßig hungrig" : "sehr hungrig")) + "Feldkoordinaten: " + occupiedField.getxPos() + ", " + occupiedField.getyPos() + "\n";
    }
}
