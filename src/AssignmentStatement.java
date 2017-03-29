public class AssignmentStatement extends BaseStatement {
    private VariableName variableName;
    private Token<?> expr;

    public AssignmentStatement(final VariableName variableName, final Token<?> expr) {
        super(TokenType.ASSIGNMENT);
        this.variableName = variableName;
        this.expr = expr;
    }

    public VariableName getVariableName() {
        return variableName;
    }

    public Token<?> getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return variableName + " = " + expr;
    }
}
