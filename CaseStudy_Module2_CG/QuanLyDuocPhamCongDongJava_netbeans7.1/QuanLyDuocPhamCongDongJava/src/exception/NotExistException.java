package exception;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class NotExistException extends Exception {

    /**
     * Creates a new instance of
     * <code>ExistException</code> without detail message.
     */
    public NotExistException() {
    }

    /**
     * Constructs an instance of
     * <code>ExistException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotExistException(String msg) {
        super(msg);
    }
}
