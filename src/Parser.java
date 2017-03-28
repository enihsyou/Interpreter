/**
 * 解析由Lexer语法分析器传递过来的Token流，通过状态机
 */
public class Parser {
    private final Lexer mLexer;
    private Token currentToken;

    public Parser(final Lexer lexer) {
        mLexer = lexer;
        currentToken = lexer.getNextToken(); // 初始化，指向第一个Token
    }

    /**
     * 使用当前的Token和期望的Token进行对比，如果是相同的，则跳到下一个Token
     *
     * @param type 期望的Token类型
     */
    private void eat(final TokenType type) {
        if (currentToken.getType() == type) { currentToken = mLexer.getNextToken(); }
        else { throw new AssertionError(type + "类型不符合要求"); }
    }

    /**
     * 数学式子里的操作数，操作因子
     * factor : INTEGER
     */
    private Token factor() {
        Token token = currentToken;
        switch (token.getType()) {
            case INTEGER:
                eat(TokenType.INTEGER);
                return token;
            case LPAREN:
                eat(TokenType.LPAREN);
                Token result = expr();
                eat(TokenType.RPAREN);
                return result;
            default:
                throw new AssertionError("不应该发生");
        }
    }

    /**
     * 算术表达式
     * term : factor ((TIMES | DIVIDE) factor)*
     * factor : INTEGER
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
                // case POWER:
                //     eat(TokenType.POWER);
                //     break;
            }
            result = new BinaryOperator(result, token, factor());
        }
        return result;
    }

    /**
     * expr : term ((PLUS | MINUS) term)*
     * term : factor ((TIMES | DIVIDE) factor)*
     * factor : INTEGER
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

    Token parse() {return expr();}
}
