package calculatorservice;

import java.io.IOException;

import dk.zpon.tcpservant.Servant;

public class CalculatorServer {

	public static void main(String[] args) {
		try {
			// Create the servant object with the
			// CalculatorRequestHandlerFactory as argument, as well as the port number
			Servant servant = new Servant(
					new CalculatorRequestHandlerFactory(), 3355);
			
			// Start the server (will block until servant.close() is called from another thread)
			servant.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
