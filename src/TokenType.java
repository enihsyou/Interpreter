import java.util.HashSet;

public enum TokenType {
    INTEGER, PLUS, MINUS, TIMES, DIVIDE, EOF;

    public static final HashSet<TokenType> OPERATORS;
    static {
        OPERATORS = new HashSet<>();
        OPERATORS.add(PLUS);
        OPERATORS.add(MINUS);
        OPERATORS.add(TIMES);
        OPERATORS.add(DIVIDE);
    }
}



