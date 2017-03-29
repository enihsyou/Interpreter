public class CompoundStatement extends BaseStatement {
    private final StatementList children;

    public CompoundStatement(final StatementList children) {
        super(TokenType.COMPOUND);
        this.children = children;
    }

    public StatementList getChildren() {
        return children;
    }
}
