package com.yasseensemlali.calculator.conversion.equationmembers.exceptions;

/** A postfix equation was invalid
 * @author Yasseen
 *
 */
public class InvalidPostfixEquationException extends RuntimeException{

	public InvalidPostfixEquationException(String msg) {
		super(msg);
	}

}
