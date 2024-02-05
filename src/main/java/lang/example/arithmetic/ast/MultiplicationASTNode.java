package lang.example.arithmetic.ast;

import lang.example.arithmetic.ast.visitor.ArithmeticASTVisitor;

public class MultiplicationASTNode extends BinaryExpression {
    public MultiplicationASTNode(ArithmeticASTNode leftOperand, ArithmeticASTNode rightOperand) {
        super();
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Object accept(ArithmeticASTVisitor visitor) {
        return visitor.visitMultiplication(this);
    }
}
