public class ControlToken extends Token<String> {
    protected ControlToken(final TokenType type) {
        super(type, type.toString());
    }
}
