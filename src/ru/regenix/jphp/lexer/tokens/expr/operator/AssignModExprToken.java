package ru.regenix.jphp.lexer.tokens.expr.operator;

import ru.regenix.jphp.lexer.TokenType;
import ru.regenix.jphp.lexer.tokens.TokenMeta;

public class AssignModExprToken extends AssignOperatorExprToken {
    public AssignModExprToken(TokenMeta meta) {
        super(meta, TokenType.T_MOD_EQUAL);
    }
}
