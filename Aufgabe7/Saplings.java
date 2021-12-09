import java.util.LinkedList;
import java.util.List;

public class Saplings {
    //INV: 0 <= x <= maxX, 0 <= y <= maxY
    private List<Tree> saplingList;
    private Shade[][] shades;
    private int[][] nrOfSaps;
    private int maxX, maxY; //Koord zw. 0 und maxX bzw. maxY
    private int maxSapAtCoord = 6;
    private int maxInsertAtOnce;

    public Saplings(int maxX, int maxY, int maxInsertAtOnce){
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxInsertAtOnce = maxInsertAtOnce;

        saplingList = new LinkedList<Tree>(); //ToDo: oder lieber Array List?
        nrOfSaps = new int[maxX][maxY];
        for (int i = 0; i < maxX - 1; i++) {
            for (int j = 0; j < maxY - 1; j++) {
                nrOfSaps[i][j] = 0;
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
        int amount = (int) (Math.random() * (maxInsertAtOnce + 1));
        for (int i = 0; i < amount; i++) {
            Tree t = generateRandomTree();
            saplingList.add(i, generateRandomTree());
            updateNrOfSaps(t.getPosition());
        }
    }

    public void updateNrOfSaps(int[] position){
        nrOfSaps[position[0]][position[1]] += 1;
    }

    //KOMMENTAR: Lässt jeden Baum der Liste um einen zufälligen Wert wachsen
    public void grow() {
        for (Tree t : saplingList) {
            t.grow(Math.random()*10);
        }
    }

    private Tree generateRandomTree(){
        double randomNumber =  Math.random();

        if (randomNumber < 0.25) {
            return new Betula(Math.random()*10, randomCoordinate(maxX-1), randomCoordinate(maxY-1));
        }
        else if (randomNumber < 0.5) {
            return new CarpinusBetulus(Math.random()*10, randomCoordinate(maxX-1), randomCoordinate(maxY-1));
        }
        else if (randomNumber < 0.75) {
            return new Fagus(Math.random()*10, randomCoordinate(maxX-1), randomCoordinate(maxY-1));
        }
        else {
            return new Quercus(Math.random()*10, randomCoordinate(maxX-1), randomCoordinate(maxY-1));
        }
    }

    private int randomCoordinate(int max){
        return (int) (Math.random() * (max + 1));
    }

    public void thin(){
        for (Tree sap : saplingList) {
            int xCoord = sap.getPosition()[0];
            int yCoord = sap.getPosition()[1];
            if (!sap.isShadeCompatible(shades[xCoord][yCoord])){
                saplingList.remove(sap); //ToDo: Geht so wohl nihct -> anders speichern und löschen?
            }
        }

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (nrOfSaps[i][j] > maxSapAtCoord){
                    assesAndDelete(sapsAtCoord(i, j), nrOfSaps[i][j] - maxSapAtCoord);
                    nrOfSaps[i][j] = maxSapAtCoord; //Nicht mega elegant, dass das hier Außerhalb gemacht wird
                }
            }
        }
    }

    //Gibt eine Liste aller Saplings aus, die an einem bestimmten Ort (x,y) stehen
    private int[] sapsAtCoord(int xCoord, int yCoord){
        int amountAtLoc = nrOfSaps[xCoord][yCoord];
        int[] presentPositions = new int[amountAtLoc];
        int counter = 0;
        for (Tree sap : saplingList) {
            if (sap.getPosition()[0] == xCoord && sap.getPosition()[1] == yCoord){
                presentPositions[amountAtLoc - 1] = counter;
                amountAtLoc--;
            }
            counter++;
            if (amountAtLoc <= 0){
                return presentPositions;
            }
        }
        return presentPositions;
    }


    //ToDo: Soll das alles in einer Methode stehen?
    private void assesAndDelete(int[] possibleCandidatePositions, int elimAmount){

        int[] candidateWorseness = new int[possibleCandidatePositions.length];
        for (int i = 0; i < possibleCandidatePositions.length - 1; i++) {
            Tree t = saplingList.get(possibleCandidatePositions[i]);
            for (int j = 0; j < possibleCandidatePositions.length - 1; j++) {
                if (t.eliminateThis(saplingList.get(possibleCandidatePositions[j]))){  //Checken: hierfür muss die Methode so geschrieben sein, dass bei zwei Bäumen gleichen Wertes t BESSER ist! Also die Methode hier false bleibt
                    candidateWorseness[i]++;
                }
            }
        }

        int[] deletionCandidates = new int[elimAmount];
        int toPluck = elimAmount;
        while (toPluck > 0) {
            int indexOfMax = 0;
            for (int i = 0; i < candidateWorseness.length - 1; i++) {
                if (candidateWorseness[i] > candidateWorseness[indexOfMax]) {
                    indexOfMax = i;
                }
            }
            deletionCandidates[toPluck - 1] = possibleCandidatePositions[indexOfMax]; //von hinten aufgefüllt...
            possibleCandidatePositions[indexOfMax] = -1;
            toPluck--;
        }

        //deleteCand nach größe (größter index unbedingt zuerst)
        //finde index mit größtem Inhalt (größtem Index)
        toPluck = elimAmount;
        while (toPluck > 0) {
            int indexOfMax = 0;
            for (int i = 0; i < deletionCandidates.length; i++) {
                if (deletionCandidates[i] > deletionCandidates[indexOfMax]){
                    indexOfMax = i;
                }
            }
            saplingList.remove(deletionCandidates[indexOfMax]);
            deletionCandidates[indexOfMax] = -1;
            toPluck--;
        }
    }

    //
    public void establish(int x, int y){
        if (nrOfSaps[x][y] == 0){
            return;
        }
        Tree bestCandidate = evaluateBestTree(sapsAtCoord(x, y));
        shades[x][y] = bestCandidate.setShade();
        saplingList.remove(bestCandidate);
    }

    private Tree evaluateBestTree(int[] possibleCandidates){
        Tree t = saplingList.get(possibleCandidates[0]);
        for (int i = 0; i < possibleCandidates.length - 1; i++) {
            boolean b = true;
            t = saplingList.get(possibleCandidates[i]);
            for (int j = 0; j < possibleCandidates.length - 1; j++) {
                if (t.eliminateThis(saplingList.get(possibleCandidates[j]))){  //ToDo: checken: hierfür muss die Methode so geschrieben sein, dass bei zwei Bäumen gleichen Wertes t BESSER ist! Also die Methode hier false bleibt
                    b = false;
                }
            }
            if (b){
                return t;
            }
        }

        return t;
    }

    public void cut(int x, int y){ //ToDo: Stimmt noch nicht
        shades[x][y] = shades[x][y].cut();
    }

    //KOMMENTAR: Liefert die Beschattungsart am Standort x, y
    public Shade get(int x, int y){
        return shades[x][y];
    }


    //ToDo: vlt. noch verschönern, sind grad drei Schleifen:
    // Außerdem stimmts sicher noch nicht 100%
    public void print(){
        String s = "";
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                String string = sapAtCoordinates(i, j);
                if (!string.equals("")){
                    s += "Bäume an Stelle (" + i + "/" + j + "): \n" + string + "Beschattung: " + shades[i][j].toString() + '\n';
                }
            }
        }
        System.out.println(s);
    }

    //ToDo: String muss sicher noch adaptiert werden
    private String sapAtCoordinates(int x, int y){
        String s = "";
        for (Tree sap : saplingList) {
            if (sap.getPosition()[0] == x && sap.getPosition()[1] == y){
                s += sap.toString() + '\n';
            }
        }
        return s;
    }

}
