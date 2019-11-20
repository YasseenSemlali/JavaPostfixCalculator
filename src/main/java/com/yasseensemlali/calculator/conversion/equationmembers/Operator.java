package com.yasseensemlali.calculator.conversion.equationmembers;

public abstract class Operator  extends ExpressionMember implements Comparable<Operator>{
	public abstract double apply(Operand operand1, Operand operand2);
	public abstract int getPrecedence();
	
	@Override
	public int compareTo(Operator o) {
		return o.getPrecedence() - this.getPrecedence();
	}
	
	@Override
	public boolean canFollow(ExpressionMember member) {
		if(member == null) {
			return false;
		}
		
		if(member.isOperand()) {
			return true;
		}
		
		return false;
	}
}
