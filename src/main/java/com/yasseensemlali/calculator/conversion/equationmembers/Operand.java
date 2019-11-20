package com.yasseensemlali.calculator.conversion.equationmembers;

public class Operand extends ExpressionMember{
	
	private double value;

	public Operand(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value+"";
	}

	@Override
	public boolean canFollow(ExpressionMember member) {
		if(member == null) {
			return true;
		}
		
		if(member.isOperator()) {
			return true;
		}
		
		return false;
	}
}
