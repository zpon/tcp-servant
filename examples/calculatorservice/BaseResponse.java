package calculatorservice;

import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;

public class BaseResponse implements IResponseObject {
	String operation = "add";
	int result;
	
	public BaseResponse(BaseRequest request, int result) {
		this.result = result;
		this.operation = request.getRequestServiceMethodName();
	}
}
