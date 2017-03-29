import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * 计算Parser解析之后的有效的数学表达式
 */
public class Interpreter implements NodeVisitor {
    private final String inputText;
    private final Parser mParser;

    public Interpreter(@NotNull final String inputText) {
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

    public Token<?> evaluate() {
        return visit(mParser.parse());
    }

    @Override
    public Token<?> visitUnary(final UnaryOperator node) {
        switch (node.getType()) {
            case PLUS:
                return visit(node.getBody());
            // case MINUS:
            //     return new IntegerToken(TokenType.INTEGER, visit(node.getBody()).getValue());
            default:
                throw new AssertionError("node = [" + node + "]" + "不应该发生");
        }
    }

    @Override
    public Token<?> visitBinary(final BinaryOperator node) {
        switch (node.getType()) {
            // case PLUS:
            //     return new Token(TokenType.INTEGER, visit(node.getLeft()).getValue() + visit(node.getRight()).getValue());
            // case MINUS:
            //     return new Token(TokenType.INTEGER, visit(node.getLeft()).getValue() - visit(node.getRight()).getValue());
            // case TIMES:
            //     return new Token(TokenType.INTEGER, visit(node.getLeft()).getValue() * visit(node.getRight()).getValue());
            // case DIVIDE:
            //     return new Token(TokenType.INTEGER, visit(node.getLeft()).getValue() / visit(node.getRight()).getValue());
            // case POWER:
            //     return new Token(TokenType.INTEGER, BigInteger.valueOf(visit(node.getLeft()).getValue())
            //         .pow(visit(node.getRight()).getValue())
            //         .intValue());
            default:
                throw new AssertionError("node = [" + node + "]" + "不应该发生");
        }
    }
}



