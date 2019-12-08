package com.yasseensemlali.calculator.conversion.equationmembers.exceptions;

/** An expression contained an invalid character
 * @author Yasseen
 *
 */
public class InvalidExpressionMemberException extends RuntimeException{

	public InvalidExpressionMemberException(String msg) {
		super(msg);
	}

}
