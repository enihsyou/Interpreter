import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://ruslanspivak.com/lsbasi-part9/
 * 计算Parser解析之后的有效的数学表达式
 */
public class Interpreter implements NodeVisitor {
    private final String inputText;
    private final Parser mParser;

    private Map<String, Token> GLOBAL_SCOPE = new HashMap<>();

    public Interpreter(@NotNull final String inputText) {
        this.inputText = inputText;
        mParser = new Parser(new Lexer(inputText));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                final Interpreter interpreter =
                    new Interpreter("BEGIN a = 11; b = a + 9 - 5 * 2 END"/*scanner.nextLine()*/);
                System.out.println(interpreter.getInputText() + " = " + interpreter.evaluate().getValue());
                System.out.println(interpreter.GLOBAL_SCOPE);
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
        switch (node.getType()) {
            case PLUS:
                return visit(node.getObject());
            case MINUS:
                return new IntegerToken(((IntegerToken) visit(node.getObject())).getValue().intValue());
            default:
                throw new AssertionError("node = [" + node + "]" + "不应该发生");
        }
    }

    @Override
    public Token visitBinary(final BinaryOperator node) {
        switch (node.getType()) {
            case PLUS:
                return new IntegerToken(
                    ((IntegerToken) visit(node.getLeft())).getValue().intValue() + ((IntegerToken) visit(
                        node.getRight())).getValue().intValue());
            case MINUS:
                return new IntegerToken(
                    ((IntegerToken) visit(node.getLeft())).getValue().intValue() - ((IntegerToken) visit(
                        node.getRight())).getValue().intValue());
            case TIMES:
                return new IntegerToken(
                    ((IntegerToken) visit(node.getLeft())).getValue().intValue() * ((IntegerToken) visit(
                        node.getRight())).getValue().intValue());
            case DIVIDE:
                return new IntegerToken(
                    ((IntegerToken) visit(node.getLeft())).getValue().intValue() / ((IntegerToken) visit(
                        node.getRight())).getValue().intValue());
            // case POWER:
            //     return new IntegerToken(BigInteger.valueOf(visit(node.getLeft()).getValue())
            //         .pow(visit(node.getRight()).getValue())
            //         .intValue());
            default:
                throw new AssertionError("node = [" + node + "]" + "不应该发生");
        }
    }

    @Override
    public void visitCompoundStatement(final CompoundStatement node) {
        for (BaseStatement statement : node.getChildren().getChildren()) {
            visit(statement);
        }
    }

    @Override
    public void visitAssignmentStatement(final AssignmentStatement node) {
        VariableName variableName = node.getVariableName();
        GLOBAL_SCOPE.put(variableName.getValue(), node.getExpr());
    }

    @Override
    public Token visitVariable(final VariableName node) {
        final String variableName = node.getValue();
        return (Token<?>) GLOBAL_SCOPE.get(variableName);
    }

    @Override
    public void visitEmptyStatement(final EmptyStatement node) { }
}



