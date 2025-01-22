public class Car {
    public String name, contents, origin, destination;
    public String type;
    public int weight, miles;

    public Car (String name, String contents, String origin, String destination, int weight, int miles) {
        this.type = "CAR";
        this.name = name;
        this.contents = contents;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.miles = miles;
    } //car constructor

    public Car (String name, String destination) {
        this.type = "ENG";
        this.name = name;
        this.destination = destination;
    } //engine constructor

    public void resetMiles() {
        miles = 100;
    } //reset miles

    public String toString() {
        if (type.equals("ENG"))
            return name + " leaving for " + destination + " with the following cars: ";
        else 
            return name + " containing " + contents;
    } //print car

} //end car
