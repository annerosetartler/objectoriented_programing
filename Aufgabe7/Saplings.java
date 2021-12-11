import java.util.ArrayList;
import java.util.List;

public class Saplings {
    //KOMMENTAR: Diese Klasse dient der Speicherung und Verwaltung von Jungbäumen vom deklarierten Typ Tree in einer Liste.
    //           Jungbäume besitzen zusätzlich zu den vom dynamischen Typ festgelegten Eigenschaften zusätzlich Koordinaten
    //           (x, y), die für die geographische Lage des jeweiligen Baumes stehen.
    //           An einem Koordinatenpaar können jeweils mehrere Bäume gleichzeitig stehen. Zur Verwaltung des Verhältnisses
    //           zwischen den Jungbäumen am selben Platz zueinander, werden zwei zweidimensionale int-Arrays verwendet.
    //           shades dient der Verwaltung der Beschattungsart, während nrOfSaps für jedes Koordinatenpaar die Anzahl an
    //           Jungbäumen beinhaltet.
    //INV: saplingList enthält keine Nulleinträge
    //     0 <= x < maxX & 0 <= y < maxY
    //     shades.length = maxX & shades[x].length = maxY
    //     nrOfSaps.length = maxX & nrOfSaps[x].length = maxY
    private List<Tree> saplingList;
    private Shade[][] shades;
    private int[][] nrOfSaps;
    private final int maxX, maxY;
    private final int maxSapAtCoord;
    private final int maxInsertAtOnce;

    //VORB: maxX > 0 & maxY > 0
    //      maxInsertAtOnce >= 0
    //      maxsapAtChoord >= 0
    public Saplings(int maxX, int maxY, int maxInsertAtOnce, int maxSapAtCoord) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxInsertAtOnce = maxInsertAtOnce;
        this.maxSapAtCoord = maxSapAtCoord;

        saplingList = new ArrayList<Tree>();
        nrOfSaps = new int[maxX][maxY];
        for (int i = 0; i < maxX ; i++) {
            for (int j = 0; j < maxY; j++) {
                nrOfSaps[i][j] = 0;
            }
        }

