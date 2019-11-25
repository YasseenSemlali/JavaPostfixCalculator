package com.yasseensemlali.calculator.conversion.equationmembers;

public abstract class Operator extends NonNumber {

    public abstract Operand apply(Operand operand1, Operand operand2);

    @Override
    public boolean canFollowOnOperatorStack(NonNumber o) {
        if (o.isLeftParenthesis()) {
            return true;
        }

        return this.getPrecedence() > o.getPrecedence();
    }

    @Override
    public boolean canFollow(ExpressionMember member) {
        if (member == null) {
            return false;
        }

        return member.isOperand() || member.isRightParenthesis();
    }
}
