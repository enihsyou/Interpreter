public class Token {
    private final int value;
    private final TokenType type;

    public Token(final TokenType type, final int value) {
        this.value = value;
        this.type = type;
    }

    protected Token(Token token) {
        this.value = token.value;
        this.type = token.type;
    }

    public int getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    public Token negate() {return new Token(type, -value);}

    @Override
    public String toString() {
        return String.format("{%s : %s}", type, value);
    }
}
