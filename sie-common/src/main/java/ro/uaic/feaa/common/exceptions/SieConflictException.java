package ro.uaic.feaa.common.exceptions;

import java.io.Serializable;

/**
 * Created by Claudiu on 1/13/2018.
 */
public class SieConflictException extends Exception implements Serializable {

    SieConflictException(String message) {
        super(message);
    }

}
