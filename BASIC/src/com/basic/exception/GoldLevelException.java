package com.basic.exception;

/**
 * Activated when the Gold Level input is not in the range
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class GoldLevelException extends Exception{

    /**
     * Activates the Exception
     * @param message The message to shown as an error
     */
    public GoldLevelException (String message){
        super(message);
    }
}
