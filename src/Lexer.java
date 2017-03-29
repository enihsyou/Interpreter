import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * 从输入字符串提取出Token
 */
class Lexer {
    private static final HashMap<String, ControlToken> REVERSED_KEYWORDS = new HashMap<>();

    static {
        REVERSED_KEYWORDS.put("BEGIN", new ControlToken(TokenType.BEGIN));
        REVERSED_KEYWORDS.put("END", new ControlToken(TokenType.END));
    }

    /**
     * 需要解析的字符串表达式。比如：1 * 3 + 2 -3
     */
    private final String input;
    /**
     * 下一个要处理的字符，在输入字符串中的位置，初始化为0代表还没有字符被处理
     */
    private int position = -1;

    Lexer(@NotNull final String input) {
        this.input = input;
    }

    public static void main(String[] args) {
        Lexer lexer = new Lexer(
            ";BEGIN     BEGIN         number = 2;         a = number;         b = 10 * a + 10 * number / 4;         c = a - - b     END;     x = 11; END.");
        while (lexer.position < lexer.input.length()) { System.out.println(lexer.getNextToken()); }
    }

    /**
     * 通过语法分析，提取出下一个Token
     * 可以跳过空白字符
     * TODO: 改成Stream
     *
     * @return 字符串中下一个Token
     */
    Token<?> getNextToken() {
        while (advance()) {
            final char c = input.charAt(position);
            if (Character.isWhitespace(c)) { skipWhiteSpace(); } // 跳过当前空白字符
            else if (Character.isDigit(c)) { return new IntegerToken(TokenType.INTEGER, getNextInteger()); }
            else if (Character.isAlphabetic(c)) { return checkKeyword(getNextName()); }
            else if ('=' == c) { return new ControlToken(TokenType.ASSIGN); }
            else if (';' == c) { return new ControlToken(TokenType.SEMICOLON); }
            else if ('.' == c) { return new ControlToken(TokenType.DOT); }
            else if ('+' == c) { return new ArithmeticOperator(TokenType.PLUS); }
            else if ('-' == c) { return new ArithmeticOperator(TokenType.MINUS); }
            else if ('*' == c) { return new ArithmeticOperator(TokenType.TIMES); }
            else if ('/' == c) { return new ArithmeticOperator(TokenType.DIVIDE); }
            else if ('^' == c) { return new ArithmeticOperator(TokenType.POWER); }
            else if ('(' == c) { return new ControlToken(TokenType.LPAREN); }
            else if (')' == c) { return new ControlToken(TokenType.RPAREN); }
            else { throw new UnsupportedOperationException("不支持的操作符"); }
        }

        return new ControlToken(TokenType.EOF);
    }

    /**
     * 当前位置指针前进一个字符
     *
     * @return 是否成功前进一个字符，如果遇到结尾则返回False
     */
    private boolean advance() {
        return ++position < input.length();
    }

    /**
     * 跳过所有空白字符，先进行下一个字符的检测，再移动位置指针，防止在while循环内 多移动了一个位置
     * //@return 是否成功跳过，如果遇到结尾则返回False
     */
    private void skipWhiteSpace() {
        while (Character.isWhitespace(peek()) && advance()) { }
    }

    /**
     * 返回字符串中下一个整数
     *
     * @return 解析出来的整数
     */
    private int getNextInteger() {
        StringBuilder pending = new StringBuilder(); // 已经遇到的数字字符
        while (position < input.length()) {
            if (Character.isDigit(input.charAt(position))) {
                pending.append(input.charAt(position));
                advance();
            }
            else { break; }
        }
        return Integer.parseInt(pending.toString());
    }

    private Token<?> checkKeyword(String name) {
        if (REVERSED_KEYWORDS.containsKey(name)) { return REVERSED_KEYWORDS.get(name);}
        return new Variable(TokenType.VARIABLE, name);
    }

    /**
     * 返回字符串中下一个符合的变量名，由字母开头，后面跟着字母或数字
     *
     * @return 解析出来的变量名
     */
    private String getNextName() {
        StringBuilder pending = new StringBuilder();
        while (position < input.length()) { // 调用点保证了第一个字符仅可能是字母
            if (Character.isLetterOrDigit(input.charAt(position))) {
                pending.append(input.charAt(position));
                advance();
            }
            else { break; }
        }
        return pending.toString();
    }

    private char peek() {
        final int peekPosition = position + 1;
        if (peekPosition < input.length()) { return input.charAt(peekPosition); }
        return '\0';
    }
}
