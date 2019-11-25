package com.yasseensemlali.calculator;

import java.util.Arrays;
import java.util.Queue;

import org.junit.Test;

import com.yasseensemlali.calculator.conversion.PostfixEvaluator;
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
                {new LinkedList<String>(Arrays.asList("1 + 2".split(" "))) , "3.0"},
                {new LinkedList<String>(Arrays.asList("5 * 9 - 6 * 2".split(" "))), "33.0"},
                {new LinkedList<String>(Arrays.asList("2.5 * 1 ( 3.5 + 5 * 2 ) 1".split(" "))), "33.75"},
                {new LinkedList<String>(Arrays.asList("( 5.5 - 9 * 4 + ( 7 * 2.7 - 3 ) 6 + 1 ) - 2 ( 3.8 + 9 ) 2".split(" "))), "14.7"},
                {new LinkedList<String>(Arrays.asList("1 + 2 ( 3 / 4 + 2 - 1 ( 6 + 9 ) / 5 ) 5".split(" "))), "-1.5"}
            //{"".split(" "), ""},
            });
        }
        private final Queue<String> expression;
        private final String expectedResult;

        public ValidExpressionTest(Queue<String> expression, String expectedResult) {
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

            assertEquals(this.expectedResult, result);
        }
    }
}
