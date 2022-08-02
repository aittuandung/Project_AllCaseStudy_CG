package exception;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class NoUnitIsSetException extends Exception {

    /**
     * Creates a new instance of
     * <code>ExistException</code> without detail message.
     */
    public NoUnitIsSetException() {
    }

    /**
     * Constructs an instance of
     * <code>ExistException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoUnitIsSetException(String msg) {
        super(msg);
    }
}
