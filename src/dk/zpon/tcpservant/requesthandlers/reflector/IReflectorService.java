package dk.zpon.tcpservant.requesthandlers.reflector;

import dk.zpon.tcpservant.UnhandledRequest;

/**
 * The service which is exposed through the {@link ReflectorRequestHandler}.
 * 
 * Methods in this class will be called when their name matches the value
 * returned by calling {@link IRequestObject#getRequestServiceMethodName()}. The
 * {@link IRequestObject} is generated by {@link IReflectorSerializer} when a
 * new request is received. The {@link IRequestObject} is passed to the methods
 * as argument.
 * 
 * @author sjuul
 * 
 */
public interface IReflectorService {

	/**
	 * This method is called if no method was matched with the result of
	 * {@link IRequestObject#getRequestServiceMethodName()}. If you do not want
	 * to handle the request in this object, just throw a
	 * {@link UnhandledRequest} exception.
	 * 
	 * @param request
	 * @return
	 */
	public IResponseObject unknownRequest(IRequestObject request) throws UnhandledRequest;
}