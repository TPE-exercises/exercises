package airport;

import static gdi.MakeItSimple.*;

import gdi.MakeItSimple;
import airport.exceptions.GeneralFlightSimulatorException;
import airport.exceptions.InvalidFlightPlanException;
import airport.exceptions.PlaneTooHighException;
import airport.exceptions.PlaneTooLowException;

public class PilotSimulation {
	
	private SimulationPlane plane;
	private FlightRoute route;
	
	private int tooLowExceptions = 0;
	private int tooHeighExceptions = 0;
	private int generalFlightExceptions = 0;
	private int successfullSimulations = 0;
	
	private void createFlightRoute() {
		FlightRoute route = null;

		// Flugplan erstellen
		do {
			try {
				System.out.println("Flugplan einstellen");
				int km, minHeight, maxHeight;
				System.out.println("Länge des Fluges in km: ");
				km = readInt();
				System.out.println("Minimale Höhe in m über der Stadt: ");
				minHeight = readInt();
				System.out.println("Maximale Höhe in m über der Stadt: ");
				maxHeight = readInt();

				route = new FlightRoute(km, minHeight, maxHeight);
			} catch (InvalidFlightPlanException e) {
				System.out.println("Ungültiger Flugplan.");
			} catch (gdi.MISException e) {
				System.out.println("Ungültige Zahl");
				MakeItSimple.readLine();
			}
		} while (route == null);
		
		System.out.println("Flugplan wurde erstellt:");
		System.out.println(route);
		this.route = route;
	}

	private void startSimulation() {
		this.plane = new SimulationPlane(route);
		System.out.println("Neue Simulation gestartet");
	}
	
	private int chooseFromMenu() {
		boolean showMenu;
		int choice = 0;
		do {
			showMenu = false;
			System.out.println("Menu");
			System.out.println("1 - Türen öffnen");
			System.out.println("2 - Türen schließen");
			System.out.println("3 - Flugzeug anhalten");
			System.out.println("4 - Fliegen");
			System.out.println("5 - Beenden");

			try {
				choice = readInt();
			} catch (gdi.MISException e) {
				System.out.println("Ungültige Zahl");
				MakeItSimple.readLine();
				showMenu = true;
			}
		} while (showMenu);

		return choice;
	}
	
	private void actOnFly() throws GeneralFlightSimulatorException {
		boolean askAgain = true;
		while(askAgain) {
			try {
				askAgain = false;
				System.out.println("Höhe angeben, die geflogen werden soll:");
				int height = readInt();
				plane.flyNextKilometer(height);
			} catch (gdi.MISException e) {
				System.out.println("Ungültige Zahl");
				MakeItSimple.readLine();
				askAgain = true;
			}
		}
		
	}
	
	private void actOnChoice(int choice) throws GeneralFlightSimulatorException {
		switch (choice) {
		case 1:
			plane.openDoors();
			break;
		case 2:
			plane.closeDoors();
			break;
		case 3:
			plane.stop();
			break;
		case 4:
			actOnFly();
			break;

		case 5:
			System.out.println("Simulation beendet");
			System.out.println("Zu hoch geflogen: "+tooHeighExceptions);
			System.out.println("Zu tief geflogen: "+tooLowExceptions);
			System.out.println("Sonstige Fehler: "+generalFlightExceptions);
			System.out.println("Erfolgeich geflogen: "+successfullSimulations);
			break;
			
		default:
			break;
		}
	}

	public static void main(String[] args) {

		PilotSimulation simulator = new PilotSimulation();
		simulator.createFlightRoute();
		simulator.startSimulation();
		
		while(true) {
			int choice = simulator.chooseFromMenu();
			
			try {
				
				simulator.actOnChoice(choice);
				
				if(simulator.simulationSuccessfullyDone()) {
					System.out.println("Simulation erflogreich beendet.");
					simulator.successfullSimulations++;
					simulator.startSimulation();
				}
				
			} catch (PlaneTooHighException e) {
				simulator.tooHeighExceptions++;
				System.out.println(e.getMessage());
				simulator.startSimulation();
			} catch (PlaneTooLowException e) {
				simulator.tooLowExceptions++;
				System.out.println(e.getMessage());
				simulator.startSimulation();
			} catch (GeneralFlightSimulatorException e) {
				simulator.generalFlightExceptions++;
				System.out.println(e.getMessage());
				simulator.startSimulation();
			}
			
			if(choice == 5)
				return;
		}
		

	}

	private boolean simulationSuccessfullyDone() {
		return plane != null && 
				plane.getCurrentKilometer() == route.getFlightLength() &&
				plane.areDoorsOpen();
	}

}
