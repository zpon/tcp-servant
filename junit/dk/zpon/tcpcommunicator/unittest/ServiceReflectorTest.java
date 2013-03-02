package dk.zpon.tcpcommunicator.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dk.zpon.tcpservant.UnhandledRequest;
import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorSerializer;
import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorService;
import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;
import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;
import dk.zpon.tcpservant.requesthandlers.reflector.ReflectorRequestHandler;

public class ServiceReflectorTest {

	@Test
	public void testMethodCall() {
		IReflectorSerializer serviceSerializer = new MockServiceSerializer();

		IReflectorService serviceHandlerInterface = new MockServiceHandler();

		ReflectorRequestHandler reflector = new ReflectorRequestHandler(
				serviceSerializer, serviceHandlerInterface);

		try {
			String handleRequest;

			String methodName = "testMethod";
			handleRequest = reflector.handleRequest(methodName);
			assertEquals(methodName, handleRequest);

			String notImplementedMethod = "unknownMethodName";
			handleRequest = reflector.handleRequest(notImplementedMethod);
			assertEquals("unknown type", handleRequest);
		} catch (UnhandledRequest e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class MockServiceHandler implements IReflectorService {
		public IResponseObject testMethod(IRequestObject request) {
			return new MockResponse("testMethod");
		}

		@Override
		public IResponseObject unknownRequest(IRequestObject request) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class MockResponse implements IResponseObject {
		private String message;

		public MockResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	public class MockServiceSerializer implements IReflectorSerializer {

		@Override
		public String serializeResponse(IResponseObject response) {
			if (response instanceof MockResponse) {
				return ((MockResponse) response).getMessage();
			} else {
				return "unknown type";
			}
		}

		@Override
		public IRequestObject deserializeRequest(final String request) {
			// TODO Auto-generated method stub
			return new IRequestObject() {

				@Override
				public String getRequestServiceMethodName() {
					// TODO Auto-generated method stub
					return request;
				}
			};
		}
	}
}
