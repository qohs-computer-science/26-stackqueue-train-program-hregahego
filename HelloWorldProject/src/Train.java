import java.util.Stack;

public class Train {
    
    private int weight;
    private int limit;
    private String destination;
    private Stack<Car> cars;
    public Train (String destination, int limit) {
        weight = 0;
        this.destination = destination;
        this.limit = limit;
        cars = new Stack<Car>();
    }

    public boolean ready() {
        try {
            if (cars.peek().type.equals("ENG")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        
    }

    public boolean canAdd(Car newCar) {
        return limit < 0 || weight + newCar.weight <= limit;
    }

    public void addCar(Car newCar) {    
        cars.push(newCar);
        weight += newCar.weight;
    }

    public void addEngine(String destination) {
        cars.push(new Car("ENG00000", destination));
    }

    public void depart() {
        while (!cars.empty()) {
            System.out.println(cars.pop());
        }
        System.out.println();
        cars.clear();
        weight = 0;
    }



}