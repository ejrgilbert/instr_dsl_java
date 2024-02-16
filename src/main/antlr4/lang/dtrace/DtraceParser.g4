parser grammar DtraceParser;

options { tokenVocab = DtraceLexer; }

dscript : probe_def+ EOF;

probe_def : spec predicate? LCURLY statement* RCURLY ;

predicate : SLASH exp SLASH ;

spec : ID | PROBE_ID | PROBE_SPEC ;

// TODO -- add support for NOT (e.g. !(a == b)
exp : exp MULOP exp       #MulExp
      | exp SUMOP exp       #SumExp
      | exp RELOP exp       #RelExp
      | exp LOGOP exp         #LogExp
      | LPAREN exp RPAREN   #InnerExp
      | (ID | STRING | INT) #BaseExp ;

statement : exp ;
