package com.yasseensemlali.calculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import com.yasseensemlali.calculator.conversion.InfixConverter;

class PostfixTest {

	@Test
	void test() {
		InfixConverter c = new InfixConverter();
		String e = "5 + 6 * 6";
		String[] s = e.split(" ");
		Queue<String> q = new LinkedList<String>(Arrays.asList(s));
		
		c.getPostfixExpression(q);
	}

}
