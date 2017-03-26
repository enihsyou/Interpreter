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
        return mParser.operator();
    }

    public static void main(String[] args) {
        System.out.println(new Interpreter("1 * 3 + 2 -3").Evaluate());
    }
}
