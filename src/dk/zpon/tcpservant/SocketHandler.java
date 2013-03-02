package dk.zpon.tcpservant;

import java.io.IOException;
import java.net.ServerSocket;

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

	@Override
	public void close() {
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
