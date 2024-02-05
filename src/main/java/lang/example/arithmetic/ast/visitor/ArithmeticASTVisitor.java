package lang.example.arithmetic.ast.visitor;

import lang.example.arithmetic.ast.DifferenceASTNode;
import lang.example.arithmetic.ast.DivisionASTNode;
import lang.example.arithmetic.ast.MultiplicationASTNode;
import lang.example.arithmetic.ast.NumberASTNode;
import lang.example.arithmetic.ast.SumASTNode;

public interface ArithmeticASTVisitor {
    public Number visitDivision(DivisionASTNode divisionASTNode);
    public Number visitMultiplication(MultiplicationASTNode multiplicationASTNode);
    public Number visitDifference(DifferenceASTNode differenceASTNode);
    public Number visitSum(SumASTNode sumASTNode);
    public Number visitNumber(NumberASTNode numberASTNode);
}
