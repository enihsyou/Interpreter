/**
 * 解析由Lexer语法分析器传递过来的Token流，通过状态机
 */
public class Parser {
    private final Lexer mLexer;
    private Token currentToken;

    public Parser(final Lexer lexer) {
        mLexer = lexer;
        currentToken = lexer.getNextToken();
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
     * 数学式子里的操作数
     * factor : INTEGER
     */
    private int factor() {
        Token token = currentToken;
        eat(TokenType.INTEGER);
        return token.getValue();
    }

    /**
     * 算术表达式
     * term : factor ((TIMES | DIVIDE) factor)*
     * factor : INTEGER
     *
     * @return 计算结果
     */
    private int term() {
        int result = factor();
        while (TokenType.SECOND.contains(currentToken.getType())) {
            switch (currentToken.getType()) {
                case TIMES:
                    eat(TokenType.TIMES);
                    result *= factor();
                    break;
                case DIVIDE:
                    eat(TokenType.DIVIDE);
                    result /= factor();
                    break;
                // case POWER:
                //     eat(TokenType.POWER);
                //     result ^= factor();
                //     break;
            }
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
    public int expr() {
        int result = term();
        while (TokenType.FIRST.contains(currentToken.getType())) {
            switch (currentToken.getType()) {
                case PLUS:
                    eat(TokenType.PLUS);
                    result += term();
                    break;
                case MINUS:
                    eat(TokenType.MINUS);
                    result -= term();
                    break;
            }
        }
        return result;
    }
}
