package hu.durfi.wonders.action;

/**
 * Thrown when action is invalid in the current state of the game.
 * Created by pudi on 2016.02.28..
 */
public class InvalidActionException extends Exception {
    public InvalidActionException() {
    }

    public InvalidActionException(String s) {
        super(s);
    }

    public InvalidActionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidActionException(Throwable throwable) {
        super(throwable);
    }
}
