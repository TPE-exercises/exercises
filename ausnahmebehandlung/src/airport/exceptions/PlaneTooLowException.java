package airport.exceptions;

public class PlaneTooLowException extends GeneralFlightSimulatorException {

	private static final long serialVersionUID = 1418895127193919601L;

	public PlaneTooLowException(int height) {
		super("Plane crushed into something at the ground in " + height + " meters height");
	}
}
