package com.yasseensemlali.calculator.conversion.equationmembers;

import com.yasseensemlali.calculator.conversion.equationmembers.operators.*;

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
		}

		if (member.matches("-?\\d+(\\.\\d+)?")) {
			return new Operand(Double.parseDouble(member));
		} else {
			throw new InvalidExpressionMemberException(member + " is not a valid member");
		}
	}

	public boolean isOperand() {
		return this instanceof Operand;
	}

	public boolean isOperator() {
		return this instanceof Operator;
	}
	
	public abstract boolean canFollow(ExpressionMember member);
}
