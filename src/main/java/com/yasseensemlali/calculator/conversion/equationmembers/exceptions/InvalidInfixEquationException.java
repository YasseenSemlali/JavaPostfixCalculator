package com.yasseensemlali.calculator.conversion.equationmembers.exceptions;

/** An infix equation was not valid 
 * @author Yasseen
 *
 */
public class InvalidInfixEquationException extends RuntimeException{

	public InvalidInfixEquationException(String msg) {
		super(msg);
	}

}
