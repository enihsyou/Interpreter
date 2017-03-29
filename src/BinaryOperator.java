public class BinaryOperator extends BaseOperator {
    private final Token left;
    private final Token right;

    BinaryOperator(final Token left, final TokenType operator, final Token right) {
        super(operator);
        this.left = left;
        this.right = right;
    }

    Token getLeft() {
        return left;
    }

    Token getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " " + getType() + " " + right;
    }
}
