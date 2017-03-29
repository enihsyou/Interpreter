import java.util.ArrayList;

/**
 * 解析由Lexer语法分析器传递过来的Token流
 * program : compound_statement DOT
 *
 * compound_statement : BEGIN statement_list END
 *
 * statement_list : statement | statement SEMI statement_list
 *
 * statement : compound_statement | assignment_statement | empty
 *
 * assignment_statement : variable ASSIGN expr
 *
 * expr: term ((PLUS | MINUS) term)*
 *
 * term: factor ((MUL | DIV) factor)*
 *
 * factor : PLUS factor | MINUS factor | INTEGER | LPAREN expr RPAREN | variable
 *
 * empty :
 *
 * variable: name
 */
class Parser {
    private final Lexer lexer;
    private Token<?> currentToken;

    Parser(final Lexer lexer) {
        this.lexer = lexer;
        currentToken = lexer.getNextToken(); // 初始化，指向第一个Token
    }

    public static void main(String[] args) {
        Parser parser = new Parser(
            new Lexer("BEGIN  BEGIN  number = 2; a = number; b = 10 * a + 10 * number / 4;  c = a--b END;x = 11;END."));
        System.out.println(parser.parse());
    }

    /**
     * 使用当前的Token和期望的Token进行对比，如果是相同的，则跳到下一个Token
     *
     * @param type 期望的Token类型
     */
    private void eat(final TokenType type) {
        if (currentToken.getType() == type) { currentToken = lexer.getNextToken(); }
        else { throw new AssertionError(type + "类型不符合对应" + type + "的要求"); }
    }

    private Token<String> program() {
        final CompoundStatement node = compoundStatement();
        eat(TokenType.DOT);
        return node;
    }

    private CompoundStatement compoundStatement() {
        eat(TokenType.BEGIN);
        final StatementList nodes = statementList();
        eat(TokenType.END);

        return new CompoundStatement(nodes);
    }

    private StatementList statementList() {
        final BaseStatement node = statement();
        final ArrayList<BaseStatement> children = new ArrayList<>();
        children.add(node);

        while (currentToken.getType() == TokenType.SEMICOLON) {
            eat(TokenType.SEMICOLON);
            children.add(statement());
        }
        if (currentToken.getType() == TokenType.VARIABLE) {
            throw new AssertionError("node = [" + currentToken + "]" + "不应该发生");
        }
        return new StatementList(children);
    }

    private BaseStatement statement() {
        switch (currentToken.getType()) {
            case BEGIN:
                return compoundStatement();
            case VARIABLE:
                return assignmentStatement();
            default:
                return empty();
        }
    }

    private AssignmentStatement assignmentStatement() {
        final Variable variable = variable();
        eat(TokenType.ASSIGN);
        final Token right = expr();
        return new AssignmentStatement(variable, right);
    }

    private Variable variable() {
        Variable node = new Variable((String) currentToken.getValue());
        eat(TokenType.VARIABLE);
        return node;
    }

    private EmptyStatement empty() {return new EmptyStatement();}

    /**
     * 数学式子里的操作数，操作因子
     * factor : (PLUS | MINUS) factor | INTEGER | LPAREN expr RPAREN
     */
    private Token factor() {
        Token token = currentToken;
        switch (token.getType()) {
            case INTEGER:
                eat(TokenType.INTEGER);
                return token;
            case PLUS:
                eat(TokenType.PLUS);
                return new UnaryOperator(token, factor());
            case MINUS:
                eat(TokenType.MINUS);
                return new UnaryOperator(token, factor());
            case LPAREN:
                eat(TokenType.LPAREN);
                Token result = expr();
                eat(TokenType.RPAREN);
                return result;
            default:
                return variable();
        }
    }

    /**
     * 算术表达式
     * term   : factor ((TIMES | DIVIDE) factor)*
     * factor : (PLUS | MINUS) factor | INTEGER | LPAREN expr RPAREN
     *
     * @return 计算结果
     */
    private Token term() {
        Token result = factor();
        while (TokenType.SECOND.contains(currentToken.getType())) {
            final Token token = this.currentToken;
            switch (token.getType()) {
                case TIMES:
                    eat(TokenType.TIMES);
                    break;
                case DIVIDE:
                    eat(TokenType.DIVIDE);
                    break;
                case POWER:
                    eat(TokenType.POWER);
                    break;
            }
            result = new BinaryOperator(result, token, factor());
        }
        return result;
    }

    /**
     * expr   : term ((PLUS | MINUS) term)*
     * term   : factor ((TIMES | DIVIDE) factor)*
     * factor : (PLUS | MINUS) factor | INTEGER | LPAREN expr RPAREN
     *
     * @return 计算结果
     */
    private Token expr() {
        Token result = term();
        while (TokenType.FIRST.contains(currentToken.getType())) {
            final Token token = currentToken;
            switch (token.getType()) {
                case PLUS:
                    eat(TokenType.PLUS);
                    break;
                case MINUS:
                    eat(TokenType.MINUS);
                    break;
            }
            result = new BinaryOperator(result, token, term());
        }
        return result;
    }

    Token parse() {
        final Token<String> program = program();
        if (currentToken.getType() != TokenType.EOF) { throw new AssertionError("语句不完整"); }
        return program;
    }
}
