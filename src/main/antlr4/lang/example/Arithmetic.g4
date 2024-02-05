grammar Arithmetic;

program : expression EOF;

expression
    : expression ('*' | '/') expression #Multiplication
    | expression ('+' | '-') expression #AlgebraicSum
    | '(' expression ')' #InnerExpression
    | DOUBLE #Number;

//term: DOUBLE #Number

DOUBLE : [0-9]+ ('.'[0-9]+)? ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

//NUMBER : [0-9]+ ;