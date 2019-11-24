package com.yasseensemlali.calculator.conversion.equationmembers;

public abstract class Operator  extends NonNumber{
	public abstract double apply(Operand operand1, Operand operand2);
	
	@Override
	public int compareTo(NonNumber o) {
            
		return this.getPrecedence() - o.getPrecedence();
	}
	
	@Override
	public boolean canFollow(ExpressionMember member) {
		if(member == null) {
			return false;
		}
		
		return member.isOperand() ||  member.isRightParenthesis();
	}
}
