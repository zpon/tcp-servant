package dk.zpon.tcpservant;

import java.io.IOException;
import java.net.ServerSocket;

public class Servant {

	private int port = 8123;
	private ServerSocket serverSocket;
	private IRequestHandlerFactory requestFactory;
	private ISocketHandler socketHandler;

	public Servant(IRequestHandlerFactory requestFactory, int port) throws IOException {
		this.requestFactory = requestFactory;
		this.port = port;
		socketHandler = new SocketHandler(this.port);
	}

	public void start() {
		try {

			// Wait for clients
			ISocketWrapper socket = socketHandler.waitForConnection();

			// Create connection object for socket
			Connection connection = new Connection(socket,
					requestFactory.getRequestHandlers(socket.getInetAddress()));

			// TODO this should be threaded
			connection.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSocketHandler(ISocketHandler newSocketHandler) {
		socketHandler = newSocketHandler;
	}
}
