import java.util.List;

public class StatementList extends BaseStatement {
    // private final BaseStatement first;
    // private final BaseStatement second;
    private final List<BaseStatement> children;

    public StatementList(final List<BaseStatement> children) {
        super(TokenType.LIST);
        this.children = children;
    }

    public List<BaseStatement> getChildren() {
        return children;
    }
}
// public StatementList(final BaseStatement first) {
//     super(TokenType.LIST);
//     this.first = first;
//     this.second = null;
// }
//
// public StatementList(final BaseStatement first, final BaseStatement second) {
//     super(TokenType.LIST);
//     this.first = first;
//     this.second = second;
// }

//     public BaseStatement getFirst() {
//         return first;
//     }
//
//     public BaseStatement getSecond() {
//         return second;
//     }
// }
