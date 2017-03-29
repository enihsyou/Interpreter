public class UnaryOperator extends BaseOperator {
    private final Token<?> body;

    public UnaryOperator(final Token<?> operator, final Token<?> token) {
        super(operator.getType());
        this.body = token;
    }

    public Token<?> getBody() {
        return body;
    }
}
