package com.yasseensemlali.calculator;

import java.util.Arrays;
import java.util.Queue;

import org.junit.Test;

import com.yasseensemlali.calculator.conversion.PostfixEvaluator;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.DivideByZeroException;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidExpressionMemberException;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidInfixEquationException;
import com.yasseensemlali.calculator.conversion.equationmembers.exceptions.InvalidPostfixEquationException;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class PostfixTest {
    
    
    @RunWith(Parameterized.class)
    public static class ValidExpressionTest {
        private static PostfixEvaluator evaluator;

        @Parameters
        public static Collection<Object[]> data() throws ParseException {
            return Arrays.asList(new Object[][]{
                {new LinkedList<String>(Arrays.asList("1".split(" "))) , 1.0},
                {new LinkedList<String>(Arrays.asList("1 + 2".split(" "))) , 3.0},
                {new LinkedList<String>(Arrays.asList("5 * 9 - 6 * 2".split(" "))), 33.0},
                {new LinkedList<String>(Arrays.asList("2.5 * 1 ( 3.5 + 5 * 2 ) 1".split(" "))), 33.75},
                {new LinkedList<String>(Arrays.asList("( 5.5 - 9 * 4 + ( 7 * 2.7 - 3 ) 6 + 1 ) - 2 ( 3.8 + 9 ) 2".split(" "))), 14.700},
                {new LinkedList<String>(Arrays.asList("1 + 2 ( 3 / 4 + 2 - 1 ( 6 + 9 ) / 5 ) 5".split(" "))), -1.5}
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

            assertEquals(this.expectedResult, Double.parseDouble(result), 0.001);
        }
    }
    
    @RunWith(Parameterized.class)
    //Infix expression contains an invalid character
    public static class InvalidExpressionMemberTest {
        private static PostfixEvaluator evaluator;

        @Parameters
        public static Collection<Object[]> data() throws ParseException {
            return Arrays.asList(new Object[][]{
                {new LinkedList<String>(Arrays.asList("1 // 2".split(" ")))},
                {new LinkedList<String>(Arrays.asList("(1 + 2)".split(" ")))},
                {new LinkedList<String>(Arrays.asList("a".split(" ")))},
                {new LinkedList<String>(Arrays.asList("1 + x2 ( 3 / 4 + 2 - 1 ( 6 + 9 ) / 5 ) 5 +".split(" ")))},
            //{"".split(" "), ""},
            });
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
    
    @RunWith(Parameterized.class)
    //Invix expression can't be converted to postfix
    public static class InvalidInfixExpressionTest {
        private static PostfixEvaluator evaluator;

        @Parameters
        public static Collection<Object[]> data() throws ParseException {
            return Arrays.asList(new Object[][]{
                {new LinkedList<String>()},
                {new LinkedList<String>(Arrays.asList("1 + + 2".split(" ")))},
                {new LinkedList<String>(Arrays.asList("* 1 + 2".split(" ")))},
                {new LinkedList<String>(Arrays.asList("( 5.5 - 9 * 4 + ( 7 * 2.7 - 3 ) 6 + 1 ) - 2 ( 3.8 + 9 ) 2 +".split(" ")))},
                {new LinkedList<String>(Arrays.asList("( + 1 + 2 )".split(" ")))},
            //{"".split(" "), ""},
            });
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
    
    @RunWith(Parameterized.class)
    //Invix expression can't be converted to postfix
    public static class DivideByZeroTest {
        private static PostfixEvaluator evaluator;

        @Parameters
        public static Collection<Object[]> data() throws ParseException {
            return Arrays.asList(new Object[][]{
                {new LinkedList<String>(Arrays.asList("1 / 0".split(" ")))},
                {new LinkedList<String>(Arrays.asList("0 / 0".split(" ")))},
                {new LinkedList<String>(Arrays.asList("1 / ( 2 - 2 )".split(" ")))},
                {new LinkedList<String>(Arrays.asList("( 1 + 4 * 5 - 9 ) / ( 3 * 0 )".split(" ")))},
                //{new LinkedList<String>(Arrays.asList(" ".split(" ")))}
            //{"".split(" "), ""},
            });
        }
        private final Queue<String> expression;

        public DivideByZeroTest(Queue<String> expression) {
            this.expression = expression;
        }

        @BeforeClass
        public static void setUp() {
            evaluator = new PostfixEvaluator();
        }

        @Test(expected = DivideByZeroException.class)
        public void testResult() {
            this.evaluator.evaluatePostfix(this.expression);
        }
    }
}
