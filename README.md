# Instrumentation DSL #

This DSL is inspired by the dtrace D language.

Pulled the original Dtrace grammar from [dt_grammar.y](https://github.com/oracle/dtrace-utils/blob/devel/libdtrace/dt_grammar.y).
Pulled the original Dtrace lexer from [dt_lex.l](https://github.com/oracle/dtrace-utils/blob/devel/libdtrace/dt_lex.l).
We have translated this yacc format to `Antlr4`.

## Tutorials ##
Run initial build with the following command to generate the Antlr Lexer/Parser/Listener/Visitor for languages:
```shell
mvn clean package
```

### ShapePlacer ###
I followed [this blog](https://codevomit.wordpress.com/2015/03/15/antlr4-project-with-maven-tutorial-episode-1/).

### Arithmetic ###
I followed [this blog](https://codevomit.wordpress.com/2015/04/25/antlr4-project-with-maven-tutorial-episode-3/).
Compilation follow-on found [here](https://codevomit.wordpress.com/2015/07/05/my-new-favourite-thing-asm/).