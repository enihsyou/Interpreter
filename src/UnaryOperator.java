public class UnaryOperator extends Token {
    private final Token token;

    public UnaryOperator(final Token token) {
        super(token);
        this.token = token;
    }
}
