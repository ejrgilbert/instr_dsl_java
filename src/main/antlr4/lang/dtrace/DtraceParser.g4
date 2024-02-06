parser grammar DtraceParser;

options { tokenVocab = DtraceLexer; }

dscript : probe_def probe_def* EOF;

probe_def : (ID | PROBE_ID | PROBE_SPEC) predicate? LCURLY statement* RCURLY ;

predicate : SLASH exp SLASH ;

exp : exp LOGOP exp         #LogExp
      | exp RELOP exp       #RelExp
      | exp MULOP exp       #MulExp
      | exp SUMOP exp       #SumExp
      | LPAREN exp RPAREN   #InnerExp
      | (ID | STRING | INT) #BaseExp ;

statement : exp ;
