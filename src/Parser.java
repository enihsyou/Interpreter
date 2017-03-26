/**
 * 解析由Lexer语法分析器传递过来的Token流，通过状态机
 */
public class Parser {
    private Lexer mLexer;
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
    private boolean eat(final TokenType type) {
        if (currentToken.getType() == type) {
            currentToken = mLexer.getNextToken();
            return true;
        }
        throw new AssertionError("类型不符合要求");
    }

    /**
     * 数学式子里的操作数
     */
    private int term() {
        Token token = currentToken;
        eat(TokenType.INTEGER);
        return token.getValue();
    }

    public int operator() {
        int result = term();
        while (TokenType.OPERATORS.contains(currentToken.getType())) {
            switch (currentToken.getType()) {
                case PLUS:
                    eat(TokenType.PLUS);
                    result += term();
                    break;
                case MINUS:
                    eat(TokenType.MINUS);
                    result -= term();
                    break;
                case TIMES:
                    eat(TokenType.TIMES);
                    result *= term();
                    break;
                case DIVIDE:
                    eat(TokenType.DIVIDE);
                    result /= term();
                    break;
            }
        }
        return result;
    }
}
