/*
 * TODO: Steven Wang
 * TODO: 1/10/25
 * TODO: 3
 * TODO: hee hee hee haw
 */
import java.util.Scanner;
import java.io.File;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

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
		Queue<Car> track1 = new LinkedList<Car>();
		trainA = new Train("Trenton", limitTrackA);
		trainB = new Train("Charlotte", limitTrackB);
		trainC = new Train("Baltimore", limitTrackC);
		trainD = new Train("Other", -1);
		
		while (!rawInput.isEmpty()) {
			String name = rawInput.remove();
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
		}

		while (!cars.isEmpty()) {
			Car nextCar = cars.remove();

			if (nextCar.weight > 700) {
				track1.add(nextCar);
				nextCar = cars.remove();
			}
			
			departures();

			switch (nextCar.destination){
				case "Trenton":
					if (trainA.canAdd(nextCar)) {
						trainA.addCar(nextCar);
						nextCar = cars.remove();
					}
					break;
				case "Charlotte":
					if (trainB.canAdd(nextCar)) {
						trainB.addCar(nextCar);
						nextCar = cars.remove();
					}
					break;
				case "Baltimore":
					if (trainC.canAdd(nextCar)) {
						trainC.addCar(nextCar);
						nextCar = cars.remove();
					}
					break;
				default:
					trainD.addCar(nextCar);
					nextCar = cars.remove();
			}

			
		}
		while (!track1.isEmpty()) {
			Car nextCar = track1.remove();	
			departures();

			switch (nextCar.destination){
				case "Trenton":
					if (trainA.canAdd(nextCar)) {
						trainA.addCar(nextCar);
						nextCar = cars.remove();
					}
					break;
				case "Charlotte":
					if (trainB.canAdd(nextCar)) {
						trainB.addCar(nextCar);
						nextCar = cars.remove();
					}
					break;
				case "Baltimore":
					if (trainC.canAdd(nextCar)) {
						trainC.addCar(nextCar);
						nextCar = cars.remove();
					}
					break;
				default:
					trainD.addCar(nextCar);
					nextCar = cars.remove();
			}

			
		}

		


	}

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

		if (trainD.ready()) {
			trainD.depart();
		}
	}
}
