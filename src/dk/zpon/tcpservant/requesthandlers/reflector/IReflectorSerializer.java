package dk.zpon.tcpservant.requesthandlers.reflector;


/**
 * Implement methods for serializing and de-serializing data
 * 
 * @author sjuul
 */
public interface IReflectorSerializer {
	public IRequestObject deserializeRequest(String request);

	public String serializeResponse(IResponseObject response);
}