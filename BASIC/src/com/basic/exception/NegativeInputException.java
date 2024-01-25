package com.basic.exception;

/**
 * Activated when the input is not in positive
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class NegativeInputException extends Exception{

    /**
     * Activates the Exception
     * @param message The message to shown as an error
     */
    public NegativeInputException (String message){
        super(message);
    }
}
