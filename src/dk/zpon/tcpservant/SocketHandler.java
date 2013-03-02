package dk.zpon.tcpservant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler implements ISocketHandler {
	
	private int port;
	private ServerSocket serverSocket;

	public SocketHandler(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(this.port);
	}

	@Override
	public ISocketWrapper waitForConnection() throws IOException {
		return new SocketWrapper(serverSocket.accept());
	}

}
