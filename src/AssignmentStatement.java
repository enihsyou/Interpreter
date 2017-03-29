public class AssignmentStatement extends BaseStatement {
    private Variable variableName;
    private Token<?> expr;

    public AssignmentStatement(final Variable variableName, final Token<?> expr) {
        super(TokenType.ASSIGNMENT);
        this.variableName = variableName;
        this.expr = expr;
    }

    public Variable getVariableName() {
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
