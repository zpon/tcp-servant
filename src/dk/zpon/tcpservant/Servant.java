package dk.zpon.tcpservant;

import java.io.IOException;

public class Servant {

	private int port;
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

				// Start connection
				startConnectionInThread(connection);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method starts the connection in a thread, to avoid it blocking
	 * 
	 * @param connection
	 */
	private void startConnectionInThread(final Connection connection) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				connection.start();
			}
		}).start();
	}

	private synchronized boolean shouldStop() {
		return stop;
	}

	public synchronized void stop() {
		if (socketHandler != null) {
			socketHandler.close();
		}
		this.stop = true;
	}

	public void setSocketHandler(ISocketHandler newSocketHandler) {
		socketHandler = newSocketHandler;
	}
}
