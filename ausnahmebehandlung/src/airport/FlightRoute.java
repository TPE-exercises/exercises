package airport;

import airport.exceptions.InvalidFlightPlanException;

public class FlightRoute {
	
	public final static int DISTANCE_TO_TOWN = 2;
		
	private int flightLength;
	private int maxHeight;
	private int minHeight;
	
	/**
	 * Creates a new flight route.
	 * @param flightLength Length of the route in km	
	 * @param minHeight	minimum height the plane needs to fly over a town in m
	 * @param maxHeight	maximum height the plane is allowed to fly in m
	 * @throws InvalidFlightPlanException If the data is not valid
	 */
	public FlightRoute(int flightLength, int minHeight, int maxHeight) throws InvalidFlightPlanException {
		this.setFlightLength(flightLength);
		this.setMinHeight(minHeight);
		this.setMaxHeight(maxHeight);
	}

	public int getFlightLength() {
		return flightLength;
	}
	
	/**
	 * Sets the length of the flight in kilometers. Has to be at least 1 km.
	 * @param flightLength	Length of the flight in km
	 * @throws InvalidFlightPlanException If supplied length < 1
	 */
	public void setFlightLength(int flightLength) throws InvalidFlightPlanException {
		if(flightLength < 1) {
			throw new InvalidFlightPlanException("Minimum flight length: 0");
		}
		this.flightLength = flightLength;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Sets the maximum height the plane is allowed to fly.
	 * @param maxHeight maximum height in m
	 * @throws InvalidFlightPlanException if maximum height < minimum height
	 */
	public void setMaxHeight(int maxHeight) throws InvalidFlightPlanException {
		if(maxHeight < minHeight) {
			throw new InvalidFlightPlanException("Maximum height must not be lower than minimum height");
		}
		this.maxHeight = maxHeight;
	}

	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * Sets the minimum height the plane is allowed to fly over a town	
	 * @param minHeight minimum height over a town in m
	 * @throws InvalidFlightPlanException If minimum height is lower than zero
	 */
	public void setMinHeight(int minHeight) throws InvalidFlightPlanException {
		if(minHeight < 0) {
			throw new InvalidFlightPlanException("Minimum Height can't be negative.");
		}
		this.minHeight = minHeight;
	}
	
	@Override
	public String toString() {
		return "L‰nge: " + flightLength + ", "+
				"minHeight: " + minHeight + ", "+
				"maxHeight: " + maxHeight;
	}
		
}