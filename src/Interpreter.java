import java.util.Scanner;

/**
 * 计算Parser解析之后的有效的数学表达式
 */
public class Interpreter extends NodeVisitor {
    private final String inputText;
    private final Parser mParser;

    public Interpreter(final String inputText) {
        this.inputText = inputText;
        mParser = new Parser(new Lexer(inputText));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                final Interpreter interpreter = new Interpreter(scanner.nextLine());
                System.out.println(interpreter.getInputText() + " = " + interpreter.evaluate().getValue());
            }
        }
    }

    public String getInputText() {
        return inputText;
    }

    public Token evaluate() {
        return visit(mParser.parse());
    }

    @Override
    public Token visitUnary(final UnaryOperator node) {
        return node;
    }

    @Override
    public Token visitBinary(final BinaryOperator node) {
        switch (node.getType()) {
            case PLUS:
                return new Token(TokenType.INTEGER,
                    visit(node.getLeft()).getValue() + visit(node.getRight()).getValue());
            case MINUS:
                return new Token(TokenType.INTEGER,
                    visit(node.getLeft()).getValue() - visit(node.getRight()).getValue());
            case TIMES:
                return new Token(TokenType.INTEGER,
                    visit(node.getLeft()).getValue() * visit(node.getRight()).getValue());
            case DIVIDE:
                return new Token(TokenType.INTEGER,
                    visit(node.getLeft()).getValue() / visit(node.getRight()).getValue());
            case POWER:
                return new Token(TokenType.INTEGER,
                    visit(node.getLeft()).getValue() ^ visit(node.getRight()).getValue());
            default:
                throw new AssertionError("不应该发生");
        }
    }
}
