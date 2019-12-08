/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasseensemlali.calculator;

import com.yasseensemlali.calculator.conversion.PostfixEvaluator;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yasseen
 */
@RunWith(value = Parameterized.class)
public class ValidExpressionTest {
    
    private static PostfixEvaluator evaluator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        return Arrays.asList(new Object[][]{
            { new LinkedList<String>(Arrays.asList("1".split(" "))), 1.0},
            {new LinkedList<String>(Arrays.asList("-1 + -2".split(" "))), -3.0}, 
            {new LinkedList<String>(Arrays.asList("5 * 9 - 6 * -2".split(" "))), 57.0}, 
            {new LinkedList<String>(Arrays.asList("2.5 * -1 ( 3.5 + 5 * 2 ) -1".split(" "))), 33.75}, 
            {new LinkedList<String>(Arrays.asList("( 5.5 - 9 * 4 + ( 7 * 2.7 - 3 ) 6 + 1 ) - 2 ( 3.8 + 9 ) 2".split(" "))), 14.700}, 
            {new LinkedList<String>(Arrays.asList("-1 + 2 ( 3 / 4 + 2 - 1 ( 6 + 9 ) / 5 ) 5".split(" "))), -3.5}
        //{"".split(" "), ""},
        });
    }
    private final Queue<String> expression;
    private final double expectedResult;

    public ValidExpressionTest(Queue<String> expression, double expectedResult) {
        this.expression = expression;
        this.expectedResult = expectedResult;
    }

    @BeforeClass
    public static void setUp() {
        evaluator = new PostfixEvaluator();
    }

    @Test
    public void testResult() {
        String result = this.evaluator.evaluatePostfix(this.expression);
        Assert.assertEquals(this.expectedResult, Double.parseDouble(result), 0.001);
    }
    
}
