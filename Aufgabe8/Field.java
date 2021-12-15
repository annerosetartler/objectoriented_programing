import java.util.LinkedList;
import java.util.List;

public class Field {
    private Field[][] forest;
    private char content;//'X','*','0','+'
    private int xPos;
    private int yPos;
    private Thread barkBThread;

    public Field(Field[][] f, int x, int y, char c){
        forest = f;
        xPos = x;
        yPos = y;
        content = c;
    }

    public List<Field> getNeighbours(){
        List<Field> freeFields = new LinkedList<Field>();
        if(getField(xPos-1,yPos).content != 'X'){
            freeFields.add(getField(xPos-1,yPos));
        }
        if(getField(xPos-1,yPos-1).content != 'X'){
            freeFields.add(getField(xPos-1,yPos-1));
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
        if(getField(xPos+1,yPos).content != 'X'){
            freeFields.add(getField(xPos+1,yPos));
        }
        if(getField(xPos+1,yPos-1).content != 'X'){
            freeFields.add(getField(xPos+1,yPos-1));
        }
        if(getField(xPos+1,yPos+1).content != 'X'){
            freeFields.add(getField(xPos+1,yPos+1));
        }
        return freeFields;
    }

    public void setBarkBThread(Thread bBthread){
        this.barkBThread = bBthread;
    }

    public void antBeetleMove(){
        content = '+';
        if(barkBThread != null){
            barkBThread.interrupt();
            barkBThread = null;
        }
    }

    public void setContent(char c){
        content = c;
    }

    public char getContent(){
        return content;
    }

    public String toString(){
        if(content == 'X'){
            return " ";
        }else{
            return "" + content;
        }
    }

    private Field getField(int x, int y){
        return forest[y][x];
    }

}
