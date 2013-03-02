package echoserver;

import dk.zpon.tcpservant.UnhandledRequest;
import dk.zpon.tcpservant.requesthandlers.filter.IRequestFilter;

public class EchoFilter implements IRequestFilter {

	@Override
	public String handleRequest(String request) throws UnhandledRequest {
		// Debugging info
		System.out.println("Recieved: " + request.trim());
		System.out.println("Responding: " + request.trim());
		
		// Since this a simple echo server, we just return the request
		return request;
	}

}
