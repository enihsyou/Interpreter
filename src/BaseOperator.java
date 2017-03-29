public abstract class BaseOperator extends Token<String> {

    public BaseOperator(final TokenType type) {
        super(type, type.toString());
    }
}

