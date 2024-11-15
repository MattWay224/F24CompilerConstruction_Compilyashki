package ast.nodes;

import visitors.ASTVisitor;

public class SignNode extends ASTNode {
    private final String sign;

    public SignNode(String sign) {
        this.sign = sign;
    }

    @Override
    public <R> R accept(ASTVisitor<R> visitor) {
        return visitor.visitSignNode(this);
    }

    @Override
    public ASTNode clone() {
        SignNode clonedNode = new SignNode(sign);
        for (ASTNode child : this.getChildren()) {
            clonedNode.addChild(child.clone());
        }
        return clonedNode;
    }

    public String getSign() {
        return sign;
    }
}
