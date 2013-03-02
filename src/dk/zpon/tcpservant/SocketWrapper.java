package dk.zpon.tcpservant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketWrapper implements ISocketWrapper {

	private Socket socket;

	public SocketWrapper(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public InetAddress getInetAddress() {
		return socket.getInetAddress();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return socket.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}
}
