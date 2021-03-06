package dk.zpon.tcpcommunicator.unittest;

import org.junit.Test;

import dk.zpon.tcpservant.UnhandledRequest;
import dk.zpon.tcpservant.protocol.jsonrpc.CalculatorService;
import dk.zpon.tcpservant.protocol.jsonrpc.JsonRpcSerializer;
import dk.zpon.tcpservant.requesthandlers.reflector.ReflectorRequestHandler;

public class JsonRpcTest {
	
	@Test
	public void subtractTest() {
		String jsonRequest = "{\"jsonrpc\": \"2.0\", \"method\": \"subtract\", \"params\": [42, 23], \"id\": 1}";
		
		CalculatorService jsonRpcHandler = new CalculatorService();
		JsonRpcSerializer jsonRpcSerializer = new JsonRpcSerializer();
		
		ReflectorRequestHandler serviceReflector = new ReflectorRequestHandler(jsonRpcSerializer, jsonRpcHandler);
		String response;
		try {
			response = serviceReflector.handleRequest(jsonRequest);
			System.out.println(response);
		} catch (UnhandledRequest e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
