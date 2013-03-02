package dk.zpon.tcpservant.requesthandlers.reflector;

/**
 * Object which should encapsulate the request
 * 
 * @author sjuul
 */
public interface IRequestObject {
	/**
	 * Return the name of method which should handle this request
	 * 
	 * @return
	 */
	public String getRequestServiceMethodName();
}
