package dk.zpon.tcpservant.requesthandlers.filter;

import dk.zpon.tcpservant.UnhandledRequest;

public interface IRequestFilter {
	public String handleRequest(String request) throws UnhandledRequest;
}
