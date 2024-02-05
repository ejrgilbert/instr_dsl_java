package lang.example.arithmetic.ast.visitor;

import lang.example.arithmetic.ast.DifferenceASTNode;
import lang.example.arithmetic.ast.DivisionASTNode;
import lang.example.arithmetic.ast.MultiplicationASTNode;
import lang.example.arithmetic.ast.NumberASTNode;
import lang.example.arithmetic.ast.SumASTNode;

public class EvaluationVisitor implements ArithmeticASTVisitor {
    @Override
    public Number visitDivision(DivisionASTNode divisionASTNode) {
        Number leftOperand = (Number) divisionASTNode.getLeftOperand().accept(this);
        Number rightOperand = (Number) divisionASTNode.getRightOperand().accept(this);
        return leftOperand.doubleValue() / rightOperand.doubleValue();
    }

    @Override
    public Number visitMultiplication(MultiplicationASTNode multiplicationASTNode) {
        Number leftOperand = (Number) multiplicationASTNode.getLeftOperand().accept(this);
        Number rightOperand = (Number) multiplicationASTNode.getRightOperand().accept(this);
        return leftOperand.doubleValue() * rightOperand.doubleValue();

    }

    @Override
    public Number visitDifference(DifferenceASTNode differenceASTNode) {
        Number leftOperand = (Number) differenceASTNode.getLeftOperand().accept(this);
        Number rightOperand = (Number) differenceASTNode.getRightOperand().accept(this);
        return leftOperand.doubleValue() - rightOperand.doubleValue();
    }

    @Override
    public Number visitSum(SumASTNode sumASTNode) {
        Number leftOperand = (Number) sumASTNode.getLeftOperand().accept(this);
        Number rightOperand = (Number) sumASTNode.getRightOperand().accept(this);
        return leftOperand.doubleValue() + rightOperand.doubleValue();

    }

    @Override
    public Number visitNumber(NumberASTNode numberASTNode) {
        return numberASTNode.getValue();
    }

}
