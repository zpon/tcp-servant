package dk.zpon.tcpservant.requesthandlers.reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.UnhandledRequest;

public class ReflectorRequestHandler implements IRequestHandler {

	private IReflectorSerializer serializer;
	private IReflectorService service;
	private Method[] serviceHandlerMethods;

	public ReflectorRequestHandler(IReflectorSerializer seriealizer,
			IReflectorService service) {
		this.serializer = seriealizer;
		this.service = service;
		this.serviceHandlerMethods = service.getClass().getMethods();
	}

	public String handleRequest(String request) throws UnhandledRequest {
		IRequestObject deserializeRequest = serializer
				.deserializeRequest(request);

		IResponseObject result = null;
		boolean foundHandlerMethod = false;
		String methodName = deserializeRequest.getRequestServiceMethodName();

		for (Method method : serviceHandlerMethods) {
			if (method.getName().equals(methodName)
					&& IResponseObject.class.isAssignableFrom(method
							.getReturnType())) {
				try {
					foundHandlerMethod = true;
					result = (IResponseObject) method.invoke(service,
							deserializeRequest);
					break;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		// If method not found, call failover method
		if (!foundHandlerMethod) {
			result = service.unknownRequest(deserializeRequest);
		}

		return serializer.serializeResponse(result);
	}
}
