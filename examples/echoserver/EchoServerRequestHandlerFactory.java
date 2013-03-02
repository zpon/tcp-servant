package echoserver;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.IRequestHandlerFactory;
import dk.zpon.tcpservant.requesthandlers.filter.FilterRequestHandler;

public class EchoServerRequestHandlerFactory implements IRequestHandlerFactory {

	@Override
	public List<IRequestHandler> getRequestHandlers(InetAddress connector) {
		// This method is in charge of returning a list of IRequestHandlers
		// which will handle request from the client. This method is called each
		// time a new client connects to the socket.
		
		// Create empty list of request handlers
		List<IRequestHandler> requestHandlers = new LinkedList<IRequestHandler>();

		// Create an FilterRequestHandler instance
		FilterRequestHandler filterRequestHandler = new FilterRequestHandler();
		// Add our EchoFilter
		filterRequestHandler.addRequestFilter(new EchoFilter());

		requestHandlers.add(filterRequestHandler);

		return requestHandlers;
	}

}
