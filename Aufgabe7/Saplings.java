import java.util.LinkedList;
import java.util.List;

public class Saplings {
    //INV: 0 <= x <= maxX, 0 <= y <= maxY
    private List<Tree> saplingList;
    private Shade[][] shades;
    private int[][] nrOfSups;
    private int maxX, maxY; //Koord zw. 0 und maxX bzw. maxY
    private int maxSapAtCoord = 10;
    private int maxInsert = 100;

    public Saplings(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
        saplingList = new LinkedList<Tree>(); //Evtl. eigene Listenklasse, die add ohne Index nimmt und nach x sortiert, sodass die Methoden mit x, y etwas effiienter sein können
        nrOfSups = new int[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                nrOfSups[i][j] = 0;
            }
        }

        shades = new Shade[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                shades[i][j] = new OpenArea();
            }
        }
    }

    //KOMMENTAR: Fügt eine zufällige Zahl an Bäumen ein
    public void fill(){
        int amount = (int) Math.random() * (maxInsert + 1);
        for (int i = 0; i < amount; i++) {
            Tree t = generateRandomTree();
            saplingList.add(0, generateRandomTree());
            updateNrOfSups(t.getPosition());
        }
    }

    public void updateNrOfSups(int[] position){
        nrOfSups[position[0]][position[1]] += 1;
    }

    //KOMMENTAR: Lässt jeden Baum der Liste um einen zufälligen Wert wachsen
    public void grow() {
        for (Tree t : saplingList) {
            t.grow(Math.random());
        }
    }

    //ToDo: vlt. noch verschönern:
    private Tree generateRandomTree(){
        Tree tree;
        int randomNumber = (int) Math.random();
        if (randomNumber < 0.25) {
            tree = new Betula(Math.random(), randomCoordinate(maxX), randomCoordinate(maxY));
        }
        else if (randomNumber < 0.5) {
            tree = new CarpinusBetulus(Math.random(), randomCoordinate(maxX), randomCoordinate(maxY));
        }
        else if (randomNumber < 0.75) {
            tree = new Fagus(Math.random(), randomCoordinate(maxX), randomCoordinate(maxY));
        }
        else {
            tree = new Quercus(Math.random(), randomCoordinate(maxX), randomCoordinate(maxY));
        }
        return tree;
    }

    private int randomCoordinate(int max){
        return (int) (Math.random() * (max + 1));
    }

    public void thin(){
        for (Tree sap : saplingList) {
            int xCoord = sap.getPosition()[0];
            int yCoord = sap.getPosition()[1];
            if (!sap.isShadeCompatible(shades[xCoord][yCoord])){
                saplingList.remove(sap);
            }
        }

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (nrOfSups[i][j] > maxSapAtCoord){
                    assesAndDelete(supsAtCoord(i, j), nrOfSups[i][j] - maxSapAtCoord);
                    nrOfSups[i][j] = maxSapAtCoord; //ToDo: nicht so elegant, dass das "außerhalb" gemacht wird
                }
            }
        }
    }

    //Gibt eine Liste aller Saplings aus, die an einem bestimmten Ort (x,y) stehen
    private int[] supsAtCoord(int xCoord, int yCoord){
        int amountAtLoc = nrOfSups[xCoord][yCoord];
        int[] presentPositions = new int[amountAtLoc];
        int counter = 0;
        for (Tree sap : saplingList) {
            if (sap.getPosition()[0] == xCoord && sap.getPosition()[1] == yCoord){
                presentPositions[amountAtLoc] = counter;
                amountAtLoc--;
            }
            counter++;
            if (amountAtLoc <= 0){
                return presentPositions;
            }
        }
        return presentPositions;
    }

    //ToDo: RF des Löschens RELEVANT!!!
    private void assesAndDelete(int[] presentPositions, int elimAmount){
        /*
        Objekte von CarpinusBetulus oder Quercus werden nur entfernt, wenn es
        an den gleichen Koordinaten keine Objekte von Fagus oder
        Betula (mehr) gibt.
        Von Bäumen gleicher Art bleiben jene mit größerer Blattanzahl bzw. Wuchshöhe
        bevorzugt erhalten.
         */
    }

    //
    public void establish(int x, int y){
        establishBest(supsAtCoord(x, y));
    }

    //Nimmt an, dass es nicht zu viele gibt?
    private void establishBest(int[] possibleCandidates){
        /*
        Der am besten geeignete Jungbaum
an den Koordinaten x,y in der Liste der Jungbäume etabliert sich.
Dieser Baum wird aus der Liste der Jungbäume entfernt und die Art
der Beschattung an diesen Koordinaten ändert sich auf BelowFagus
oder BelowNonFagus, abhängig von der Art des Baums. Die Auswahl
des am besten geeigneten Baums verwendet die gleichen Kriterien
wie thin: geeignete Art der Beschattung, Hainbuche oder
Eiche bevorzugt gegenüber Birke oder Buche, unter diesen Bäumen
wird jener mit der größten Blattanzahl oder Wuchshöhe gewählt.
         */
    }

    public void cut(int x, int y){
        shades[x][y].cut();
    }

    //KOMMENTAR: Liefert die Beschattungsart am Standort x, y
    public Shade get(int x, int y){
        return (Shade) shades[x][y];
    }


    //ToDo: vlt. noch verschönern, sind grad drei Schleifen:
    // Außerdem stimmts sicher ncoh nicht 100%
    public void print(){
        String s = "";
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                String string = sapAtCoordinates(i, j);
                if (!string.equals("")){
                    s += "Bäume an Stelle (" + i + "/" + j + "): " + string + "Beschattung: " + shades[i][j].toString() + '\n';
                }
            }
        }
        System.out.println(s);
    }

    //ToDo: String "putzen"
    private String sapAtCoordinates(int x, int y){
        int[] array = {x, y};
        String s = "";
        for (Tree sap : saplingList) {
            if (sap.getPosition().equals(array)){
                s += sap.toString() + ", \n";
            }
        }
        return s;
    }

}
