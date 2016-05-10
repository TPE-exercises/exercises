package airport.exceptions;

public class InvalidFlightPlanException extends GeneralFlightSimulatorException {

	private static final long serialVersionUID = 7289000683464295828L;
	
	public InvalidFlightPlanException(String message) {
		super(message);
	}

}

