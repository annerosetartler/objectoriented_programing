import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        //kleiner Test zu Number, kann wieder gelöscht werden
        Number f = 2.5f;
        Number i = 2;
        i = i.intValue() + f.intValue();
        f = f.floatValue() + i.floatValue();
        System.out.println(i);
        System.out.println(f);
    }
}
