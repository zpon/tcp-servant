package echoserver;

import java.io.IOException;

import dk.zpon.tcpservant.Servant;

public class EchoServer {
	public static void main(String[] args) {

		Servant servant = null;
		try {
			// Create a new instance of the Servant class with the
			// EchoServerRequestHandlerFactory and port number as argument
			servant = new Servant(new EchoServerRequestHandlerFactory(), 3355);
			servant.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
