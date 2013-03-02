package dk.zpon.tcpservant;

public interface IRequestHandler {
	public String handleRequest(String request) throws UnhandledRequest;
}