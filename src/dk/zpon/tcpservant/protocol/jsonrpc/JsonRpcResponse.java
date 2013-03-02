package dk.zpon.tcpservant.protocol.jsonrpc;

import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;

public class JsonRpcResponse implements IResponseObject {
	String jsonrpc;
	Object result;
	int id;
	
	public JsonRpcResponse(String jsonrpc, Object result, int id) {
		this.jsonrpc = jsonrpc;
		this.result = result;
		this.id = id;
	}
}
