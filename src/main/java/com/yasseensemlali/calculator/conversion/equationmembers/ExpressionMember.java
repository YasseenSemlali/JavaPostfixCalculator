package com.yasseensemlali.calculator.conversion.equationmembers;

import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidInfixEquationException;
import com.yasseensemlali.calculator.conversion.equationmembers.operators.*;
import com.yasseensemlali.calculator.conversion.equationmembers.parenthesis.LeftParenthesis;
import com.yasseensemlali.calculator.conversion.equationmembers.parenthesis.RightParenthesis;

public abstract class ExpressionMember {
	public static ExpressionMember getFromString(String member) {
		switch (member) {
		case "+":
			return new Plus();
		case "-":
			return new Minus();
		case "/":
			return new Divide();
		case "*":
			return new Times();
		case "(":
			return new LeftParenthesis();
		case ")":
			return new RightParenthesis();
		}

		if (member.matches("-?\\d+(\\.\\d+)?")) {
			return new Operand(Double.parseDouble(member));
		} 
                
                throw new InvalidInfixEquationException(member + " is not a valid member");
		
	}

	public boolean isOperand() {
		return this instanceof Operand;
	}

	public boolean isNonNumber() {
		return this instanceof NonNumber;
	}

	public boolean isOperator() {
		return this instanceof Operator;
	}

	public boolean isParenthesis() {
		return this instanceof Parenthesis;
	}

	public boolean isLeftParenthesis() {
		return this instanceof LeftParenthesis;
	}

	public boolean isRightParenthesis() {
		return this instanceof RightParenthesis;
	}

	
	public abstract boolean canFollow(ExpressionMember member);
}
