package airport.exceptions;

public class PlaneTooHighException extends GeneralFlightSimulatorException {

	private static final long serialVersionUID = 5048348324280267994L;

	public PlaneTooHighException(int height) {
		super("Plane crushed into another plane in " + height + " meters height");
	}
}
