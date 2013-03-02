package calculatorservice;

import java.util.List;

public class AddRequest extends BaseRequest {
	private List<Integer> arguments;

	public List<Integer> getArguments() {
		return arguments;
	}

}
