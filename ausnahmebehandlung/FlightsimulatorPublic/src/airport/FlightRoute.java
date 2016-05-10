package airport;

import airport.exceptions.InvalidFlightPlanException;

public class FlightRoute {
	
	
	/**
	 * Creates a new flight route.
	 * @param flightLength Length of the route in km	
	 * @param minHeight	minimum height the plane needs to fly over a town in m
	 * @param maxHeight	maximum height the plane is allowed to fly in m
	 * @throws InvalidFlightPlanException If the data is not valid
	 */
	public FlightRoute(int flightLength, int minHeight, int maxHeight) throws InvalidFlightPlanException {
	}

	public int getFlightLength() {
	}
	
	/**
	 * Sets the length of the flight in kilometers. Has to be at least 1 km.
	 * @param flightLength	Length of the flight in km
	 * @throws InvalidFlightPlanException If supplied length < 1
	 */
	public void setFlightLength(int flightLength) throws InvalidFlightPlanException {
	}

	public int getMaxHeight() {
	}

	/**
	 * Sets the maximum height the plane is allowed to fly.
	 * @param maxHeight maximum height in m
	 * @throws InvalidFlightPlanException if maximum height < minimum height
	 */
	public void setMaxHeight(int maxHeight) throws InvalidFlightPlanException {
	}

	public int getMinHeight() {
	}

	/**
	 * Sets the minimum height the plane is allowed to fly over a town	
	 * @param minHeight minimum height over a town in m
	 * @throws InvalidFlightPlanException If minimum height is lower than zero
	 */
	public void setMinHeight(int minHeight) throws InvalidFlightPlanException {
	}
	
	@Override
	public String toString() {
	}
		
}