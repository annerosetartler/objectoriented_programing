import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        //KOMMENTAR: Tests zu Tree und Shade zur Überprüfung des mehrfachen Bindens
        Tree f1 = new Fagus(0.6, 0,0);
        Tree f2 = new Fagus(0.8, 0,0);
        Tree c1 = new CarpinusBetulus(0.7, 0,0);
        Tree c2 = new CarpinusBetulus(0.9, 0,0);
        Tree b1 = new Betula(3, 0,0);
        Tree b2 = new Betula(5, 0,0);
        Tree q1 = new Quercus(4, 0,0);
        Tree q2 = new Quercus(6, 0,0);

        Shade s1 = new BelowFagus();
        Shade s2 = new BelowNonFagus();
        Shade s3 = new OpenArea();

        System.out.println("Tests zu Tree und Shade:");
        System.out.println();

        System.out.println("Tests zu grow() in Tree:");
        System.out.println();
        System.out.println(f2.toString());
        System.out.println(c2.toString());
        System.out.println(b2.toString());
        System.out.println(q2.toString());
        System.out.println("Grow um 1.0:");
        Number growth = 1.0;
        f2.grow(growth);
        c2.grow(growth);
        b2.grow(growth);
        q2.grow(growth);
        System.out.println(f2.toString() +": " + testValues(1.8f,f2.getLeavesOrHeight(),false));
        System.out.println(c2.toString() +": " + testValues(1.9f,c2.getLeavesOrHeight(),false));
        System.out.println(b2.toString() +": " + testValues(6,b2.getLeavesOrHeight(),true));
        System.out.println(q2.toString() +": " + testValues(7,q2.getLeavesOrHeight(),true));
        System.out.println();

        System.out.println("Tests zu setShade() in Tree:");
        System.out.println();
        System.out.println(f1.toString());
        String resultf1 = f1.setShade().toString();
        System.out.println("Shade: " + resultf1 + ": " + testValues(s1.toString(),resultf1));
        System.out.println();
        System.out.println(c1.toString());
        String resultc1 = c1.setShade().toString();
        System.out.println("Shade: " + resultc1 + ": " + testValues(s2.toString(),resultc1));
        System.out.println();
        System.out.println(b1.toString());
        String resultb1 = b1.setShade().toString();
        System.out.println("Shade: " + resultb1 + ": " + testValues(s2.toString(),resultb1));
        System.out.println();
        System.out.println(q1.toString());
        String resultq1 = q1.setShade().toString();
        System.out.println("Shade: " + resultq1 + ": " + testValues(s2.toString(),resultq1));
        System.out.println();

        System.out.println("Tests zu isShadeCompatible() in Tree:");
        System.out.println();
        System.out.println(f1.toString());
        boolean f1s1 = f1.isShadeCompatible(s1);
        System.out.println("Fagus kompatibel mit " + s1.toString() + ": " + f1s1 + ": " + testValues(true,f1s1));
        boolean f1s2 = f1.isShadeCompatible(s2);
        System.out.println("Fagus kompatibel mit " + s2.toString() + ": " + f1s2 + ": " + testValues(true,f1s2));
        boolean f1s3 = f1.isShadeCompatible(s3);
        System.out.println("Fagus kompatibel mit " + s3.toString() + ": " + f1s3 + ": " + testValues(false,f1s3));
        System.out.println();

        System.out.println(c1.toString());
        boolean c1s1 = c1.isShadeCompatible(s1);
        System.out.println("CarpinusBetulus kompatibel mit " + s1.toString() + ": " + c1s1 + ": " + testValues(false,c1s1));
        boolean c1s2 = c1.isShadeCompatible(s2);
        System.out.println("CarpinusBetulus kompatibel mit " + s2.toString() + ": " + c1s2 + ": " + testValues(true,c1s2));
        boolean c1s3 = c1.isShadeCompatible(s3);
        System.out.println("CarpinusBetulus kompatibel mit " + s3.toString() + ": " + c1s3 + ": " + testValues(false,c1s3));
        System.out.println();

        System.out.println(b1.toString());
        boolean b1s1 = b1.isShadeCompatible(s1);
        System.out.println("Betula kompatibel mit " + s1.toString() + ": " + b1s1 + ": " + testValues(false,b1s1));
        boolean b1s2 = b1.isShadeCompatible(s2);
        System.out.println("Betula kompatibel mit " + s2.toString() + ": " + b1s2 + ": " + testValues(false,b1s2));
        boolean b1s3 = b1.isShadeCompatible(s3);
        System.out.println("Betula kompatibel mit " + s3.toString() + ": " + b1s3 + ": " + testValues(true,b1s3));
        System.out.println();

        System.out.println(q1.toString());
        boolean q1s1 = q1.isShadeCompatible(s1);
        System.out.println("Quercus kompatibel mit " + s1.toString() + ": " + q1s1 + ": " + testValues(false,q1s1));
        boolean q1s2 = q1.isShadeCompatible(s2);
        System.out.println("Quercus kompatibel mit " + s2.toString() + ": " + q1s2 + ": " + testValues(false,q1s2));
        boolean q1s3 = q1.isShadeCompatible(s3);
        System.out.println("Quercus kompatibel mit " + s3.toString() + ": " + q1s3 + ": " + testValues(true,q1s3));
        System.out.println();

        //TODO: refactor wegen Methodenname
        System.out.println("Tests zu eliminateThis() in Tree:");
        System.out.println();
        System.out.println(f1.toString());
        System.out.println("Überprüfung mit '" + f2.toString() + "' als Input");
        boolean f1f2 = f1.eliminateThis(f2);
        System.out.println("Erwartete Rückgabe: " + true + ": " + testValues(true,f1f2));
        System.out.println("Überprüfung mit '" + c1.toString() + "' als Input");
        boolean f1c1 = f1.eliminateThis(c1);
        System.out.println("Erwartete Rückgabe: " + true + ": " + testValues(true,f1c1));
        System.out.println("Überprüfung mit '" + b1.toString() + "' als Input");
        boolean f1b1 = f1.eliminateThis(b1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,f1b1));
        System.out.println("Überprüfung mit '" + q1.toString() + "' als Input");
        boolean f1q1 = f1.eliminateThis(q1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,f1q1));//TODO sollte Quercus auch gegenüber Fagus bevorzugt werden? Wenn ja, muss true zurückgegeben werden
        System.out.println();

        System.out.println(c2.toString());
        System.out.println("Überprüfung mit '" + f1.toString() + "' als Input");
        boolean c2f1 = c2.eliminateThis(f1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,c2f1));
        System.out.println("Überprüfung mit '" + c1.toString() + "' als Input");
        boolean c2c1 = c2.eliminateThis(c1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,c2c1));
        System.out.println("Überprüfung mit '" + b2.toString() + "' als Input");
        boolean c2b2 = c2.eliminateThis(b2);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,c2b2));
        System.out.println("Überprüfung mit '" + q1.toString() + "' als Input");
        boolean c2q1 = c2.eliminateThis(q1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,c2q1));
        System.out.println();

        System.out.println(b1.toString());
        System.out.println("Überprüfung mit '" + f1.toString() + "' als Input");
        boolean b1f1 = b1.eliminateThis(f1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,b1f1));
        System.out.println("Überprüfung mit '" + c1.toString() + "' als Input");
        boolean b1c1 = b1.eliminateThis(c1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,b1c1));//TODO sollte CarpinusBetulus auch gegenüber Betula bevorzugt werden? Wenn ja, muss true zurückgegeben werden
        System.out.println("Überprüfung mit '" + b2.toString() + "' als Input");
        boolean b1b2 = b1.eliminateThis(b2);
        System.out.println("Erwartete Rückgabe: " + true + ": " + testValues(true,b1b2));
        System.out.println("Überprüfung mit '" + q1.toString() + "' als Input");
        boolean b1q1 = b1.eliminateThis(q1);
        System.out.println("Erwartete Rückgabe: " + true + ": " + testValues(true,b1q1));
        System.out.println();

        System.out.println(q2.toString());
        System.out.println("Überprüfung mit '" + f2.toString() + "' als Input");
        boolean q2f1 = q2.eliminateThis(f2);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,q2f1));
        System.out.println("Überprüfung mit '" + c1.toString() + "' als Input");
        boolean q2c1 = q2.eliminateThis(c1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,q2c1));
        System.out.println("Überprüfung mit '" + b2.toString() + "' als Input");
        boolean q2b2 = q2.eliminateThis(b2);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,q2b2));
        System.out.println("Überprüfung mit '" + q1.toString() + "' als Input");
        boolean q2q1 = q2.eliminateThis(q1);
        System.out.println("Erwartete Rückgabe: " + false + ": " + testValues(false,q2q1));
        System.out.println();


    }

    private static String testValues(boolean expected, boolean received){
        return expected == received? "Test erfolgreich!" : "Test fehlgeschlagen!";
    }

    private static String testValues(String expected, String received){
        return expected.equals(received)? "Test erfolgreich!" : "Test fehlgeschlagen!";
    }

    private static String testValues(Number expected, Number received, boolean IsInt){
        if(IsInt){
            return expected.intValue() == received.intValue()? "Test erfolgreich!" : "Test fehlgeschlagen!";
        }else{
            return expected.floatValue() == received.floatValue()? "Test erfolgreich!" : "Test fehlgeschlagen!";
        }
    }
}
