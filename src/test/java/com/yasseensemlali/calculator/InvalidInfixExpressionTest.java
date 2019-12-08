/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasseensemlali.calculator;

import com.yasseensemlali.calculator.conversion.PostfixEvaluator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidInfixEquationException;
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
public class InvalidInfixExpressionTest {
    
    private static PostfixEvaluator evaluator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        return Arrays.asList(new Object[][]{{new LinkedList<String>()}, {new LinkedList<String>(Arrays.asList("1 + + 2".split(" ")))}, {new LinkedList<String>(Arrays.asList("* 1 + 2".split(" ")))}, {new LinkedList<String>(Arrays.asList("( 5.5 - 9 * 4 + ( 7 * 2.7 - 3 ) 6 + 1 ) - 2 ( 3.8 + 9 ) 2 +".split(" ")))}, {new LinkedList<String>(Arrays.asList("( + 1 + 2 )".split(" ")))}});
    }
    private final Queue<String> expression;

    public InvalidInfixExpressionTest(Queue<String> expression) {
        this.expression = expression;
    }

    @BeforeClass
    public static void setUp() {
        evaluator = new PostfixEvaluator();
    }

    @Test(expected = InvalidInfixEquationException.class)
    public void testResult() {
        this.evaluator.evaluatePostfix(this.expression);
    }
    
}
