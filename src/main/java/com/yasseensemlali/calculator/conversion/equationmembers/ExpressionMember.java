package com.yasseensemlali.calculator.conversion.equationmembers;

import com.yasseensemlali.calculator.conversion.equationmembers.parenthesis.LeftParenthesis;
import com.yasseensemlali.calculator.conversion.equationmembers.parenthesis.RightParenthesis;

/** Represents anything that can be contained in an equation.
 * @author Yasseen
 *
 */
public abstract class ExpressionMember {
	
	/**
	 * @return Whether the current member is an operand
	 */
	public boolean isOperand() {
		return this instanceof Operand;
	}
	/**
	 * @return Whether the current member is a NonNumber
	 */
	public boolean isNonNumber() {
		return this instanceof NonNumber;
	}
	/**
	 * @return Whether the current member is an Operator
	 */
	public boolean isOperator() {
		return this instanceof Operator;
	}
	/**
	 * @return Whether the current member is a Parenthesis
	 */
	public boolean isParenthesis() {
		return this instanceof Parenthesis;
	}
	/**
	 * @return Whether the current member is a LeftParenthesis
	 */
	public boolean isLeftParenthesis() {
		return this instanceof LeftParenthesis;
	}
	/**
	 * @return Whether the current member is a RightParenthesis
	 */
	public boolean isRightParenthesis() {
		return this instanceof RightParenthesis;
	}


	/** Checks if the current {@link ExpressionMember} can follow the one specified
	 * @param member The preceding {@link ExpressionMember}
	 * @return
	 */
	public abstract boolean canFollow(ExpressionMember member);
}
