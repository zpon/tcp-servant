package dk.zpon.tcpservant.requesthandlers.reflector;

/**
 * Implement methods for serializing and deserializing data
 * 
 * @author sjuul
 */
public interface IReflectorSerializer {
	/**
	 * Deserialize a request into an object implementing the
	 * {@link IRequestObject} interface.
	 * <p>
	 * The returned object is used to lookup the method which can handle the
	 * request, by calling {@link IRequestObject#getRequestServiceMethodName()}.
	 * </p>
	 * 
	 * @param request
	 *            RequestObject describing the request
	 * @return
	 */
	public IRequestObject deserializeRequest(String request);

	/**
	 * Serialize the response back into text
	 * 
	 * @param response
	 *            Serialized response
	 * @return
	 */
	public String serializeResponse(IResponseObject response);
}