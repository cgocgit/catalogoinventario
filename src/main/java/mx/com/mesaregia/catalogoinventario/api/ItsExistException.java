package mx.com.mesaregia.catalogoinventario.api;

/**
 *
 * Excepci&oacute;n personalizada para un elemento existente.
 * 
 * @author Carlos Gilberto Olvera Casanova
 * 
 *
 * @version 1.0.0 
 */
public class ItsExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2651147417064590992L;

	/**
	 * 
	 */
	public ItsExistException() {
	}

	/**
	 * @param message
	 */
	public ItsExistException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ItsExistException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ItsExistException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ItsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
