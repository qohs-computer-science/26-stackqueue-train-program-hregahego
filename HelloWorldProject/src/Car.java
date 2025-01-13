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
    }

    public Car (String name, String destination) {
        this.type = "ENG";
        this.name = name;
        this.destination = destination;
    }

    public String toString() {
        return name + " " + contents + " " + destination + " " + weight + " " + miles;
    }

}
