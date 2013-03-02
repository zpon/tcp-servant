package dk.zpon.tcpservant;

import java.io.IOException;
import java.net.ServerSocket;

public class Servant {

	private int port = 8123;
	private ServerSocket serverSocket;
	private IRequestHandlerFactory requestFactory;
	private ISocketHandler socketHandler;
	private boolean stop;

	public Servant(IRequestHandlerFactory requestFactory, int port)
			throws IOException {
		this.requestFactory = requestFactory;
		this.port = port;
		socketHandler = new SocketHandler(this.port);
	}

	public void start() {
		while (!shouldStop()) {
			try {

				// Wait for clients
				ISocketWrapper socket = socketHandler.waitForConnection();

				// Create connection object for socket
				Connection connection = new Connection(socket,
						requestFactory.getRequestHandlers(socket
								.getInetAddress()));

				// TODO this should be threaded
				connection.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized boolean shouldStop() {
		return stop;
	}
	
	public synchronized void stop() {
		this.stop = true;
	}

	public void setSocketHandler(ISocketHandler newSocketHandler) {
		socketHandler = newSocketHandler;
	}
}
