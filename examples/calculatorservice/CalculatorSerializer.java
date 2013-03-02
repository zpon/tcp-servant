package calculatorservice;

import com.google.gson.Gson;

import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorSerializer;
import dk.zpon.tcpservant.requesthandlers.reflector.IRequestObject;
import dk.zpon.tcpservant.requesthandlers.reflector.IResponseObject;

public class CalculatorSerializer implements IReflectorSerializer {

	@Override
	public IRequestObject deserializeRequest(String request) {
		Gson gson = new Gson();

		// Gson can de-serialize json data into objects, in this case, it will
		// try to map the request to the BaseRequest class (which implements
		// IRequestObject)
		BaseRequest requestObject = gson.fromJson(request, BaseRequest.class);

		// In this example, all methods needs a series of integers as input, so
		// it just uses the same class for all requests, however, it would be
		// easy to change this, such that request object returned is different
		// for each method in the service
		return requestObject;
	}

	@Override
	public String serializeResponse(IResponseObject response) {
		Gson gson = new Gson();

		// This method serializes the response to a textual representation
		return gson.toJson(response);
	}

}
