package com.yasseensemlali.calculator.conversion.equationmembers.parenthesis;

import com.yasseensemlali.calculator.conversion.equationmembers.PrecedenceConstants;
import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.NonNumber;
import com.yasseensemlali.calculator.conversion.equationmembers.Parenthesis;

/** A right parenthesis ")"
 *
 * @author Yasseen
 */
public class RightParenthesis extends Parenthesis{

    @Override
    public int getPrecedence() {
        return PrecedenceConstants.PRECEDENCE_MAX;
    }

    @Override
    public boolean canFollow(ExpressionMember member) {
        if (member == null) {
            return false;
        }

        return member.isOperand()|| member.isRightParenthesis();
    }

    @Override
    public boolean canFollowOnOperatorStack(NonNumber nonNumber) {
        return false;
    }

    @Override
    public String toString() {
        return ")";
    }
    
}
