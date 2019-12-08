package com.yasseensemlali.calculator.conversion.equationmembers;

public abstract class Operator extends NonNumber {

    /** Applies the operator on the specified operand, with operand1 being on the left 
     * @param operand1 The operand on the left of the expression
     * @param operand2 The operand on the right of the expression
     * @return
     */
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
