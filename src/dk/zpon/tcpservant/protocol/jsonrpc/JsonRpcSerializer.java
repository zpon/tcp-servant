package dk.zpon.tcpservant.protocol.jsonrpc;

import com.google.gson.Gson;

import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorSerializer;
import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;
import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;

public class JsonRpcSerializer implements IReflectorSerializer {
	
	private Gson gson;

	public JsonRpcSerializer() {
		gson = new Gson();
	}

	@Override
	public IRequestObject deserializeRequest(String request) {
		return gson.fromJson(request, JsonRpcRequest.class);
	}

	@Override
	public String serializeResponse(IResponseObject response) {
		return gson.toJson(response);
	}

}
