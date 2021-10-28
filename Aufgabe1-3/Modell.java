public interface Modell {
    //Input für plusOneYear: ein Array mit [Hitze,Mure,Sturm,Zuwachs] mit jeweils Werten zwischen [0.0,1.0]
    //vll ist das eine Objekteigenschaft von dem jeweiligen Modell?
    void plusOneYear(float afaktor, float zfaktor);

    String toString();
}
