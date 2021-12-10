import java.util.ArrayList;
import java.util.List;

public class Saplings {
    //INV: 0 <= x <= maxX, 0 <= y <= maxY
    private List<Tree> saplingList;
    private Shade[][] shades;
    private int[][] nrOfSaps;
    private int maxX, maxY; //Koord zw. 0 und maxX bzw. maxY
    private int maxSapAtCoord;
    private int maxInsertAtOnce;

    public Saplings(int maxX, int maxY, int maxInsertAtOnce, int maxSapAtCoord){
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxInsertAtOnce = maxInsertAtOnce;
        this.maxSapAtCoord = maxSapAtCoord;

        saplingList = new ArrayList<Tree>();
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
            saplingList.add(i, t);
            updateNrOfSaps(t.getPosition(), true);
        }
    }

    private void updateNrOfSaps(int[] position, boolean isPositive){
        if (isPositive) nrOfSaps[position[0]][position[1]] += 1;
        else nrOfSaps[position[0]][position[1]] -= 1;
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
        //speichert eine Liste aller Indices in der saplingsList, die gelöscht werden müssen.
        List<Tree> delete = new ArrayList<Tree>();
        for (Tree sap : saplingList) {
            int xCoord = sap.getPosition()[0];
            int yCoord = sap.getPosition()[1];
            if (!sap.isShadeCompatible(shades[xCoord][yCoord])){
                delete.add(sap);
            }
        }

        delete(delete);

        List<Tree> delete2 = new ArrayList<Tree>();
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (nrOfSaps[i][j] > maxSapAtCoord){
                    delete2.addAll(findDelCandidates(sapsAtCoord(i, j), nrOfSaps[i][j] - maxSapAtCoord));
                    nrOfSaps[i][j] = maxSapAtCoord; //Nicht mega elegant, dass das hier Außerhalb gemacht wird
                }
            }
        }

        delete(delete2);
    }

    private void delete (List<Tree> removelist){
        for (Tree sap : removelist){
            updateNrOfSaps(sap.getPosition(), false);
            saplingList.remove(sap);
        }
    }


    //Gibt eine Liste aller Saplings aus, die an einem bestimmten Ort (x,y) stehen
    private List<Tree> sapsAtCoord(int xCoord, int yCoord){
        int amountAtLoc = nrOfSaps[xCoord][yCoord];
        List<Tree> atPosition = new ArrayList<>();
        for (Tree sap : saplingList) {
            if (sap.getPosition()[0] == xCoord && sap.getPosition()[1] == yCoord){
                atPosition.add(sap);
                amountAtLoc--;
            }
            if (amountAtLoc <= 0){
                return atPosition;
            }
        }
        return atPosition;
    }

    private List<Tree> findDelCandidates(List<Tree> possibleCandidates, int elimAmount){

        //Vergleiche die Trees und bewerte, wie oft sie "schlechter" sind
        int[] candidateWorseness = new int[possibleCandidates.size()];
        int counter = 0;
        for (Tree assessTree : possibleCandidates) {
            for (Tree compTrees : possibleCandidates) {
                if (assessTree.isLessSuitableThan(compTrees)){
                    candidateWorseness[counter]++;
                }
            }
            counter++;
        }

        //Suche die schlechtesten Raus und speichere sie in einer Liste
        List<Tree> deletionCandidates = new ArrayList<>();
        int pluckNumber = elimAmount;
        while (pluckNumber > 0){
            int indexOfMax = 0;
            for (int i = 0; i < candidateWorseness.length; i++) {
                if (candidateWorseness[i] > candidateWorseness[indexOfMax]) {
                    indexOfMax = i;
                }
            }
            deletionCandidates.add(possibleCandidates.get(indexOfMax));
            candidateWorseness[indexOfMax] = -1;
            pluckNumber--;
        }

        return deletionCandidates;
    }


    public void establish(int x, int y){
        if (nrOfSaps[x][y] == 0){
            return;
        }
        Tree bestCandidate = evaluateBestTree(sapsAtCoord(x, y));
        shades[x][y] = bestCandidate.setShade();
        saplingList.remove(bestCandidate);
    }

    private Tree evaluateBestTree(List<Tree> possibleCandidates){
        Tree t = generateRandomTree(); //ToDo: passt noch nicht, aber sonst hab ich keine
        for (Tree thisTree : possibleCandidates) {
            boolean b = true;
            t = thisTree;
            for (Tree theOtherTree :possibleCandidates) {
                if (thisTree.isLessSuitableThan(theOtherTree)){
                    b = false;
                }
            }
            if (b){
                return thisTree;
            }
        }
        return t;
    }



    public void cut(int x, int y){
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

    public List<Tree> getSaplingList() {
        return saplingList;
    }

}
