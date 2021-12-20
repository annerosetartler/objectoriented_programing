import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Field {
    //KOMMENTAR: Field repräsentiert ein Feld in einem Wald. Ein Feld ist entweder leer (= 'X'), hat etnweder einen Baum
    //           (= '*'), eine Borkenkäferpopulation (= '0') oder eine Ameisenbuntkäferpopulation (= '+').

    //INV:  forest != null; alle Zeilen von forest sind gleich lang; forest hat keine Null-Einträge
    //      xPos >= 0 & xPos <= forest[0].length-1
    //      yPos >= 0 & yPos <= forest.length-1
    //      beetle = ein BarkBeetle || beetle = ein AntBeetle || beetle = null
    //KOMMENTAR: jedes Feld besitzt ein Lock, damit Felder möglichst rasch gelockt werden können und gleichzeitig auf
    //           keine gelockten Felder zugegriffen werden kann
    private final Field[][] forest;
    private final int xPos;
    private final int yPos;
    private int treeVitality;
    private Beetle beetle;
    private Lock lock;

    //VORB: f != null; alle Zeilen von f sind gleich lang; f hat keine Null-Einträge
    //      x >= 0 & x <= f[0].length-1
    //      y >= 0 & y <= f.length-1
    //      c = 'X' || c = '*'
    public Field(Field[][] f, int x, int y, char c) {
        forest = f;
        xPos = x;
        yPos = y;
        treeVitality = (c == '*') ? 3 : 0;
        lock = new ReentrantLock();
    }

    //VORB: xPos >= 1 & xPos <= forest[0].length-2
    //      yPos >= 1 & yPos <= forest.length-2
    //NACHB: gibt eine Liste aller 8 Nachbarn eines Felds zurück
    public List<Field> getNeighbours() {
        List<Field> freeFields = new LinkedList<Field>();
        freeFields.add(getField(xPos - 1, yPos - 1));
        freeFields.add(getField(xPos - 1, yPos));
        freeFields.add(getField(xPos - 1, yPos + 1));
        freeFields.add(getField(xPos, yPos - 1));
        freeFields.add(getField(xPos, yPos + 1));
        freeFields.add(getField(xPos + 1, yPos - 1));
        freeFields.add(getField(xPos + 1, yPos));
        freeFields.add(getField(xPos + 1, yPos + 1));
        return freeFields;
    }

    //NACHB: setzt einen Beetle auf dieses Feld
    //       oder setzt beetle auf null
    public void setBeetle(Beetle b) {
        this.beetle = b;
    }

    //NACHB: gibt den Lock des Felds zurück
    public Lock getLock(){
        return lock;
    }

    //NACHB: gibt true aus solange treeVitality > 0 ist, das
    //       heißt so lange das Feld noch einen lebenden Baum besitzt
    public boolean hasTree(){
        return treeVitality > 0;
    }

    //NACHB: gibt den Beetle auf diesem Feld zurück oder Null, wenn das Feld von keinem Beetle besetzt wird
    public Beetle getBeetle(){
        return beetle;
    }

    //NACHB: verringert die treeVitality bei jedem Aufruf
    //       ist die treeVitality <= 0, wird der Thread des sich auf dem Feld befindenden Beetles abgebrochen
    public void damageTree() {
        treeVitality--;
        if (treeVitality <= 0) {
            beetle.endThread();
        }
    }

    //NACHB: gibt die x-Koordinate dieses Felds zurück
    public int getxPos() {
        return xPos;
    }

    //NACHB: gibt die y-Koordinate dieses Felds zurück
    public int getyPos() {
        return yPos;
    }

    //NACHB: gibt den Inhalt dieses Felds als String zurück,
    //       wenn es sich beim beetle um einen BarkBeetle handelt wird "0" zurückgegeben
    //       wenn es sich beim beetle um einen AntBeetle handelt wird "+" zurückgegeben
    //       ist beetle null, so wird bei einer treeVitality > 0 "*" zurückgegeben
    //       andernfalls wird ein Leerzeichen " " zurückgegeben
    public String toString() {
        if (beetle != null) {
            return beetle.getValueAsString();
        }
        if (treeVitality > 0) {
            return "*";
        }
        return " ";
    }

    //VORB: x >= 0 & x <= forest[0].length-1
    //      y >= 0 & y <= forest.length-1
    //NACHB: gibt das Feld an den Koordinaten x,y zurück
    private Field getField(int x, int y) {
        return forest[y][x];
    }

}
