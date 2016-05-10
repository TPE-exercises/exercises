package airport.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import airport.FlightRoute;
import airport.SimulationPlane;
import airport.exceptions.GeneralFlightSimulatorException;
import airport.exceptions.InvalidFlightPlanException;
import airport.exceptions.PlaneTooHighException;
import airport.exceptions.PlaneTooLowException;

public class SimulationPlaneTest {
	
	private SimulationPlane plane;

	@Before
	public void init() throws InvalidFlightPlanException {
		FlightRoute route = new FlightRoute(6, 100, 200);
		plane = new SimulationPlane(route);
	}
	
	@Test(expected = GeneralFlightSimulatorException.class)
	public void flyWithOpenDoors() throws GeneralFlightSimulatorException {
		plane.flyNextKilometer(50);
	}
	
	@Test(expected = GeneralFlightSimulatorException.class)
	public void flyWithTooMuchHeightDifference() throws GeneralFlightSimulatorException {
		plane.closeDoors();
		plane.flyNextKilometer(101);
	}
	
	@Test(expected = PlaneTooHighException.class)
	public void flyTooHeigh() throws GeneralFlightSimulatorException {
		plane.closeDoors();
		plane.flyNextKilometer(100);
		plane.flyNextKilometer(100);
		plane.flyNextKilometer(1);
	}
	
	@Test(expected = PlaneTooLowException.class)
	public void flyBelowGround() throws GeneralFlightSimulatorException {
		plane.closeDoors();
		plane.flyNextKilometer(-1);
	}
	
	@Test(expected = PlaneTooLowException.class)
	public void flyTooLowAtCityStart() throws GeneralFlightSimulatorException {
		plane.closeDoors();
		plane.flyNextKilometer(50);
		plane.flyNextKilometer(49);
	}
	
	@Test(expected = PlaneTooLowException.class)
	public void flyTooLowAtCityEnd() throws GeneralFlightSimulatorException {
		plane.closeDoors();
		plane.flyNextKilometer(100);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(-1);
	}

	public void flyCorrect() {
		plane.closeDoors();
		try {
			plane.flyNextKilometer(0);
			plane.flyNextKilometer(100);
			plane.flyNextKilometer(50);
			plane.flyNextKilometer(50);
			plane.flyNextKilometer(-100);
			plane.flyNextKilometer(-100);
		} catch (GeneralFlightSimulatorException e) {
			fail();
		}
		
		assertEquals(6, plane.getCurrentKilometer());
		assertEquals(0, plane.getCurrentHeight());
	}
	
	@Test(expected = GeneralFlightSimulatorException.class)
	public void flyTooFar() throws GeneralFlightSimulatorException {
		plane.closeDoors();
		plane.flyNextKilometer(100);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(0);
		plane.flyNextKilometer(0);
	}
}
