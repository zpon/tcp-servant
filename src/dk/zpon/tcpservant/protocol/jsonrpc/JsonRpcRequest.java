package dk.zpon.tcpservant.protocol.jsonrpc;

import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;

public class JsonRpcRequest implements IRequestObject {
	
	String jsonrpc;
	String method;
	Object params;
	int id;

	@Override
	public String getRequestHandlerMethodName() {
		return method;
	}
	
	public Object getParams() {
		return params;
	}
	
	public int getId() {
		return id;
	}
	
	public String getJsonrpc() {
		return jsonrpc;
	}

}
