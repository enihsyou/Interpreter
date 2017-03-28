public abstract class NodeVisitor {
    public Token visit(Token node) {
        if (node instanceof UnaryOperator) { return visitUnary((UnaryOperator) node); }
        else if (node instanceof BinaryOperator) { return visitBinary((BinaryOperator) node); }
        else { return genericVisit(node); }
    }

    public abstract Token visitUnary(UnaryOperator node);

    public abstract Token visitBinary(BinaryOperator node);

    public Token genericVisit(Token node) {
        return node;
    }
}
