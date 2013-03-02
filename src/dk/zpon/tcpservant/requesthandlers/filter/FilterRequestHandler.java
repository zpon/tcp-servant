package dk.zpon.tcpservant.requesthandlers.filter;

import java.util.List;

import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.UnhandledRequest;

public class FilterRequestHandler implements IRequestHandler {

	private List<IRequestFilter> filters;

	public FilterRequestHandler(List<IRequestFilter> filters) {
		this.filters = filters;
	}

	@Override
	public String handleRequest(String request) throws UnhandledRequest {
		String result = null;
		for (IRequestFilter filter : filters) {
			try {
				result = filter.handleRequest(request);
				return result;
			} catch (UnhandledRequest e) {
				// Ignore
			}
		}

		throw new UnhandledRequest();
	}

}
