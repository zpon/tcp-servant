package dk.zpon.tcpservant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

public interface ISocketWrapper {

	InetAddress getInetAddress();

	InputStream getInputStream() throws IOException;

	OutputStream getOutputStream() throws IOException;

}
