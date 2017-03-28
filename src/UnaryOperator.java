public class UnaryOperator extends Token {
    private final Token body;

    public UnaryOperator(final Token operator, final Token token) {
        super(operator);
        this.body = token;
    }

    public Token getBody() {
        return body;
    }
}
