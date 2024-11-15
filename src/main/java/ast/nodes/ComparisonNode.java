package ast.nodes;

import visitors.ASTVisitor;

public class ComparisonNode extends ASTNode {
    String comparison;
    ASTNode leftElement;
    ASTNode rightElement;
    int line;
    private BooleanNode constantValue;

    public ComparisonNode(String comparison, ASTNode leftElement, ASTNode rightElement, int line) {
        this.comparison = comparison;
        this.leftElement = leftElement;
        this.rightElement = rightElement;
        this.line = line;
    }

    @Override
    public <R> R accept(ASTVisitor<R> visitor) {
        return visitor.visitComparisonNode(this);
    }

    public String getComparison() {
        return comparison;
    }

    public ASTNode getLeftElement() {
        return leftElement;
    }

    public ASTNode getRightElement() {
        return rightElement;
    }

    public int getLine() {
        return line;
    }

    public BooleanNode getConstantValue() {
        return constantValue;
    }

    @Override
    public void setConstantValue(BooleanNode constantValue) {
        this.constantValue = constantValue;
    }

    public boolean isConstant() {
        return constantValue != null;
    }

    @Override
    public ComparisonNode clone() {
        ComparisonNode clonedNode = new ComparisonNode(comparison, leftElement.clone(), rightElement.clone(), line);
        for (ASTNode child : this.getChildren()) {
            clonedNode.addChild(child.clone());
        }
        return clonedNode;
    }
}
