package visitors;

import ast.nodes.*;

public interface ASTVisitor<R> {
	R visitAssignmentNode(AssignmentNode node);

	R visitAtomNode(AtomNode node);

	R visitBreakNode(BreakNode node);

	R visitComparisonNode(ComparisonNode node);

	R visitConditionNode(ConditionNode node);

	R visitConsNode(ConsNode node);

	R visitFunctionNode(FunctionNode node);

	R visitFunctionCallNode(FunctionCallNode node);

	R visitHeadNode(HeadNode node);

	R visitLambdaNode(LambdaNode node);

	R visitListNode(ListNode node);

	R visitLiteralNode(LiteralNode node);

	R visitLogicalOperationNode(LogicalOperationNode node);

	R visitNotNode(NotNode node);

	R visitOperationNode(OperationNode node);

	R visitPredicateNode(PredicateNode node);

	R visitProgNode(ProgNode node);

	R visitReturnNode(ReturnNode node);

	R visitTailNode(TailNode node);

	R visitWhileNode(WhileNode node);

	R visitConditionBranch(ConditionBranch branch);

	R visitQuoteNode(QuoteNode node);

	R visitLambdaCallNode(LambdaCallNode node);

	R visitEvalNode(EvalNode node);

	R visitNullNode(NullNode nullNode);

	R visitBoolNode(BooleanNode booleanNode);
}
