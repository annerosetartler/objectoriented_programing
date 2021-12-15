public class Forest {
    private Field[][] forest;

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

    public Field getField(int x, int y){
        return forest[y][x];
    }

    //KOMMENTAR: ruft die synchronisierte Methode in Field auf
    //           wenn es sich vom Stack her nicht ausgeht, wird
    //           die nicht synchronisierte Methode printWald() aufgerufen --> kann inkonsistent sein
    public void print(){
        try {
            System.out.println(forest[1][1].print());
        } catch (StackOverflowError e) {
            System.out.println(printWald());
        }
        System.out.println();
    }

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
