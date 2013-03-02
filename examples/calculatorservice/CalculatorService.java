package calculatorservice;

import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorService;
import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;
import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;

/**
 * A simple calculator example service. Call the {@link #add(BaseRequest)} or
 * {@link #multiply(BaseRequest)} methods to add or multiply a series of numbers
 * together.
 * 
 * @author sjuul
 * 
 */
public class CalculatorService implements IReflectorService {

	/**
	 * Add a series of integers together. Example of call:
	 * 
	 * <pre>
	 * {operation: "add", arguments : [3, 5, 7]}
	 * </pre>
	 * 
	 * Example of response:
	 * 
	 * <pre>
	 * {"operation":"add","result":15}
	 * </pre>
	 * 
	 * @param request
	 * @return
	 */
	public IResponseObject add(BaseRequest request) {

		int sum = 0;
		for (Integer i : request.getArguments()) {
			sum += i;
		}

		return new BaseResponse(request, sum);
	}

	/**
	 * Multiply a series of integers together. Example of call:
	 * 
	 * <pre>
	 * {operation: "multiply", arguments : [3, 5, 7]}
	 * </pre>
	 * 
	 * Example of response:
	 * 
	 * <pre>
	 * {"operation":"multiply","result":105}
	 * </pre>
	 * 
	 * @param request
	 * @return
	 */
	public IResponseObject multiply(BaseRequest request) {

		int sum = request.getArguments().get(0);

		for (int i = 1; i < request.getArguments().size(); i++) {
			sum *= request.getArguments().get(i);
		}

		return new BaseResponse(request, sum);
	}

	@Override
	public IResponseObject unknownRequest(IRequestObject request) {
		System.out.println("Unknown request " + request);
		return null;
	}

}
