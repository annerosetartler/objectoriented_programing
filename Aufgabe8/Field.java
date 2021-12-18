import java.util.LinkedList;
import java.util.List;

public class Field {
    //KOMMENTAR: Field repräsentiert ein Feld in einem Wald. Ein Feld ist entweder leer (= 'X'), hat etnweder einen Baum
    //           (= '*'), eine Borkenkäferpopulation (= '0') oder eine Ameisenbuntkäferpopulation (= '+').

    //INV:  forest != null; alle Zeilen von forest sind gleich lang; forest hat keine Null-Einträge
    //      xPos >= 0 & xPos <= forest[0].length-1
    //      yPos >= 0 & yPos <= forest.length-1
    //      content = ein Element aus der Menge: {'X','*','0','+'}
    //      barkBThread = null || barkBThread ist ein BarkBeetle-Thread
    private Field[][] forest;
    private char content;
    private int xPos;
    private int yPos;
    private Thread barkBThread;

    //VORB: f != null; alle Zeilen von f sind gleich lang; f hat keine Null-Einträge
    //      x >= 0 & x <= f[0].length-1
    //      y >= 0 & y <= f.length-1
    //      c = 'X' || c = '*'
    public Field(Field[][] f, int x, int y, char c){
        forest = f;
        xPos = x;
        yPos = y;
        content = c;
    }

    //VORB: xPos >= 1 & xPos <= forest[0].length-2
    //      yPos >= 1 & yPos <= forest.length-2
    //NACHB: gibt eine Liste aller 8 Nachbarn eines Felds zurück, die nicht leer sind
    public List<Field> getNeighbours(){
        List<Field> freeFields = new LinkedList<Field>();
        if(getField(xPos-1,yPos-1).content != 'X'){
            freeFields.add(getField(xPos-1,yPos-1));
        }
        if(getField(xPos-1,yPos).content != 'X'){
            freeFields.add(getField(xPos-1,yPos));
        }
        if(getField(xPos-1,yPos+1).content != 'X'){
            freeFields.add(getField(xPos-1,yPos+1));
        }
        if(getField(xPos,yPos-1).content != 'X'){
            freeFields.add(getField(xPos,yPos-1));
        }
        if(getField(xPos,yPos+1).content != 'X'){
            freeFields.add(getField(xPos,yPos+1));
        }
        if(getField(xPos+1,yPos-1).content != 'X'){
            freeFields.add(getField(xPos+1,yPos-1));
        }
        if(getField(xPos+1,yPos).content != 'X'){
            freeFields.add(getField(xPos+1,yPos));
        }
        if(getField(xPos+1,yPos+1).content != 'X'){
            freeFields.add(getField(xPos+1,yPos+1));
        }
        return freeFields;
    }

    //VORB: bBthread ist ein Thread von BarkBeetle || bBthread = null
    //NACHB: setzt einen BarkBeetle-Thread auf dieses Feld
    //       oder setzt barkBThread auf null
    public void setBarkBThread(Thread bBthread){
        this.barkBThread = bBthread;
    }

    //NACHB: simuliert, wenn sich eine Ameisenbuntkäferpopulation auf dieses Feld bewegt
    //       setzt den Inhalt dieses Felds auf '+' und unterbricht den Borkenkäfer-Thread,
    //       falls sich auf diesem Feld ein derartiger Thread befindet (= Ameisenbuntkäferpopulation
    //       frisst Borkenkäferpopulation)
    //       Gibt true aus, wenn sich eine Borkenkäferpopulation auf dem Feld befunden hat
    public boolean antBeetleMove(){
        content = '+';
        if(barkBThread != null){
            barkBThread.interrupt();
            barkBThread = null;
            BarkBeetle.countThreads--;
            return true;
        }
        return false;
    }

    //VORB: c = 'X' || c = '*' || c = '+' || c = '0'
    //NACHB: setzt den Inhalt dieses Felds auf c
    public void setContent(char c){
        content = c;
    }

    //NACHB: gibt den Inhalt des Felds zurück
    public char getContent(){
        return content;
    }

    //NACHB: gibt die x-Koordinate dieses Felds zurück
    public int getxPos(){
        return xPos;
    }

    //NACHB: gibt die y-Koordinate dieses Felds zurück
    public int getyPos(){
        return yPos;
    }

    //NACHB: gibt den Inhalt dieses Felds als String zurück,
    //       wenn content = 'X', dann wird ein Leerzeichen zurückgegeben
    public String toString(){
        if(content == 'X'){
            return " ";
        }else{
            return "" + content;
        }
    }

    //VORB: xPos >= 1 & yPos >= 1
    //NACHB: gibt den Wald (ohne Grenzfelder) beginnend ab diesem Feld als String zurück
    public synchronized String print() {
        if (xPos != forest[0].length - 2) {
            return this + getField(xPos + 1, yPos).print();
        } else {
            if (yPos != forest.length - 2) {
                return this + "\n" + getField(1, yPos + 1).print();
            } else {
                return "" + this;
            }
        }
    }

    //VORB: x >= 0 & x <= forest[0].length-1
    //      y >= 0 & y <= forest.length-1
    //NACHB: gibt das Feld an den Koordinaten x,y zurück
    private Field getField(int x, int y){
        return forest[y][x];
    }

}
