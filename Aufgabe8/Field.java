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
    //      content = ein Element aus der Menge: {'X','*','0','+'}
    //      barkBThread = null || barkBThread ist ein BarkBeetle-Thread
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
    //NACHB: gibt eine Liste aller 8 Nachbarn eines Felds zurück, die nicht leer sind
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

    //VORB: bBthread ist ein Thread von BarkBeetle || bBthread = null
    //NACHB: setzt einen BarkBeetle-Thread auf dieses Feld
    //       oder setzt barkBThread auf null
    public void setBeetle(Beetle b) {
        this.beetle = b;
    }

    public Lock getLock(){
        return lock;
    }

    public boolean hasTree(){
        return treeVitality > 0;
    }

    public Beetle getBeetle(){
        return beetle;
    }

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
    //       wenn content = 'X', dann wird ein Leerzeichen zurückgegeben
    public String toString() {
        if (beetle != null) {
            return beetle.getCharacter();
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