        shades = new Shade[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                shades[i][j] = generateRandomShade();
            }
        }
    }


    //NACHB: Fügt eine zufällige Zahl an Jungbäumen in saplingList ein.
    public void fill() {
        int amount = (int) (Math.random() * (maxInsertAtOnce + 1));
        for (int i = 0; i < amount; i++) {
            Tree t = generateRandomTree();
            saplingList.add(i, t);
            int[] position = t.getPosition();
            nrOfSaps[position[0]][position[1]] += 1;
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

    //NACHB: Gibt ein Objekt vom Typ Tree zurück
    //       Koordinaten in 0 <= x < maxX && 0 <= y < maxY
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

    //NACHB: Gibt ein Objekt vom deklarierten Typ Shade mit einem zufällig aus dessen Untertypen ausgewählten dynamischen Typen zurück
    private Shade generateRandomShade(){
        double rand = Math.random();

        if (rand < 0.33){
            return new BelowNonFagus();
        } else if (rand < 0.66){
            return new BelowFagus();
        } else {
            return new OpenArea();
        }
    }

    //VORB: max >= 0
    //NACHB: Gibt eine ganze Zahl zwischen 0 und max zurück
    private int randomCoordinate(int max) {
        return (int) (Math.random() * (max + 1));
    }

    //KOMMENTAR: Löscht zuerst alle die Bäume, die für die Beschattung ungeeignet sind. Erst in einem zweiten Schritt wird die
    //           danach noch überzählige Anzahl an Bäumen ermittelt und der Bestand ausgedünnt
    //NACHB: Löscht Bäume von einer Position, wenn diese unpassend sind. Bäume sind entweder unpassend, wenn sie für die
    //       an der Position existierende Beschattung ungeeignet sind, oder wenn es zu viele Bäume an derselben Position
    //       gibt und sie nicht zu den "auserwählten" gehören
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
    //NACHB: Löscht alle Trees aus removelist aus saplingList und verringert die in nrOfSaplings vermerkte Anzahl an der entsprechenden Position um eins
    private void delete(List<Tree> removelist) {
        for (Tree sap : removelist) {
            saplingList.remove(sap);
            int[] position = sap.getPosition();
            nrOfSaps[position[0]][position[1]] -= 1;
        }
    }


    //VORB:  0 <= xCoord < maxX & 0 <= yCoord < maxY
    //NACHB: gibt eine Liste aller der Bäume aus saplingList zurück, die sich an (xCoord, yCoord) befinden
    private List<Tree> sapsAtCoord(int xCoord, int yCoord) {
        int amountAtLoc = nrOfSaps[xCoord][yCoord];
        List<Tree> atPosition = new ArrayList<>();
        for (Tree sap : saplingList) {
            if (sap.hasSamePosition(xCoord, yCoord)) {
                atPosition.add(sap);
                amountAtLoc--;
            }
            if (amountAtLoc <= 0) {
                return atPosition;
            }
        }
        return atPosition;
    }

    //VORB:  possibleCandidates != null & elimAmount > 0
    //NACHB: gibt eine Liste zurück, die die "elimAmount" kleinsten/unbeschattetsten Trees beinhaltet
    private List<Tree> findDelCandidates(List<Tree> possibleCandidates, int elimAmount) {

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

    //VORB:  0 <= x < maxX & 0 <= y < maxY
    //NACHB: ermittelt den Besten Baum an Standort x,y und ändert die Beschattungsart dementsprechend. Der Baum wird aus
    //       der Jungbaumliste entfernt und auch nrOfSaps[x][y] wird um eins verringert
    public void establish(int x, int y) {
        if (nrOfSaps[x][y] == 0) {
            return;
        }
        Tree bestCandidate = evaluateBestTree(sapsAtCoord(x, y));
        shades[x][y] = bestCandidate.setShade();
        saplingList.remove(bestCandidate);
        nrOfSaps[x][y] -= 1;
    }

    //VORB:  possiblecandidates != null
    //NACHB: Gibt den am besten geeigneten Baum aus der Liste möglicher Kandidaten zurück
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

    //KOMMENTAR: Entfernt den Baum an Position x,y, wodurch sich die Beschattung ändert
    public void cut(int x, int y) {
        shades[x][y] = shades[x][y].cut();
    }

    //VORB:  0 <= x < maxX & 0 <= y < maxY
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
                    s += "*Bäume an Stelle (" + i + "/" + j + ") unter Beschattung " + shades[i][j].toString() + ": \n" + string;
                }
            }
        }
        System.out.println(s);
    }

    //VORB:  0 <= x < maxX & 0 <= y < maxY
    //NACHB: Gibt einen String zurück welcher alle Bäume an der Position x,y beinhaltet
    public String sapAtCoordinates(int x, int y) {
        String s = "";
        for (Tree sap : saplingList) {
            if (sap.hasSamePosition(x, y)) {
                s += "   -" + sap.toString() + '\n';
            }
        }
        return s;
    }

    //KOMMENTAR: Fürs Testen
    //NACHB: Gibt die Anzahl der Saps an einer bestimmten Position x,y zurück
    public int nrOfSapsAt(int x, int y){
        return nrOfSaps[x][y];
    }

    //KOMMENTAR: Fürs Testen
    public int getMaxSap(){
        return maxSapAtCoord;
    }

    //KOMMENTAR: Fürs Testen
    //NACHB: Gibt die Gesamtanzahl an Jungbäumen zurück
    public int NrOfSapsInList(){
        int i = 0;
        for (Tree sap :saplingList) {
            i++;
        }
        return i;
    }

    //KOMMENTAR: Fürs Testen:
    public int getSize(){
        return saplingList.size();
    }

    //KOMMENTAR: Fürs Testen
    //NACHB: Gibt den ersten Tree der Liste zurück
    public Tree getFirst() {
        return saplingList.get(0);
    }
}

