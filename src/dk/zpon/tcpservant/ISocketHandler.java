package dk.zpon.tcpservant;

import java.io.IOException;

public interface ISocketHandler {
	public ISocketWrapper waitForConnection() throws IOException;

	/**
	 * Close socket
	 */
	public void close();
}
