public class StatementList extends BaseStatement {
    private final BaseStatement first;
    private final BaseStatement second;

    public StatementList(final BaseStatement first) {
        super(TokenType.LIST);
        this.first = first;
        this.second = null;
    }

    public StatementList(final BaseStatement first, final BaseStatement second) {
        super(TokenType.LIST);
        this.first = first;
        this.second = second;
    }

    public BaseStatement getFirst() {
        return first;
    }

    public BaseStatement getSecond() {
        return second;
    }
}
