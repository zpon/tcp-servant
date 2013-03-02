package calculatorservice;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.IRequestHandlerFactory;
import dk.zpon.tcpservant.requesthandlers.reflector.ReflectorRequestHandler;

public class CalculatorRequestHandlerFactory implements IRequestHandlerFactory {

	@Override
	public List<IRequestHandler> getRequestHandlers(InetAddress connector) {
		// Create list of request handlers
		List<IRequestHandler> handlers = new LinkedList<IRequestHandler>();
		
		// Add reflector request handler
		handlers.add(new ReflectorRequestHandler(new CalculatorSerializer(), new CalculatorService()));
		
		return handlers;
	}

}
