public interface Beetle extends Runnable {

    void endThread();

    boolean isPrey();

    String getCharacter();
}
