package com.yasseensemlali.calculator.conversion.equationmembers;

public interface Operator  extends ExpressionMember{
	public double apply(Operand operand1, Operand operand2);
}
