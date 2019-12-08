/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasseensemlali.calculator;

import com.yasseensemlali.calculator.conversion.PostfixEvaluator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.DivideByZeroException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Yasseen
 */
@RunWith(value = Parameterized.class)
public class DivideByZeroTest {
    
    private PostfixEvaluator evaluator;

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        return Arrays.asList(new Object[][]{{new LinkedList<String>(Arrays.asList("1 / 0".split(" ")))}, {new LinkedList<String>(Arrays.asList("0 / 0".split(" ")))}, {new LinkedList<String>(Arrays.asList("1 / ( 2 - 2 )".split(" ")))}, {new LinkedList<String>(Arrays.asList("( 1 + 4 * 5 - 9 ) / ( 3 * 0 )".split(" ")))}});
    }
    private final Queue<String> expression;

    public DivideByZeroTest(Queue<String> expression) {
        this.expression = expression;
    }

    @Before
    public void setUp() {
        evaluator = new PostfixEvaluator();
    }

    @Test(expected = DivideByZeroException.class)
    public void testResult() {
        this.evaluator.evaluatePostfix(this.expression);
    }
    
}
