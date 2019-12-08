package com.yasseensemlali.calculator.conversion.equationmembers;

/**
 *
 * @author Yasseen
 */
public abstract class NonNumber extends ExpressionMember {
	/** Gets the precedence of the NonNumber
	 */
	public abstract int getPrecedence();
	
	/** Checks if the current NonNumber can follow the specified one in the operator stack, or if it needs to be popped
	 */
	public abstract boolean canFollowOnOperatorStack(NonNumber nonNumber);
}
