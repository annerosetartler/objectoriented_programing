public class AntBeetle implements Runnable{

    //TODO: noch Baustelle...
    private Thread aBeetle;
    private Field currentField;
    @Override
    public void run() {

    }

    public void endThread(){
        if(!aBeetle.isInterrupted()){
            aBeetle.interrupt();
        }
    }
}
