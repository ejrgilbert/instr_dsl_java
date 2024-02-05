package lang.example.arithmetic.ast;

import lang.example.arithmetic.ast.visitor.ArithmeticASTVisitor;

public abstract class ArithmeticASTNode {
    public abstract Object accept(ArithmeticASTVisitor visitor);
}
