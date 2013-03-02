package dk.zpon.tcpservant.protocol.jsonrpc;

import java.util.ArrayList;

import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorService;
import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;
import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;

public class CalculatorService implements IReflectorService {
	
	public IResponseObject subtract(JsonRpcRequest request) {
		Object params = request.getParams();
		
		if(params instanceof ArrayList<?>) {
			Double a = (Double) ((ArrayList) params).get(0);
			Double b = (Double) ((ArrayList) params).get(1);
			
			return new JsonRpcResponse(request.getJsonrpc(), a-b, request.getId()+1);
		}
		return null;
	}

	@Override
	public IResponseObject unknownRequest(IRequestObject request) {
		// TODO Auto-generated method stub
		return null;
	}

}
