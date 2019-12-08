package com.yasseensemlali.calculator.conversion;

import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidInfixEquationException;
import com.yasseensemlali.calculator.conversion.equationmembers.NonNumber;
import com.yasseensemlali.calculator.conversion.equationmembers.Operand;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidPostfixEquationException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Evaluates a postfix expression and returns a result
 * @author Yasseen
 */
public class PostfixEvaluator {
    private final static Logger LOG = LoggerFactory.getLogger(InfixConverter.class);
    
    /**
     * @param stringExpression  A queue containing all members of the equation
     * @return A string containing the result of the operation
     */
    public String evaluatePostfix(Queue<String> stringExpression) {
        InfixConverter converter = new InfixConverter();
        Queue<ExpressionMember> expression = converter.getPostfixExpression(stringExpression);
        
        Deque<ExpressionMember> resultStack = new ArrayDeque<ExpressionMember>();
        
        LOG.info("Evaluating expression: " + expression);
        
        for(ExpressionMember member: expression) {
            LOG.info("Evaluating: " + member);
            
            if(member.isOperand()) {
                LOG.debug("member is operand, adding to result stack");
                
                resultStack.add(member);                
            } else if (member.isOperator()) {
                LOG.debug("member is operand");
                
                if(resultStack.size() < 2) {
                    throw new InvalidPostfixEquationException("Operator was not preceded by 2 members");
                }
                
                ExpressionMember operand2 = resultStack.removeLast();
                ExpressionMember operand1 = resultStack.removeLast();
                
                if(!operand1.isOperand() || !operand2.isOperand()) {
                    throw new InvalidPostfixEquationException("Operator was not preceded by 2 operands");
                }
                
                LOG.debug("Applying operator " + member + " on "  + operand1 + " and " + operand2);
                
                resultStack.addLast(this.applyOperator((Operator) member, (Operand) operand1, (Operand) operand2));
            } else {
                throw new InvalidPostfixEquationException("Could not evaluate expression because it an invalid member: " + member);
            } 
        }
        
        if(resultStack.size() != 1) {
            throw new InvalidPostfixEquationException("Result stack did not finish with 1 member");
        } else if (!resultStack.peekLast().isOperand()) {
             throw new InvalidPostfixEquationException("Result stack did not finish with am operand");
        }
        
        double result = ((Operand) resultStack.removeLast()).getValue();
        LOG.info("Result: " + result);
        return Double.toString(result);
    }
    
    /**Applies the operator on the specified operands, with operand1 being first
     * @param operator The operator to apply on the operands
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return
     */
    private Operand applyOperator(Operator operator, Operand operand1, Operand operand2) {
        return operator.apply(operand1, operand2);
    }
}
