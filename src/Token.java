public class Token {
    private int value;
    private TokenType type;

    public Token(final TokenType type, final int value) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    @Override

    public String toString() {
        return String.format("{%s : %s}", type, value);
    }

    public static void main(String[] args) {
        Token token = new Token(TokenType.TIMES, 3);
        System.out.println(token);
    }
}
