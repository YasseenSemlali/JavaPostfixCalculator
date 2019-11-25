package com.yasseensemlali.calculator.conversion.equationmembers.operators;

import com.yasseensemlali.calculator.conversion.equationmembers.EquationConstants;
import com.yasseensemlali.calculator.conversion.equationmembers.Operand;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.DivideByZeroException;

public class Divide extends Operator {

    private static final int PRECEDENCE = EquationConstants.PRECEDENCE_2;

    @Override
    public Operand apply(Operand operand1, Operand operand2) {
        if(operand2.getValue() == 0) {
            throw new DivideByZeroException("Cannot divide by 0");
        }
        return new Operand(operand1.getValue() / operand2.getValue());
    }

    @Override
    public int getPrecedence() {
        return PRECEDENCE;
    }

    @Override
    public String toString() {
        return "/";
    }

}
