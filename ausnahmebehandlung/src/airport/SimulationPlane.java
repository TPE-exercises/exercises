package airport;

import airport.exceptions.PlaneTooHighException;
import airport.exceptions.PlaneTooLowException;
import airport.exceptions.GeneralFlightSimulatorException;

public class SimulationPlane implements Plane {
	
	private final static int MAXIMUM_HEIGHT_DIFFERENCE = 100;
	
	private FlightRoute route;
	
	private boolean doorsOpen;
	private int currentKilometer;
	private int currentHeight;
	private boolean standing;
	
	public boolean areDoorsOpen() {
		return doorsOpen;
	}

	public int getCurrentKilometer() {
		return currentKilometer;
	}

	public int getCurrentHeight() {
		return currentKilometer;
	}
	
	public boolean isStanding() {
		return standing;
	}

	public SimulationPlane(FlightRoute route) {
		this.route = route;
		this.currentKilometer = 0;
		this.doorsOpen = true;
		this.standing = true;
	}
	
	public void openDoors() throws GeneralFlightSimulatorException {
		if(!standing) {
			throw new GeneralFlightSimulatorException("Cannot open doors while plane has not stopped");
		}
		System.out.println("Türen geöffnet");
		this.doorsOpen = true;
	}
	
	public void closeDoors() {
		System.out.println("Türen geschlossen");
		this.doorsOpen = false;
	}
	
	public void stop() throws GeneralFlightSimulatorException {
		if(currentHeight != 0) {
			throw new GeneralFlightSimulatorException("Plane can't stop while flying");
		}
		System.out.println("Flugzeug gestoppt");
		this.standing = true;
	}
	
	public boolean simulationSuccessfullyDone() {
		return standing && doorsOpen && currentKilometer == route.getFlightLength();
	}
	
	public void flyNextKilometer(int additionalHeight) throws GeneralFlightSimulatorException {
		
		// Nur mit geschlossenen Türen losfliegen
		if(doorsOpen) {
			throw new GeneralFlightSimulatorException("Can't start flying with open doors");
		}
		
		// Sowohl negative, als auch positive Höhenunterschiede prüfen, dass sie nicht zu hoch sind
		if(Math.abs(additionalHeight) > MAXIMUM_HEIGHT_DIFFERENCE) {
			throw new GeneralFlightSimulatorException("Too much height difference: "+additionalHeight);
		}
		
		standing = false;
		currentHeight += additionalHeight;
		currentKilometer++;
		
		// prüfen ob zu weit
		if(currentKilometer > route.getFlightLength()) {
			throw new GeneralFlightSimulatorException("You flew too long!");
		}
		
		// Prüfung ob zu hoch immer
		if(currentHeight > route.getMaxHeight()) {
			throw new PlaneTooHighException(currentHeight);
		}
		
		// prüfen ob abgestürzt immer
		if(currentHeight < 0) {
			throw new PlaneTooLowException(currentHeight);
		}
		
		// Fluglänge - entfernungZuStadt = letzter punkt in der Stadt 
		int borderOfTown = route.getFlightLength() - FlightRoute.DISTANCE_TO_TOWN;
		
		// Prüfung ob zu tief für stadt nur wenn stadt erreicht und noch nicht verlassen
		if(currentKilometer >= FlightRoute.DISTANCE_TO_TOWN && currentKilometer <= borderOfTown ) {
			if(currentHeight < route.getMinHeight()) {
				throw new PlaneTooLowException(currentHeight);
			}
		}
		
		System.out.println("Flugzeug ist an Kilometer: "+currentKilometer + " auf Höhe: " + currentHeight + " m.");
	}
}
