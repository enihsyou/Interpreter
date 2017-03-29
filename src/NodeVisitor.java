public interface NodeVisitor {
    default Token visit(Token<?> node) {
        if (node instanceof UnaryOperator) { return visitUnary((UnaryOperator) node); }
        else if (node instanceof BinaryOperator) { return visitBinary((BinaryOperator) node); }
        else if (node instanceof CompoundStatement) { visitCompoundStatement((CompoundStatement) node); }
        else if (node instanceof AssignmentStatement) { visitAssignmentStatement((AssignmentStatement) node); }
        else if (node instanceof VariableName) { return visitVariable((VariableName) node); }
        else if (node instanceof EmptyStatement) { visitEmptyStatement((EmptyStatement) node); }
        else { return genericVisit(node); }
        return null;
    }

    Token visitUnary(UnaryOperator node);

    Token visitBinary(BinaryOperator node);

    void visitCompoundStatement(CompoundStatement node);

    void visitAssignmentStatement(AssignmentStatement node);

    Token visitVariable(VariableName node);

    void visitEmptyStatement(EmptyStatement node);

    default Token genericVisit(Token<?> node) {
        return node;
    }
}
