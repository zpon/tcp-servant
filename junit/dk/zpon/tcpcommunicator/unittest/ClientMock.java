package dk.zpon.tcpcommunicator.unittest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;

public class ClientMock {
	private int port;
	private Socket client;
	private BufferedReader in;
	private BufferedWriter out;

	public ClientMock(int port) throws UnknownHostException, IOException {
		this.port = port;

		client = new Socket("localhost", port);
		out = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}

	public String writeToHostAndWaitForResponse(String outString,
			int timeoutSeconds) throws IOException {
		out.write(outString);
		out.flush();

		CharBuffer buff = CharBuffer.allocate(16);
		StringBuffer buffer = new StringBuffer();

		while (timeoutSeconds > 0) {
			if (in.ready()) {
				while (in.ready()) {
					in.read(buff);
					buffer.append(buff.flip());
				}
				break;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			timeoutSeconds--;
		}

		return buffer.toString();
	}

	public void close() throws IOException {
		if (this.client != null) {
			this.client.close();
		}
	}

}
