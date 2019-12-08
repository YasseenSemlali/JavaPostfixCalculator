package com.yasseensemlali.calculator.conversion.equationmembers.parenthesis;

import com.yasseensemlali.calculator.conversion.equationmembers.PrecedenceConstants;
import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.NonNumber;
import com.yasseensemlali.calculator.conversion.equationmembers.Parenthesis;

/** A left parenthesis "("
 *
 * @author Yasseen
 */
public class LeftParenthesis extends Parenthesis {

    @Override
    public int getPrecedence() {
        return PrecedenceConstants.PRECEDENCE_MAX;
    }

    @Override
    public boolean canFollow(ExpressionMember member) {
        if (member == null) {
            return true;
        }

        return member.isOperator() || member.isOperand() ||member.isLeftParenthesis();
    }

    @Override
    public boolean canFollowOnOperatorStack(NonNumber nonNumber) {
        return true;
    }

    

    @Override
    public String toString() {
        return "(";
    }
    
    

}
