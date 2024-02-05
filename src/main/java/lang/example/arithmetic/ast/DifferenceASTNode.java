package lang.example.arithmetic.ast;

import lang.example.arithmetic.ast.visitor.ArithmeticASTVisitor;

public class DifferenceASTNode extends BinaryExpression {
    public DifferenceASTNode(ArithmeticASTNode leftOperand, ArithmeticASTNode rightOperand) {
        super();
        setLeftOperand(leftOperand);
        setRightOperand(rightOperand);
    }

    @Override
    public Object accept(ArithmeticASTVisitor visitor) {
        return visitor.visitDifference(this);
    }
}
