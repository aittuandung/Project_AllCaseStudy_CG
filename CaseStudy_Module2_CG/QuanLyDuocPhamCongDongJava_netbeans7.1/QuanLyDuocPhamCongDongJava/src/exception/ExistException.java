package exception;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ExistException extends Exception {

    /**
     * Creates a new instance of
     * <code>ExistException</code> without detail message.
     */
    public ExistException() {
    }

    /**
     * Constructs an instance of
     * <code>ExistException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExistException(String msg) {
        super(msg);
    }
}
