import java.util.Scanner;

/**
 * 计算Parser解析之后的有效的数学表达式
 */
public class Interpreter {
    private String inputText;
    private Parser mParser;

    public Interpreter(final String inputText) {
        this.inputText = inputText;
        mParser = new Parser(new Lexer(inputText));
    }
    public int Evaluate(){
        return mParser.expr();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) System.out.println(new Interpreter(scanner.nextLine()).Evaluate());
    }
}
