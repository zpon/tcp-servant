package dk.zpon.tcpservant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.util.List;

public class Connection {

	private ISocketWrapper socket;
	private BufferedReader in;
	private BufferedWriter out;
	private List<IRequestHandler> handlers;
	private boolean stop;
	private boolean printExceptions;

	public Connection(ISocketWrapper socket, List<IRequestHandler> handlers)
			throws IOException {
		this.socket = socket;
		this.handlers = handlers;

		this.in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		this.out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	public void start() {
		try {
			StringBuilder inputData = new StringBuilder();
			CharBuffer charBuffer = CharBuffer.allocate(16);

			while (!shouldStop()) {

				// Read data from input stream
				while (in.ready()) {
					in.read(charBuffer);
					inputData.append(charBuffer.flip());
				}

				// Find handler of input
				if (inputData.length() > 0) {
					for (IRequestHandler handler : handlers) {
						try {
							String clone = new String(inputData.toString());
							String response = handler.handleRequest(clone);
							out.write(response);
							out.flush();
							break;
						} catch (UnhandledRequest e) {
							if(printExceptions) {
								e.printStackTrace();
							}
							// Else ignore
						} catch (Throwable e) {
							if(printExceptions) {
								e.printStackTrace();
							}
							// Else ignore
						}
					}

					inputData = new StringBuilder();
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// Ignore
				}
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private synchronized boolean shouldStop() {
		return stop;
	}

	public synchronized void stop() {
		this.stop = true;
	}

	/**
	 * This method is useful for debugging {@link IRequestHandler}s. It prints
	 * exceptions thrown when calling
	 * {@link IRequestHandler#handleRequest(String)}
	 * 
	 * @param printExceptions
	 */
	public void printExceptionsOfHandlers(boolean printExceptions) {
		this.printExceptions = printExceptions;
	}
}
