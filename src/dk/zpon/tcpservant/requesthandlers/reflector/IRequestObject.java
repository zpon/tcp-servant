package dk.zpon.tcpservant.requesthandlers.reflector;

public interface IRequestObject {
	/**
	 * Returns the name of method which handles this request
	 * 
	 * @return
	 */
	public String getRequestHandlerMethodName();
}
