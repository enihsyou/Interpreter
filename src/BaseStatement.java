public abstract class BaseStatement extends Token<String> {
    public BaseStatement(final TokenType type) {
        super(type, type.toString());
    }
}
