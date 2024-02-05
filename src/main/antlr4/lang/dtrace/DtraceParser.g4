parser grammar DtraceParser;

options { tokenVocab = DtraceLexer; }

dscript : probe_def probe_def* EOF;

probe_def : (ID | PROBE_ID | PROBE_SPEC) predicate? LCURLY statement* RCURLY ;

predicate : SLASH exp SLASH ;

exp : exp LOGOP exp |
      exp RELOP exp |
      exp MULOP exp |
      exp SUMOP exp |
      LPAREN exp RPAREN |
      (ID | STRING | INT) ;

statement : exp ;
