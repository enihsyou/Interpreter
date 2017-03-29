public class AssignOperator extends BaseOperator {
    private final Token left;
    private final Token right;

    AssignOperator(final Token left, final Token operator, final Token right) {
        super(operator.getType());
        this.left = left;
        this.right = right;
    }

    Token getLeft() {
        return left;
    }

    Token getRight() {
        return right;
    }
}
