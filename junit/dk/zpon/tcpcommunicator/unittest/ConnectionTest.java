package dk.zpon.tcpcommunicator.unittest;

import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import dk.zpon.tcpcommunicator.unittest.ConnectionTest.ReflectionService.ResponseObject;
import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.IRequestHandlerFactory;
import dk.zpon.tcpservant.Servant;
import dk.zpon.tcpservant.UnhandledRequest;
import dk.zpon.tcpservant.requesthandlers.filter.FilterRequestHandler;
import dk.zpon.tcpservant.requesthandlers.filter.IRequestFilter;
import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorSerializer;
import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorService;
import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;
import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;
import dk.zpon.tcpservant.requesthandlers.reflector.ReflectorRequestHandler;

public class ConnectionTest {

	@Test
	public void testRequestHandlerFactory() {
		int port = 12346;

		RequstHandlerFactoryMock requstHandlerFactoryMock = new RequstHandlerFactoryMock();
		try {
			final Servant servant = new Servant(requstHandlerFactoryMock, port);
			new Thread(new Runnable() {
				@Override
				public void run() {
					servant.start();
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// NOTE: Do ease debugging, the timeout values can be increased, else
		// you will only have 3 seconds from the request has been made
		ClientMock clientMock = null;
		try {
			clientMock = new ClientMock(port);

			String writeToHostAndWaitForResponse = clientMock
					.writeToHostAndWaitForResponse("filter", 3);
			Assert.assertEquals(writeToHostAndWaitForResponse, "YES");

			writeToHostAndWaitForResponse = clientMock
					.writeToHostAndWaitForResponse("reflection", 3);
			Assert.assertEquals(writeToHostAndWaitForResponse, "REFLECT");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (clientMock != null) {
				try {
					clientMock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class RequstHandlerFactoryMock implements IRequestHandlerFactory {

		@Override
		public List<IRequestHandler> getRequestHandlers(InetAddress connector) {
			List<IRequestFilter> filters = new LinkedList<IRequestFilter>();
			filters.add(new RequestFilter());
			FilterRequestHandler request = new FilterRequestHandler(filters);
			List<IRequestHandler> requestHandlers = new LinkedList<IRequestHandler>();
			requestHandlers.add(request);

			ReflectionSerializer reflectionSerializer = new ReflectionSerializer();
			ReflectionService reflectionService = new ReflectionService();
			ReflectorRequestHandler reflectorRequestHandler = new ReflectorRequestHandler(
					reflectionSerializer, reflectionService);
			requestHandlers.add(reflectorRequestHandler);

			return requestHandlers;
		}

	}

	public class ReflectionSerializer implements IReflectorSerializer {

		@Override
		public IRequestObject deserializeRequest(final String request) {
			if (request.equals("reflection")) {
				return new IRequestObject() {

					@Override
					public String getRequestServiceMethodName() {
						return request;
					}
				};
			} else {
				return null;
			}
		}

		@Override
		public String serializeResponse(IResponseObject response) {
			if (response instanceof ResponseObject) {
				return ((ResponseObject) response).getResponse();
			}
			return null;
		}

	}

	public class ReflectionService implements IReflectorService {

		public IResponseObject reflection(IRequestObject obj) {
			return new ResponseObject("REFLECT");
		}

		@Override
		public IResponseObject unknownRequest(IRequestObject request) {
			// TODO Auto-generated method stub
			return null;
		}

		public class ResponseObject implements IResponseObject {
			private String response;

			public ResponseObject(String response) {
				this.response = response;
			}

			public String getResponse() {
				return response;
			}
		}

	}

	public class RequestFilter implements IRequestFilter {

		@Override
		public String handleRequest(String request) throws UnhandledRequest {
			if (request.equals("filter")) {
				return "YES";
			}
			throw new UnhandledRequest();
		}

	}
}
