import java.util.ArrayList;
import java.util.List;

public class Saplings {
    //KOMMENTAR: In Saplings werde junbäume verwaltet. Diese sind in einer Liste gespeichert.
    //INV:       saplingList enthält keine Nulleinträge
    //           maxX >= 0 & maxY >= 0
    //           0 <= x <= maxX
    //           shades.length = maxX & shades[x].length = maxY
    //           nrOfSaps.length = maxX & nrOfSaps[x].length = maxY
    //           maxSapAtCoord >= 0
    //           maxInsertAtCoord >= 0
    private List<Tree> saplingList;
    private Shade[][] shades;
    private int[][] nrOfSaps;
    private int maxX, maxY;
    private int maxSapAtCoord;
    private int maxInsertAtOnce;

    //VORB: maxX >= 0 & maxY >= 0
    //      maxInsertAtOnce >= 0
    //      maxsapAtChoord >= 0
    public Saplings(int maxX, int maxY, int maxInsertAtOnce, int maxSapAtCoord) {
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
                double rand = Math.random();
                if (rand < 0.33){
                    shades[i][j] = new BelowNonFagus();
                }else if (rand < 0.66){
                    shades[i][j] = new BelowFagus();
                }else{
                    shades[i][j] = new OpenArea();
                }
            }
        }
    }


    //NACHB: Fügt eine zufällige Zahl an Bäumen in saplingList ein.
    public void fill() {
        int amount = (int) (Math.random() * (maxInsertAtOnce + 1));
        for (int i = 0; i < amount; i++) {
            Tree t = generateRandomTree();
            saplingList.add(i, t);
            updateNrOfSaps(t.getPosition(), true);
        }
    }

    //VORB:  position != null
    //NACHB: wenn isPositive true ist, wird nrOfSaps inkrementiert
    //       wenn isPositive false ist wird nrOfSaps dekrementiert
    private void updateNrOfSaps(int[] position, boolean isPositive) {
        nrOfSaps[position[0]][position[1]] += (isPositive ? 1 : -1);
    }

    //NACHB: Lässt jeden Baum der Liste um einen zufälligen Wert wachsen
    public void grow() {
        for (Tree t : saplingList) {
            t.grow(Math.random() * 10);
        }
    }

    //NACHB: Bei randomNumber < 0.25 wird Betula zurückgegeben
    //       sonst wird bei randomNumber < 0.5 wird Betula zurückgegeben
    //       sonst wird bei randomNumber < 0.75 wird Fagus zurückgegeben
    //       sonst wird Quercus zurückgegeben
    private Tree generateRandomTree() {
        double randomNumber = Math.random();

        if (randomNumber < 0.25) {
            return new Betula(Math.random() * 10, randomCoordinate(maxX - 1), randomCoordinate(maxY - 1));
        } else if (randomNumber < 0.5) {
            return new CarpinusBetulus(Math.random() * 10, randomCoordinate(maxX - 1), randomCoordinate(maxY - 1));
        } else if (randomNumber < 0.75) {
            return new Fagus(Math.random() * 10, randomCoordinate(maxX - 1), randomCoordinate(maxY - 1));
        } else {
            return new Quercus(Math.random() * 10, randomCoordinate(maxX - 1), randomCoordinate(maxY - 1));
        }
    }

    //VORB: max >= 0
    private int randomCoordinate(int max) {
        return (int) (Math.random() * (max + 1));
    }

    //NACHB: Löscht einen Junbaum aus saplingList wenn dieser unpassend für die Beschattungsart ist
    //       Löscht Bäume von einer Position bis nrOfSaps = maxSapAtCord
    public void thin() {
        List<Tree> deleteList1 = new ArrayList<Tree>();
        for (Tree sap : saplingList) {
            int xCoord = sap.getPosition()[0];
            int yCoord = sap.getPosition()[1];
            if (!sap.isShadeCompatible(shades[xCoord][yCoord])) {
                deleteList1.add(sap);
            }
        }

        delete(deleteList1);

        List<Tree> deleteList2 = new ArrayList<Tree>();
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (nrOfSaps[i][j] > maxSapAtCoord) {
                    deleteList2.addAll(findDelCandidates(sapsAtCoord(i, j), nrOfSaps[i][j] - maxSapAtCoord));
                }
            }
        }

        delete(deleteList2);
    }

    //VORB:  removelist != null
    //NACHB: Löscht alle Trees aus removelist aus saplingList und updatet nrOfSaps nach jeder Löschung
    private void delete(List<Tree> removelist) {
        for (Tree sap : removelist) {
            updateNrOfSaps(sap.getPosition(), false);
            saplingList.remove(sap);
        }
    }


    //VORB:  xCoord >= 0 & yCoord >= 0
    //NACHB: Wenn sap.getPosition()[0] == xCoord & sap.getPosition()[1] == yCoord wird der Baum zu einer Liste hinzugefügt
    //       sonst passiert nichts
    //       Wenn alle Bäume gefunden oder alle Bäume aus saplingList gecheckt wurden wird die Liste zurückgegeben
    private List<Tree> sapsAtCoord(int xCoord, int yCoord) {
        int amountAtLoc = nrOfSaps[xCoord][yCoord];
        List<Tree> atPosition = new ArrayList<>();
        for (Tree sap : saplingList) {
            if (sap.getPosition()[0] == xCoord && sap.getPosition()[1] == yCoord) {
                atPosition.add(sap);
                amountAtLoc--;
            }
            if (amountAtLoc <= 0) {
                return atPosition;
            }
        }
        return atPosition;
    }

    //VORB:  possibleCandidates != null & elimAmount >= 0
    //NACHB: deleetionCandidates.size = elimAmount
    //       gibt eine Liste zurück mit den unpassensten Bäumen für die Beschattung
    private List<Tree> findDelCandidates(List<Tree> possibleCandidates, int elimAmount) {

        //KOMMENTAR: Vergleiche die Trees und bewerte, wie oft sie "schlechter" sind
        int[] candidateWorseness = new int[possibleCandidates.size()];
        int counter = 0;
        for (Tree assessTree : possibleCandidates) {
            for (Tree compTrees : possibleCandidates) {
                if (assessTree.isLessSuitableThan(compTrees)) {
                    candidateWorseness[counter]++;
                }
            }
            counter++;
        }

        //KOMMENTAR: Suche die schlechtesten Raus und speichere sie in einer Liste
        List<Tree> deletionCandidates = new ArrayList<>();
        int pluckNumber = elimAmount;
        while (pluckNumber > 0) {
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

    //VORB:  0 <= x <= maxX & 0 <= y <= maxY
    //NACHB: wenn nrOfSaps[x][y] = 0 passiert nichts
    //       sonst wird der beste Baum am Standort x,y gesucht und aus der Liste entfernt
    //       wodurch nrofSaps geupdatet wird
    //       und die Beschattungsart abhängig von diesem Baum verändert
    public void establish(int x, int y) {
        if (nrOfSaps[x][y] == 0) {
            return;
        }
        Tree bestCandidate = evaluateBestTree(sapsAtCoord(x, y));
        shades[x][y] = bestCandidate.setShade();
        saplingList.remove(bestCandidate);
        int[] array = {x, y};
        updateNrOfSaps(array, false);
    }

    //VORB:  possiblecandidates != null
    //NACHB: Geht alle Bäume aus possiblecandidates durch und vergleicht sie mit der Methode isLessSuitable
    //       Gibt den am besten geeigneten Baum aus der Liste zurück
    private Tree evaluateBestTree(List<Tree> possibleCandidates) {
        Tree t = possibleCandidates.get(0);
        for (Tree thisTree : possibleCandidates) {
            boolean b = true;
            t = thisTree;
            for (Tree theOtherTree : possibleCandidates) {
                if (thisTree.isLessSuitableThan(theOtherTree)) {
                    b = false;
                }
            }
            if (b) {
                return thisTree;
            }
        }
        return t;
    }

    public void cut(int x, int y) {
        shades[x][y] = shades[x][y].cut();
    }

    //VORB:  0 <= x <= maxX & 0 <= y <= maxY
    //NACHB: gibt die Beschattungsart am Standort x, y zurück
    public Shade get(int x, int y) {
        return shades[x][y];
    }


    //NACHB: Gibt den Inhalt von saplingList nach Koordinaten aus
    public void print() {
        String s = "";
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                String string = sapAtCoordinates(i, j);
                if (!string.equals("")) {
                    s += "Bäume an Stelle (" + i + "/" + j + "): \n" + string + "Beschattung: " + shades[i][j].toString() + '\n';
                }
            }
        }
        System.out.println(s);
    }

    //VORB:  0 <= x <= maxX & 0 <= y <= maxY
    //NACHB: Gibt einen String zurück welcher alle Bäume an der Position x,y beinhaltet
    public String sapAtCoordinates(int x, int y) {
        String s = "";
        for (Tree sap : saplingList) {
            if (sap.getPosition()[0] == x && sap.getPosition()[1] == y) {
                s += sap.toString() + '\n';
            }
        }
        return s;
    }

    //ToDo Zusicherungen
    public int nrOfSapsAt(int x, int y){
        return nrOfSaps[x][y];
    }

    public int getMaxSap(){
        return maxSapAtCoord;
    }


    public int NrOfSapsInList(){
        int i = 0;
        for (Tree sap :saplingList) {
            i++;
        }
        return i;
    }

}

