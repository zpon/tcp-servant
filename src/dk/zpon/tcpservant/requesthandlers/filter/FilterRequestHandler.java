package dk.zpon.tcpservant.requesthandlers.filter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.UnhandledRequest;

public class FilterRequestHandler implements IRequestHandler {

	private List<IRequestFilter> filters;

	public FilterRequestHandler(List<IRequestFilter> filters) {
		this.filters = filters;
	}

	/**
	 * This constructor has no filters as input, thus you need to call
	 * {@link #addRequestFilter(IRequestFilter)} and add a filter before it is
	 * used.
	 */
	public FilterRequestHandler() {
		filters = new CopyOnWriteArrayList<IRequestFilter>();
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

	/**
	 * Add a request filter to the list of filters
	 * @param filter
	 */
	public synchronized void addRequestFilter(IRequestFilter filter) {
		filters.add(filter);
	}
}
