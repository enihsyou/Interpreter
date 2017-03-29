import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum TokenType {
    INTEGER(0, "\\d+"),
    DOUBLE(0, "\\d+[.]\\d*"),
    PLUS(1, "+"),
    MINUS(1, "-"),
    TIMES(2, "[*]"),
    DIVIDE(2, "/"),
    POWER(3, "[^]"),
    LPAREN(9, "\\("),
    RPAREN(9, "\\)"),
    EOF(10, "$"),
    ASSIGN(1, "="),
    SEMICOLON(1, ";"),
    VARIABLE(1, "\\s[a-zA-Z0-9]*"),
    BEGIN(1, "BEGIN"),
    END(1, "END"),
    DOT(1, "[.]"),

    STATEMENT(0, "statement"),
    ASSIGNMENT(0, "assignment"),
    LIST(0, "statement_list"),
    COMPOUND(0, "compound_statement"),
    EMPTY(0, "empty_statement");


    public static final Set<TokenType> OPERATORS =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{PLUS, MINUS, TIMES, DIVIDE, POWER,})));
    public static final Set<TokenType> FIRST =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{PLUS, MINUS,})));
    public static final Set<TokenType> SECOND =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{TIMES, DIVIDE, POWER,})));
    public static final Set<TokenType> PARENTHESIS =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{LPAREN, RPAREN})));
    private final int precedence;
    private final String literal;

    TokenType(final int precedence, final String literal) {
        this.precedence = precedence;
        this.literal = literal;
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getLiteral() {
        return literal;
    }

}



