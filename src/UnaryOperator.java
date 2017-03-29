public class UnaryOperator extends BaseOperator {
    private final Token object;

    public UnaryOperator(final TokenType operatorType, final Token token) {
        super(operatorType);
        object = token;
    }

    public Token getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "{" + getType() + " " + object + "}";
    }
}
