package lang.example.arithmetic.ast;

import lang.example.arithmetic.ast.visitor.ArithmeticASTVisitor;

public class DivisionASTNode extends BinaryExpression {
    public DivisionASTNode(ArithmeticASTNode leftOperand, ArithmeticASTNode rightOperand) {
        super();
        setLeftOperand(leftOperand);
        setRightOperand(rightOperand);
    }

    @Override
    public Object accept(ArithmeticASTVisitor visitor) {
        return visitor.visitDivision(this);
    }
}
