package lang.example.arithmetic.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import lang.example.ArithmeticParser.AlgebraicSumContext;
import lang.example.ArithmeticParser.InnerExpressionContext;
import lang.example.ArithmeticParser.MultiplicationContext;
import lang.example.ArithmeticParser.NumberContext;
import lang.example.ArithmeticParser.ProgramContext;
import lang.example.ArithmeticVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The first attempt to interpret the expressions will be a visitor that operates on the concrete parse tree.
 * I call it “naive” because you do not need to define an AST object model: you just traverse the parse tree and
 * “manually” skip all the productions and terminals that you do not care about.
 */

public class NaiveInterpreterVisitor implements ArithmeticVisitor<Double> {

    private static final Logger logger = LoggerFactory.getLogger(NaiveInterpreterVisitor.class);

    private ParseTreeProperty<Double> numberNodesAnnotations = new ParseTreeProperty<>();

    protected ParseTreeProperty<Double> getNumberNodesAnnotations() {
        return numberNodesAnnotations;
    }

    protected void setNumberNodesAnnotations(
            ParseTreeProperty<Double> numberNodesAnnotations) {
        this.numberNodesAnnotations = numberNodesAnnotations;
    }

    @Override
    public Double visit(ParseTree arg0) {
        return arg0.accept(this);
    }

    @Override
    public Double visitChildren(RuleNode node)
    {
        return null;
    }

    @Override
    public Double visitTerminal(TerminalNode node) {

        return null;
    }

    @Override
    public Double visitErrorNode(ErrorNode node) {

        return null;
    }

    @Override
    public Double visitProgram(ProgramContext context) {
        return context.expression().accept(this);
    }

    @Override
    public Double visitAlgebraicSum(AlgebraicSumContext context) {
        String operand = context.getChild(1).getText();
        Double left = context.expression(0).accept(this);
        Double right = context.expression(1).accept(this);
        if(operand.equals("+")){
            return left + right;
        }
        else if(operand.equals("-")){
            return left - right;
        }
        else
        {
            throw new ArithmeticException("Something has really gone wrong");
        }
    }

    @Override
    public Double visitMultiplication(MultiplicationContext context) {
        String operand = context.getChild(1).getText();
        Double left = context.expression(0).accept(this);
        Double right = context.expression(1).accept(this);
        if(operand.equals("*")){
            return left * right;
        }
        else if(operand.equals("/")){
            return left / right;
        }
        else
        {
            throw new ArithmeticException("Something has really gone wrong");
        }
    }

    @Override
    public Double visitInnerExpression(InnerExpressionContext context) {
        return context.expression().accept(this);
    }

    @Override
    public Double visitNumber(NumberContext context) {
        String v = context.DOUBLE().getText();
        return Double.parseDouble(v);
    }
}
