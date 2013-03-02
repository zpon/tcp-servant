package dk.zpon.tcpservant;

import java.io.IOException;

public interface ISocketHandler {
	public ISocketWrapper waitForConnection() throws IOException;
}
