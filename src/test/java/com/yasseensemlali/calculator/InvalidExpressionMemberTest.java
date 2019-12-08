/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasseensemlali.calculator;

import com.yasseensemlali.calculator.conversion.PostfixEvaluator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidExpressionMemberException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yasseen
 */
@RunWith(value = Parameterized.class)
public class InvalidExpressionMemberTest {
    
    private static PostfixEvaluator evaluator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        return Arrays.asList(new Object[][]{{new LinkedList<String>(Arrays.asList("1 // 2".split(" ")))}, {new LinkedList<String>(Arrays.asList("(1 + 2)".split(" ")))}, {new LinkedList<String>(Arrays.asList("a".split(" ")))}, {new LinkedList<String>(Arrays.asList("1 + x2 ( 3 / 4 + 2 - 1 ( 6 + 9 ) / 5 ) 5 +".split(" ")))}});
    }
    private final Queue<String> expression;

    public InvalidExpressionMemberTest(Queue<String> expression) {
        this.expression = expression;
    }

    @BeforeClass
    public static void setUp() {
        evaluator = new PostfixEvaluator();
    }

    @Test(expected = InvalidExpressionMemberException.class)
    public void testResult() {
        this.evaluator.evaluatePostfix(this.expression);
    }
    
}
