public class Test {
    public static void main(String[] args) {
        char[][] testArr = new char[][]{
                {'*','*','*','*','X'},
                {'X','*','*','*','*'},
                {'*','*','*','*','*'},
                {'*','*','*','*','*'},
                {'*','*','*','*','X'}
        };

        Forest f1 = new Forest(testArr);
        System.out.println(f1.printWald());
    }
}
