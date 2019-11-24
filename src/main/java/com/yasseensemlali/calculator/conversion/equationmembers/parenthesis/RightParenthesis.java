package com.yasseensemlali.calculator.conversion.equationmembers.parenthesis;

import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.NonNumber;
import com.yasseensemlali.calculator.conversion.equationmembers.Parenthesis;

/**
 *
 * @author Yasseen
 */
public class RightParenthesis extends Parenthesis{

    @Override
    public int getPrecedence() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canFollow(ExpressionMember member) {
        if (member == null) {
            return false;
        }

        return member.isOperand()|| member.isRightParenthesis();
    }

    @Override
    public int compareTo(NonNumber arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return ")";
    }
    
}
