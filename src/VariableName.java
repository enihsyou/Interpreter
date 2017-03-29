public class VariableName extends Token<String> {
    // private Number value;
    public VariableName(final String variableName) {
        super(TokenType.VARIABLE, variableName);
    }
}
