grammar Jcc;

PAREN_OPEN: '(' ;
PAREN_CLOSE: ')' ;
SEMICOLON: ';' ;
BRACE_OPEN: '{';
BRACE_CLOSE: '}';
KEYWORD_INT: 'int';
KEYWORD_RETURN: 'return'[ \t\r\n];
IDENTIFIER: [a-zA-Z]+ ;
LITERAL_INT: [0-9]+ ;
WS: [ \t\r\n]+ -> skip;

program: functionDeclaration;

functionDeclaration: KEYWORD_INT IDENTIFIER PAREN_OPEN PAREN_CLOSE BRACE_OPEN statement BRACE_CLOSE;

statement: KEYWORD_RETURN expression SEMICOLON;

expression: LITERAL_INT;

