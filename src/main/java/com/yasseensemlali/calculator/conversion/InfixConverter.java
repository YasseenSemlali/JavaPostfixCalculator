package com.yasseensemlali.calculator.conversion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.yasseensemlali.calculator.conversion.equationmembers.ExpressionMember;
import com.yasseensemlali.calculator.conversion.equationmembers.Operator;

public class InfixConverter {
	
	public Queue<ExpressionMember> getPostfixExpression(Queue<ExpressionMember> expression) {
		Deque<Operator> operatorStack = new ArrayDeque<Operator>();
		Queue<ExpressionMember> postfixExpression = new LinkedList<ExpressionMember>();
		return null;
	}
}
