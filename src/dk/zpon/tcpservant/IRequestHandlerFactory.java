package dk.zpon.tcpservant;

import java.net.InetAddress;
import java.util.List;

public interface IRequestHandlerFactory {

	public List<IRequestHandler> getRequestHandlers(InetAddress connector);
}
