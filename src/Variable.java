public class Variable extends Token<String> {
    public Variable(final String variableName) {
        super(TokenType.VARIABLE, variableName);
    }
}
