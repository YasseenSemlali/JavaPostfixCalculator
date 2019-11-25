/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasseensemlali.calculator.conversion.equationmembers;

/**
 *
 * @author Yasseen
 */
public abstract class NonNumber extends ExpressionMember {
	public abstract int getPrecedence();
        public abstract boolean canFollowOnOperatorStack(NonNumber nonNumber);
}
