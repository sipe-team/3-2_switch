import java.util.Scanner;

public abstract class BaseInterpreter<T> {
    public T startSequence() {
        System.out.println(createUxGuide());
        return parser(readUserInput());
    }

    protected abstract String createUxGuide();

    protected String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected abstract T parser(String input);
}