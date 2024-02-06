lexer grammar DtraceLexer;

INT : DEC_DIG_W_ZERO |          // Single digit, base 10 (account for having just '0')
      DEC_DIG DEC_DIG_W_ZERO+ | // Multiple digit, base 10
      '0b' BIN_DIG+ |           // Binary digit
      '0' OCT_DIG+ |            // Octal digit
      '0x' HEX_DIG+ ;           // Hexadecimal digit

PROBE_SPEC : (ID | PROBE_ID)? ':' (ID | PROBE_ID)? |
             (ID | PROBE_ID)? ':' (ID | PROBE_ID)? ':' (ID | PROBE_ID)? |
             (ID | PROBE_ID)? ':' (ID | PROBE_ID)? ':' (ID | PROBE_ID)? ':' (ID | PROBE_ID)? ;

ID : WORD ( DEC_DIG_W_ZERO | WORD )* ;
STRING : '"' .*? '"' ;

PROBE_ID : PROBE_SYM+ ;
PROBE_SYM : LETTER |
            DEC_DIG_W_ZERO |
            '*' |
            '+' |
            '\\' |
            '?' |
            '!' |
            '[' |
            ']' ;

WORD : LETTER+;

LETTER : 'A' .. 'Z' |
         'a' .. 'z' |
         '_';

BIN_DIG : '0' | '1';
OCT_DIG : '0' .. '7';
HEX_DIG : DEC_DIG_W_ZERO |
          'A' .. 'F' |
          'a' .. 'f' ;

DEC_DIG : '1' .. '9';
DEC_DIG_W_ZERO : '0' | DEC_DIG;

SLASH : '/' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;

// Highest precedence arithmetic operators
SUMOP : '+' |
        '-' ;

// Next highest precedence arithmetic operators
MULOP : '*' |
        '/' |
        '%' ;

// Relational operators
RELOP : '==' |
        '!=' |
        '>' |
        '>=' |
        '<' |
        '<=' ;

// Logical operators
LOGOP : '&&' |
        '||' ;

BEGIN_COMM : '/*' ;
END_COMM : '*/' ;
COMMENT : BEGIN_COMM .*? END_COMM -> skip ; // skip the comments

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
