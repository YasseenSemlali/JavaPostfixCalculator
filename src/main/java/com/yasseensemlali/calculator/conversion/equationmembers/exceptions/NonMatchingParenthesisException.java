package com.yasseensemlali.calculator.conversion.equationmembers.exceptions;

/** There is an uneven number of left and right parenthesis 
 * @author Yasseen
 *
 */
public class NonMatchingParenthesisException extends RuntimeException{

	public NonMatchingParenthesisException(String msg) {
		super(msg);
	}

}
