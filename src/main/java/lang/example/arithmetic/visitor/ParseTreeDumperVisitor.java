package lang.example.arithmetic.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import lang.example.ArithmeticParser.AlgebraicSumContext;
import lang.example.ArithmeticParser.InnerExpressionContext;
import lang.example.ArithmeticParser.MultiplicationContext;
import lang.example.ArithmeticParser.NumberContext;
import lang.example.ArithmeticParser.ProgramContext;
import lang.example.ArithmeticVisitor;

public class ParseTreeDumperVisitor implements ArithmeticVisitor<String> {

    private int indentationLevel = 0;

    private void increaseIndentation() {
        this.indentationLevel += 1;
    }

    private void decreaseIndentation() {
        this.indentationLevel -= 1;
    }

    @Override
    public String visit(ParseTree tree) {
        return tree.accept(this);
    }

    @Override
    public String visitChildren(RuleNode node) {
        StringBuilder builder = new StringBuilder();
        builder.append(System.lineSeparator());
        int childrenCount = node.getChildCount();
        increaseIndentation();
        for (int i = 0; i < childrenCount; i++) {
            builder.append(node.getChild(i).accept(this));
        }
        decreaseIndentation();
        return builder.toString();
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        return getIndentation() + "TERMINAL: " + node.getText() + System.lineSeparator();
    }

    @Override
    public String visitErrorNode(ErrorNode node) {

        return null;
    }

    @Override
    public String visitProgram(ProgramContext context) {
        return getIndentation() + visitChildren(context) + System.lineSeparator();
    }

    @Override
    public String visitAlgebraicSum(AlgebraicSumContext context) {
        return getIndentation() + "AlgebraicSum" + visitChildren(context);
    }

    @Override
    public String visitMultiplication(MultiplicationContext context) {
        return getIndentation() + "Multiplication" + visitChildren(context);
    }

    @Override
    public String visitNumber(NumberContext context) {
        return getIndentation() + "Number: " + context.DOUBLE().accept(this);
    }

    @Override
    public String visitInnerExpression(InnerExpressionContext context) {
        return getIndentation() + "InnerExpression" + visitChildren(context);
    }

//    @Override
//    public String visitRealNumber(RealNumberContext context) {
//        String intPart = context.NUMBER(0).getText();
//        if(context.getChildCount() == 3){
//            String decimal = context.NUMBER(1).getText();
//            return intPart + "." + decimal + System.lineSeparator();
//        }
//        else return intPart + System.lineSeparator();
//    }

    private String getIndentation() {
        return "--".repeat(Math.max(0, indentationLevel));
    }
}
