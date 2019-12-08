package com.yasseensemlali.calculator.conversion.equationmembers.operators;

import com.yasseensemlali.calculator.conversion.equationmembers.PrecedenceConstants;
import com.yasseensemlali.calculator.conversion.equationmembers.Operand;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;
/** Represents an addition operation "+"
 * @author Yasseen
 *
 */
public class Plus extends Operator {

    private static final int PRECEDENCE = PrecedenceConstants.PRECEDENCE_1;

    @Override
    public Operand apply(Operand operand1, Operand operand2) {
        return new Operand(operand1.getValue() + operand2.getValue());
    }

    @Override
    public int getPrecedence() {
        return PRECEDENCE;
    }

    @Override
    public String toString() {
        return "+";
    }

}
