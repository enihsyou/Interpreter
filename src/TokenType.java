import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum TokenType {
    INTEGER, DOUBLE, PLUS, MINUS, TIMES, DIVIDE, POWER, EOF;
    public static final Set<TokenType> OPERATORS =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{PLUS, MINUS, TIMES, DIVIDE, POWER,})));
    public static final Set<TokenType> FIRST =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{PLUS, MINUS,})));
    public static final Set<TokenType> SECOND =
        Collections.unmodifiableSet(new HashSet<>(Arrays.asList(new TokenType[]{TIMES, DIVIDE, POWER,})));
}



