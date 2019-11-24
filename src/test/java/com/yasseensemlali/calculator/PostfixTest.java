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
		String e = "1 * ( 2 + 3 ) * 4";
                 //e = "1 * 2 + 3 * 4";
		String[] s = e.split(" ");
		Queue<String> q = new LinkedList<String>(Arrays.asList(s));
		try{
                    c.getPostfixExpression(q);
                } catch(Exception ex) {
                    for(StackTraceElement i: ex.getStackTrace()) {
                        System.err.println(i);
                    }
                }
	}

}
