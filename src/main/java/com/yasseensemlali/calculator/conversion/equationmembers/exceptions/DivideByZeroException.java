package com.yasseensemlali.calculator.conversion.equationmembers.exceptions;

/** A number was divided by 0
 * @author Yasseen
 *
 */
public class DivideByZeroException extends RuntimeException{

	public DivideByZeroException(String msg) {
		super(msg);
	}

}
