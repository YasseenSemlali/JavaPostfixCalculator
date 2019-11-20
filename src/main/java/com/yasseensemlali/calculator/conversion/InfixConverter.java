package com.yasseensemlali.calculator.conversion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.InvalidExpressionMemberException;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;

public class InfixConverter {
	 private final static Logger LOG = LoggerFactory.getLogger(InfixConverter.class);

	 public Queue<ExpressionMember> getPostfixExpression(Queue<String> expression) {
		 Queue<ExpressionMember> infixExpression = new LinkedList<ExpressionMember>();
		 for(String member: expression) {
			 infixExpression.add(ExpressionMember.getFromString(member));
		 }
		 
		 return this.infixToPostfix(infixExpression);
	 }
	 
	private Queue<ExpressionMember> infixToPostfix(Queue<ExpressionMember> expression) {
		Deque<Operator> operatorStack = new ArrayDeque<Operator>();
		Queue<ExpressionMember> postfixExpression = new LinkedList<ExpressionMember>();

		ExpressionMember previousMember = null;
		for(ExpressionMember member: expression) {
			LOG.info("Evaluating: " + member);
			
			if(!member.canFollow(previousMember)) {
				throw new InvalidExpressionMemberException(member + " cannot follow " + previousMember);
			}
			
			LOG.debug("Current expression queue: " + postfixExpression);
			LOG.debug("Current operator stack: " + operatorStack);
			
			if(member.isOperand()) {
				LOG.debug("member is operand, adding to expression");
				postfixExpression.add(member);
			} else if (member.isOperator()) {
				LOG.debug("member is operator");
				Operator op = (Operator) member;

				while(!operatorStack.isEmpty()) {
					LOG.debug("Comparing operand to " + operatorStack.peek());
					
					if(op.compareTo(operatorStack.peekLast()) > 0) {
						LOG.debug("Have higher priority, popping top of operand stack to expression");
						postfixExpression.add(operatorStack.removeLast());
					} else {
						LOG.debug("Have lower priority");
						break;
					}
				}
				
				LOG.debug("Adding operand to operand stack");
				operatorStack.add(op);
			} else {
				throw new InvalidExpressionMemberException(member + " was not an operand or operator");
			}
			
			previousMember = member;
		}
		
		while(!operatorStack.isEmpty()) {
			LOG.debug("Adding " + operatorStack.peekLast() + " to expression");
			postfixExpression.add(operatorStack.removeLast());
		}

		LOG.info("Final expression: " + postfixExpression.toString());
		
		return postfixExpression;
	}
}
