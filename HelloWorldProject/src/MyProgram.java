/*
 * TODO: Steven Wang
 * TODO: 1/10/25
 * TODO: 3
 * TODO: Train program
 */
import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;


public class MyProgram {
	public static int val = 0;
	static Train trainA;
	static Train trainB;
	static Train trainC;
	static Train trainD;
	public static void main(String[] args) {

		int limitTrackA = 100000, limitTrackB = 100000, limitTrackC = 100000;
		
		Scanner x = new Scanner(System.in);

		String line = "";
		try{
			File f = new File("HelloWorldProject/src/data.txt");
			x = new Scanner (f);
			line = x.nextLine();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		Queue<String> rawInput = new LinkedList<String>();
		while (!line.equals("END")) {
			rawInput.add(line);
			line = x.nextLine();
		}

		// START PROCESSING CARS
		Queue<Car> cars = new LinkedList<Car>();
		trainA = new Train("Trenton", limitTrackA);
		trainB = new Train("Charlotte", limitTrackB);
		trainC = new Train("Baltimore", limitTrackC);
		trainD = new Train("Other", -1);
		
		while (!rawInput.isEmpty()) {
			String name = rawInput.remove();
			//System.out.println(name);
			if (name.substring(0, 3).equals("CAR")) {
				cars.add(
					new Car(
						name,
						rawInput.remove(),
						rawInput.remove(),
						rawInput.remove(),
						Integer.valueOf(rawInput.remove()),
						Integer.valueOf(rawInput.remove())
					)
				);
			} else {
				cars.add(
					new Car(
						name,
						rawInput.remove()
					)
				);
			}
		} //get raw string input from file


		

		Car nextCar = cars.remove();
		while (nextCar != null) {
			
			if (nextCar.miles > 700) {
				nextCar.resetMiles();
				cars.add(nextCar);
				nextCar = getNext(cars);
			}
			
			switch (nextCar.destination){
				case "Trenton":
					if (trainA.canAdd(nextCar)) {
						trainA.addCar(nextCar);
						nextCar = getNext(cars);
					} else {
						trainA.addEngine(nextCar.destination);
					}
					break;
				case "Charlotte":
					if (trainB.canAdd(nextCar)) {
						trainB.addCar(nextCar);
						nextCar = getNext(cars);
					} else {
						trainB.addEngine(nextCar.destination);
					}
					break;
				case "Baltimore":
					if (trainC.canAdd(nextCar)) {
						trainC.addCar(nextCar);
						nextCar = getNext(cars);
					} else {
						trainC.addEngine(nextCar.destination);
					}
					break;
				default:
					trainD.addCar(nextCar);
					//System.out.println(":::::" + nextCar.name);
					nextCar = getNext(cars);
					break;
			} //handle adding cars to trains
			departures();
		} //process all trains

		//final departures
		trainA.addEngine("Trenton");
		trainB.addEngine("Charlotte");
		trainC.addEngine("Baltimore");
		trainA.depart();
		trainB.depart();
		trainC.depart();
		
		System.out.println("Track D Cars: ");
		trainD.depart();
		
	}//end main

	public static void departures() {
		if (trainA.ready()) {
			trainA.depart();
		}

		if (trainB.ready()) {
			trainB.depart();
		}

		if (trainC.ready()) {
			trainC.depart();
		}
	} //handle departures

	public static Car getNext(Queue<Car> cars) {
		try {
			return cars.remove();

		} catch (Exception e) {
			return null;
		}
	} //return next car from queue
} //end program
