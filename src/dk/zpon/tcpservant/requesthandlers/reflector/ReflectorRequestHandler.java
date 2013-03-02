package dk.zpon.tcpservant.requesthandlers.reflector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dk.zpon.tcpservant.IRequestHandler;
import dk.zpon.tcpservant.UnhandledRequest;

public class ReflectorRequestHandler implements IRequestHandler {

	private IReflectorSerializer serializer;
	private IReflectorService handle;
	private Method[] serviceHandlerMethods;

	public ReflectorRequestHandler(IReflectorSerializer seriealizer,
			IReflectorService handler) {
		this.serializer = seriealizer;
		this.handle = handler;
		this.serviceHandlerMethods = handle.getClass().getMethods();
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
					result = (IResponseObject) method.invoke(handle,
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
			result = handle.unknownRequest(deserializeRequest);
		}

		return serializer.serializeResponse(result);
	}
}
