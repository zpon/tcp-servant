package dk.zpon.tcpservant;

import java.net.InetAddress;
import java.util.List;

public interface IRequestHandlerFactory {

	/**
	 * This method is in charge of returning a list of IRequestHandlers which
	 * will handle request from the client. This method is called each time a
	 * new client connects to the socket.
	 * 
	 * @param connector
	 * @return
	 */
	public List<IRequestHandler> getRequestHandlers(InetAddress connector);
}
