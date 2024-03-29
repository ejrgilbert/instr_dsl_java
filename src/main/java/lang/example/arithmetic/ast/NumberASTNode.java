package lang.example.arithmetic.ast;

import lang.example.arithmetic.ast.visitor.ArithmeticASTVisitor;

public class NumberASTNode extends ArithmeticASTNode {
    private Number value;

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public NumberASTNode(Number value) {
        super();
        setValue(value);
    }

    @Override
    public Object accept(ArithmeticASTVisitor visitor) {
        return visitor.visitNumber(this);
    }
}
