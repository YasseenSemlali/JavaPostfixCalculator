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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(value = Parameterized.class)
public class ValidExpressionTest {
    
    private PostfixEvaluator evaluator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        return Arrays.asList(new Object[][]{
            {new LinkedList<String>(Arrays.asList("1".split(" "))), 1.0},
            {new LinkedList<String>(Arrays.asList("-1 + -2".split(" "))), -3.0}, 
            {new LinkedList<String>(Arrays.asList("5 * 9 - 6 * -2".split(" "))), 57.0}, 
            {new LinkedList<String>(Arrays.asList("( 5 + 2 ) 2".split(" "))), 14.0},
            {new LinkedList<String>(Arrays.asList("-1 * 50".split(" "))), -50.0},
            {new LinkedList<String>(Arrays.asList("-1 * 3 + 4 - -8".split(" "))), 9.0},
            {new LinkedList<String>(Arrays.asList("( 1 + ( 4 * 4 ( 4 - 2 ) -1 ) )".split(" "))), -31.0},
            {new LinkedList<String>(Arrays.asList("76 - 9 * 5 + 1".split(" "))), 32.0},
            {new LinkedList<String>(Arrays.asList("5 / 5".split(" "))), 1.0},
            {new LinkedList<String>(Arrays.asList("1 + 1 + 1 + 1 + 1 + 1 + 1 + 1".split(" "))), 8.0},
            {new LinkedList<String>(Arrays.asList("2 / 5".split(" "))), 0.4},
            {new LinkedList<String>(Arrays.asList("( 4 + 5 ) / ( 4 / -2 )".split(" "))), -4.5},
            {new LinkedList<String>(Arrays.asList("0.5 + -8.6".split(" "))), -8.1},
            {new LinkedList<String>(Arrays.asList("4.6 / 0.1".split(" "))), 46},
            {new LinkedList<String>(Arrays.asList("100000 + 6570".split(" "))), 106570},
            {new LinkedList<String>(Arrays.asList("10.0001 * 43".split(" "))), 430.0043},
            {new LinkedList<String>(Arrays.asList("2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 3".split(" "))), 3072},
            {new LinkedList<String>(Arrays.asList("0.01 - 0.2".split(" "))), -0.19},
            {new LinkedList<String>(Arrays.asList("1 ( 2 + 4 ) -1 ( 3 / 2 )".split(" "))), -9.0},
            {new LinkedList<String>(Arrays.asList("93 / 1000".split(" "))), 0.093},
            {new LinkedList<String>(Arrays.asList("80 / 1 / 1 / 1 / 1 - 80".split(" "))), 0.0},
            {new LinkedList<String>(Arrays.asList("1 + 8 - ( 5 * 6 ( 4 + 1 ( -1 ) ) )".split(" "))), -81},
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

    @Before
    public void setUp() {
        evaluator = new PostfixEvaluator();
    }

    @Test
    public void testResult() {
        String result = this.evaluator.evaluatePostfix(this.expression);
        Assert.assertEquals(this.expectedResult, Double.parseDouble(result), 0.001);
    }
    
}
