package calculatorservice;

import java.util.List;

import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;

public class BaseRequest implements IRequestObject {

	private String operation;
	private List<Integer> arguments;
	
	@Override
	public String getRequestServiceMethodName() {
		return operation;
	}
	
	public List<Integer> getArguments() {
		return arguments;
	}
}
