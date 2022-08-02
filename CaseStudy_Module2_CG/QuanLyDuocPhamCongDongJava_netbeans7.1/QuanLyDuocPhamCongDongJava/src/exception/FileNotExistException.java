package exception;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class FileNotExistException extends Exception {

    /**
     * Creates a new instance of
     * <code>ExistException</code> without detail message.
     */
    public FileNotExistException() {
    }

    /**
     * Constructs an instance of
     * <code>ExistException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileNotExistException(String msg) {
        super(msg);
    }
}
