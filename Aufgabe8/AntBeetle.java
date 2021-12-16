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
            boolean alreadyUsed = false;

            while (!aBeetle.isInterrupted() && !alreadyUsed) {
                nextField = getFreeField(stepsToStarvation < 2); //ToDo: noch überlegen, ob <= besser wäre
                if (nextField != null) {
                    synchronized (occupiedField) {
                        synchronized (nextField) {
                            if (Thread.currentThread().isInterrupted()) {
                                break;
                            }
                            gotFood = nextField.antBeetleMove();
                            occupiedField.setContent('*');
                            occupiedField = nextField; //ToDo: geht das so?
                            nextField = null; //ToDo: geht das so?
                            alreadyUsed = true;
                        }
                    }
                }

                long waitTime = (long) (Math.random() * 45 + 5);
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    break;
                }

            }

            if (alreadyUsed) { //Damit dieser atomare Teil nicht vorm anderen passiert ToDO: Bessere möglichkeit, vlt. steht was im Skriptum?

                boolean alreadyUsed2 = false; //Haha

                while (!aBeetle.isInterrupted() && !alreadyUsed2) {
                    if (reproductionCount * Math.random() > 2) {  //ToDo: noch überlegen, ob andere Zahl besser wäre
                        getFreeField(false);
                        if (childField != null) {
                            synchronized (childField) {
                                if (Thread.currentThread().isInterrupted()) {
                                    break;
                                }
                                childField.setContent('+');
                                AntBeetle child = new AntBeetle(simRef, childField.getxPos(), childField.getyPos());
                                simRef.addABeetleAndStart(child); //ToDo: geht das so?
                                childField = null;
                            }
                        }
                        reproductionCount = 0;
                        alreadyUsed2 = true;
                    }

                    long waitTime = (long) (Math.random() * 45 + 5);
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
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

    private Field getFreeField(boolean hungryBeetle) {
        List<Field> neighbours = occupiedField.getNeighbours();
        List<Field> firstSelection = new LinkedList<Field>();
        for (Field f : neighbours) {
            if (f.getContent() == '*' || f.getContent() == '0') {
                firstSelection.add(f);
            }
        }
        if (firstSelection.size() > 0) {
            if (hungryBeetle) {
                for (Field field : firstSelection) {
                    if (field.getContent() == '0') {
                        return field;
                    }
                }
            } else {
                return firstSelection.get(0);
            }
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
