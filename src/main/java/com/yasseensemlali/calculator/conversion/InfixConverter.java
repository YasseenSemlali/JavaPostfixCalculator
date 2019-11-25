package com.yasseensemlali.calculator.conversion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidInfixEquationException;
import com.yasseensemlali.calculator.conversion.equationmembers.NonNumber;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.NonMatchingParenthesisException;
import com.yasseensemlali.calculator.conversion.equationmembers.operators.Times;
import com.yasseensemlali.calculator.conversion.equationmembers.parenthesis.LeftParenthesis;
import com.yasseensemlali.calculator.conversion.equationmembers.parenthesis.RightParenthesis;

public class InfixConverter {

    private final static Logger LOG = LoggerFactory.getLogger(InfixConverter.class);

    public Queue<ExpressionMember> getPostfixExpression(Queue<String> expression) {
        Queue<ExpressionMember> infixExpression = this.processStringQueue(expression);
        LOG.info("Converting expression " + infixExpression);
        return this.infixToPostfix(infixExpression);
    }

    private Queue<ExpressionMember> processStringQueue(Queue<String> stringExpression) {
        Queue<ExpressionMember> infixExpression = new LinkedList<ExpressionMember>();
        
        ExpressionMember prevMember = null;
        for (String member : stringExpression) {
            ExpressionMember newMember = ExpressionMember.getFromString(member);
            
            if(prevMember != null) {
                if((prevMember.isOperand() && newMember.isLeftParenthesis()) ||
                        (prevMember.isRightParenthesis()&& newMember.isOperand())) {
                    infixExpression.add(new Times());
                }
            }
            
            infixExpression.add(ExpressionMember.getFromString(member));
            prevMember = newMember;
        }
        
        return infixExpression;
    }

    private Queue<ExpressionMember> infixToPostfix(Queue<ExpressionMember> expression) {
        Deque<NonNumber> operatorStack = new ArrayDeque<NonNumber>();
        Queue<ExpressionMember> postfixExpression = new LinkedList<ExpressionMember>();

        ExpressionMember previousMember = null;
        for (ExpressionMember member : expression) {
            LOG.info("Evaluating: " + member);

            if (!member.canFollow(previousMember)) {
                throw new InvalidInfixEquationException(member + " cannot follow " + previousMember);
            }

            LOG.debug("Current expression queue: " + postfixExpression);
            LOG.debug("Current operator stack: " + operatorStack);

            if (member.isOperand()) {
                LOG.debug("member is operand, adding to expression");

                postfixExpression.add(member);
            } else if (member.isLeftParenthesis()) {
                LOG.debug("member is left parenthesis, adding to operator stack");

                LeftParenthesis leftParenthesis = (LeftParenthesis) member;
                operatorStack.add(leftParenthesis);
            } else if (member.isRightParenthesis()) {
                LOG.debug("member is right parenthesis");
                RightParenthesis rightParenthesis = (RightParenthesis) member;

                boolean reachedEnd = false;
                while (!operatorStack.isEmpty()) {
                    LOG.debug("Evaluating: " + operatorStack.peekLast());

                    if (operatorStack.peekLast().isLeftParenthesis()) {
                        LOG.debug("Encountered left parenthesis, discarding it from operator stack");
                        operatorStack.removeLast();
                        reachedEnd = true;
                        break;
                    } else {
                        LOG.debug("Popping top of operand stack to expression");
                        postfixExpression.add(operatorStack.removeLast());
                    }
                }
                
                if(!reachedEnd) {
                    throw new NonMatchingParenthesisException("Closing parenthesis was not opened");
                }
            } else if (member.isOperator()) {
                LOG.debug("member is operator");

                Operator op = (Operator) member;

                while (!operatorStack.isEmpty()) {
                    LOG.debug("Comparing operand to " + operatorStack.peekLast());

                    if (op.canFollowOnOperatorStack(operatorStack.peekLast())) {
                        LOG.debug("Can follow");
                        break;
                    } else {
                        LOG.debug("Can't follow, popping top of operator stack to expression");
                        postfixExpression.add(operatorStack.removeLast());
                    }
                }

                LOG.debug("Adding operand to operator stack");
                operatorStack.add(op);
            } else {
                throw new InvalidInfixEquationException(member + " was not an operand or operator");
            }

            previousMember = member;

            LOG.debug("New expression queue: " + postfixExpression);
            LOG.debug("New operator stack: " + operatorStack);

        }

        while (!operatorStack.isEmpty()) {
            if (!operatorStack.peekLast().isLeftParenthesis()) {
                LOG.debug("Adding " + operatorStack.peekLast() + " to expression");
                postfixExpression.add(operatorStack.removeLast());
            } else {
                throw new NonMatchingParenthesisException("An opening parenthesis was not closed");
            }
        }
        this.validatePostfix(postfixExpression);
        LOG.info("Final expression: " + postfixExpression.toString());
        
        return postfixExpression;
    }
    
    private void validatePostfix(Queue<ExpressionMember> expression) {
        ExpressionMember[] expressionArray = {};
        expressionArray = expression.toArray(expressionArray);
        
        if(expressionArray.length == 0) {
            throw new InvalidInfixEquationException("Equation was empty");
        } else if (expressionArray.length == 1 ) {
            if(expressionArray[0].isOperand()) {
                return;
            } else {
            throw new InvalidInfixEquationException("Equation had one member, but it was not an operand");
            }
        }
        
        int operandCount = 0;
        int operatorCount = 0;
        
        for(ExpressionMember member: expressionArray) {
            if(member.isOperand()) {
                operandCount++;
            } else if(member.isOperator()) {
                operatorCount++;
            } else {
            throw new InvalidInfixEquationException("Postfix result did not consist of only operators and operands");
            }
        }
        
        if(operandCount != operatorCount + 1) {
            throw new InvalidInfixEquationException("Number of operators did not match number of operands");
        }
        
        if(!(expressionArray[0].isOperand() && expressionArray[1].isOperand())) {
            throw new InvalidInfixEquationException("Postfix result did not start with 2 numbers");
        }
        
        if(!expressionArray[expressionArray.length-1].isOperator()) {
            throw new InvalidInfixEquationException("Postfix result did not end with an operator");
        }
    }
    
}
