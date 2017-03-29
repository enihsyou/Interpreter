public interface NodeVisitor {
    default Token<?> visit(Token<?> node) {
        if (node instanceof UnaryOperator) { return visitUnary((UnaryOperator) node); }
        else if (node instanceof BinaryOperator) { return visitBinary((BinaryOperator) node); }
        else { return genericVisit(node); }
    }

    Token<?> visitUnary(UnaryOperator node);

    Token<?> visitBinary(BinaryOperator node);

    default Token<?> genericVisit(Token<?> node) {
        return node;
    }
}
