/**
 * 从输入字符串提取出Token
 */
public class Lexer {
    /**
     * 需要解析的字符串表达式。比如：1 * 3 + 2 -3
     */
    private final String input;

    /**
     * 下一个要处理的字符，在输入字符串中的位置，初始化为0代表还没有字符被处理
     */
    private int position = 0;

    public Lexer(final String input) {
        this.input = input;
    }

    /**
     * 通过语法分析，提取出下一个Token
     * 可以跳过空白字符
     * TODO: 改成Stream
     *
     * @return 字符串中下一个Token
     */
    public Token getNextToken() {
        while (position < input.length()) {
            final char c = input.charAt(position);
            if (Character.isWhitespace(c)) { skipWhiteSpace(); } // 跳过当前空白字符
            else if (Character.isDigit(c)) { return new Token(TokenType.INTEGER, getNextInteger()); }
            else {
                position++; // 因为之后吃掉一个字符，这里统一递增计数器
                if ('+' == c) { return new Token(TokenType.PLUS, '+'); }
                else if ('-' == c) { return new Token(TokenType.MINUS, '-'); }
                else if ('*' == c) { return new Token(TokenType.TIMES, '*'); }
                else if ('/' == c) { return new Token(TokenType.DIVIDE, '/'); }
                else { throw new UnsupportedOperationException("不支持的操作符"); }
            }
        }
        return new Token(TokenType.EOF, '\0');
    }

    /**
     * 返回字符串中下一个整数
     *
     * @return 解析出来的整数
     */
    private int getNextInteger() {
        StringBuilder pending = new StringBuilder(); // 已经遇到的数字字符
        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            pending.append(input.charAt(position++));
        }
        return Integer.parseInt(pending.toString());
    }

    /**
     * 跳过所有空白字符
     * //@return 是否成功跳过，如果遇到结尾则返回False
     */
    private void skipWhiteSpace() {
        while (position < input.length() && Character.isWhitespace(input.charAt(++position))) { }
        // return position < input.length(); //如果遇到结尾则返回失败
    }

    // /**
    //  * 当前位置指针前进一个字符
    //  *
    //  * @return 是否成功前进一个字符，如果遇到结尾则返回False
    //  */
    // private boolean advance() {
    //     return ++position < input.length();
    // }

    public static void main(String[] args) {
        Lexer lexer = new Lexer("1 * 3  / 2");
        System.out.println(lexer.getNextInteger());
        System.out.println(lexer.getNextInteger());
        System.out.println(lexer.getNextInteger());
    }
}
