public class Forest {
    //KOMMENTAR: Forest repräsentiert einen Wald bestehend aus Feldern.

    //INV:  forest != null; alle Zeilen von forest sind gleich lang
    //      forest hat keine Null-Einträge
    //      leere Grenzfelder in forest bleiben leer
    private final Field[][] forest;

    //VORB: f != null; alle Zeilen von f sind gleich lang; f hat keine Null-Einträge
    //      Einträge in f sind entweder 'X' oder '*'
    //NACHB: erzeugt einen forest, der in jede Dimension um 2 größer ist als f
    //       und für jeden Wert in f ein Feld in forest anlegt, sowie leere Grenzfelder erzeugt
    //KOMMENTAR: die leeren Grenzfelder dienen zur Erleichterung späterer Abfragen von Nachbarfeldern
    public Forest(char[][] f){
        forest = new Field[f.length + 2][f[0].length + 2];
        FillBorder();
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[0].length; j++) {
                int y = i+1;
                int x = j+1;
                forest[y][x] = new Field(forest,x,y,f[i][j]);
            }
        }
    }

    //NACHB: Befüllt alle Grenzfelder von forest mit leeren Feldern
    private void FillBorder(){
        int lastY = forest.length-1;
        int lastX = forest[0].length-1;
        for (int i = 0; i < forest[0].length; i++) {
            forest[0][i] = new Field(forest,i,0,'X');
            forest[lastY][i] = new Field(forest,i,lastY,'X');
        }
        for (int i = 1; i < forest.length-1; i++) {
            forest[i][0] = new Field(forest,0,i,'X');
            forest[i][lastX] = new Field(forest,0,i,'X');
        }
    }

    //VORB: x >= 0 & x <= forest[0].length-1
    //      y >= 0 & y <= forest.length-1
    //NACHB: gibt das Feld an den Koordinaten x,y zurück
    public Field getField(int x, int y){
        return forest[y][x];
    }

    //NACHB: Gibt den Zustand des Walds auf der Konsole aus
    //       ruft dafür die synchronisierte Methode in Field auf;
    //       wenn es sich vom Stack her nicht ausgeht, wird
    //       die nicht synchronisierte Methode printWald() aufgerufen --> kann inkonsistent sein
    public void print(String message){
        System.out.println(message);
        System.out.println(printWald());
        System.out.println();
    }

    //NACHB: gibt einen String zurück, der den Zustand des Walds ausgibt
    //       dabei wird der Inhalt jedes Felds zurückgegeben
    //       könnte bei laufenden Threads zu Inkonsistenzen führen
    public String printWald(){
        String s = "";
        for (int i = 1; i < forest.length-1; i++) {
            for (int j = 1; j < forest[0].length - 1; j++) {
                s += forest[i][j].toString();
            }
            s += "\n";
        }
        return s;
    }
}
