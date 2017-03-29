public class IntegerToken extends BaseNumberToken {

    public IntegerToken(final int value) {
        super(TokenType.INTEGER, value);
    }

    public IntegerToken(final Integer value) {
        super(TokenType.INTEGER, value);
    }
}
