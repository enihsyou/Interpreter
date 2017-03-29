public abstract class Token<T> {
    private final TokenType type;
    private final T value;

    public Token(final TokenType type, final T value) {
        this.value = value;
        this.type = type;
    }

    protected Token(Token<T> token) {
        this.value = token.value;
        this.type = token.type;
    }

    public T getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("{%s : %s}", type, value);
    }
}
