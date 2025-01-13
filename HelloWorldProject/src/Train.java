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
        if (cars.peek().type.equals("ENG")) {
            return true;
        } else {
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

    public void depart() {
        Car engine = cars.pop();
        System.out.println(engine.name + " leaving for " + engine.destination + " with the following cars: ");
        while (!cars.empty()) {
            Car cur = cars.pop();
            System.out.println(cur.name + " containing " + cur.contents);
        }
        cars.clear();
        weight = 0;
    }



}