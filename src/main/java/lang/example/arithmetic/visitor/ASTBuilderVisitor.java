package lang.example.arithmetic.visitor;

import lang.example.ArithmeticBaseVisitor;
import lang.example.ArithmeticParser.ProgramContext;
import lang.example.ArithmeticParser.AlgebraicSumContext;
import lang.example.ArithmeticParser.MultiplicationContext;
import lang.example.ArithmeticParser.NumberContext;
import lang.example.ArithmeticParser.InnerExpressionContext;
import lang.example.arithmetic.ast.ArithmeticASTNode;
import lang.example.arithmetic.ast.DifferenceASTNode;
import lang.example.arithmetic.ast.DivisionASTNode;
import lang.example.arithmetic.ast.MultiplicationASTNode;
import lang.example.arithmetic.ast.NumberASTNode;
import lang.example.arithmetic.ast.SumASTNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts the Concrete Syntax Tree (aka Parse Tree) to an Abstract Syntax Tree (AST).
 * Follows the order of the grammar defined in Arithmetic.g4
 */
public class ASTBuilderVisitor extends ArithmeticBaseVisitor<ArithmeticASTNode> {
    private static final Logger logger = LoggerFactory.getLogger(ASTBuilderVisitor.class);

    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String TIMES = "*";
    public static final String DIVIDED = "/";

//    @Override
//    public ArithmeticASTNode visit(ParseTree tree) {
//        return tree.accept(this);
//    }
//
//    @Override
//    public ArithmeticASTNode visitChildren(RuleNode node) {
//        // unsure why you'd do this `return null`?
//        return null;
//    }
//
//    @Override
//    public ArithmeticASTNode visitTerminal(TerminalNode node) {
//        // unsure why you'd do this `return null`?
//        return null;
//    }
//
//    @Override
//    public ArithmeticASTNode visitErrorNode(ErrorNode node) {
//        // unsure why you'd do this `return null`?
//        return null;
//    }

    @Override
    public ArithmeticASTNode visitProgram(ProgramContext context) {
        return context.expression().accept(this);
    }

    @Override
    public ArithmeticASTNode visitMultiplication(MultiplicationContext context) {
        String operand = context.getChild(1).getText();
        ArithmeticASTNode leftOperand = context.expression(0).accept(this);
        ArithmeticASTNode rightOperand = context.expression(1).accept(this);

        if (operand.equals(TIMES)) {
            return new MultiplicationASTNode(leftOperand, rightOperand);
        } else if (operand.equals(DIVIDED)) {
            return new DivisionASTNode(leftOperand, rightOperand);
        }

        throw new ArithmeticException("Something has really gone wrong: operand '" + operand + "' is unexpected.");
    }

    @Override
    public ArithmeticASTNode visitAlgebraicSum(AlgebraicSumContext context) {
        String operand = context.getChild(1).getText();
        ArithmeticASTNode leftOperand = context.expression(0).accept(this);
        ArithmeticASTNode rightOperand = context.expression(1).accept(this);

        if (operand.equals(PLUS)) {
            return new SumASTNode(leftOperand, rightOperand);
        } else if (operand.equals(MINUS)) {
            return new DifferenceASTNode(leftOperand, rightOperand);
        }

        throw new ArithmeticException("Something has really gone wrong: operand '" + operand + "' is unexpected.");

    }

    @Override
    public ArithmeticASTNode visitNumber(NumberContext context) {
        String v = context.DOUBLE().getText();
        Double value = Double.parseDouble(v);

        return new NumberASTNode(value);
    }

    @Override
    public ArithmeticASTNode visitInnerExpression(InnerExpressionContext context) {
        return context.expression().accept(this);
    }
}
