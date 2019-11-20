package com.yasseensemlali.calculator.conversion.equationmembers.operators;

import com.yasseensemlali.calculator.conversion.equationmembers.EquationConstants;
import com.yasseensemlali.calculator.conversion.equationmembers.Operand;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;

public class Divide extends Operator {
	private static final int PRECEDENCE = EquationConstants.PRECEDENCE_2;

	@Override
	public double apply(Operand operand1, Operand operand2) {
		return operand1.getValue() / operand2.getValue();
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}
	
	@Override
	public String toString() {
		return "/";
	}

}
