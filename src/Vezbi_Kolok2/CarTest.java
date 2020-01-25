//package Vezbi_Kolok2;

import java.util.*;

class Car implements Comparable<Car>{

    String manufacturer;
    String model;
    int price;
    float power;

    public Car(String manufacturer,String model,int price,float power){
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    @Override
    public int compareTo(Car car) {
        if (this.price == car.price)
            return 0;
        else if(this.price < car.price)
            return -1;
        else
            return 1;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%.0fKW) %d" , manufacturer,model,power,price);
    }
}

class CarCollection{

    private List<Car> cars;

    public CarCollection(){
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void sortByPrice(boolean ascending){
        this.cars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car car, Car t1) {
                int temp = 0;
                if (ascending){
                    temp = Integer.compare(car.price,t1.price);
                    if (temp == 0)
                        return Float.compare(car.power,t1.power);
                    return temp;
                }
                temp = -(Integer.compare(car.price,t1.price));
                if (temp == 0)
                    return -(Float.compare(car.power,t1.power));
                return temp;
            }
        });
    }

    public List<Car> filterByManufacturer(String manufacturer){
        List<Car> tempLista = new ArrayList<>();
        for (Car temp : cars){
            if (temp.manufacturer.equalsIgnoreCase(manufacturer))
                tempLista.add(temp);
        }
        tempLista.sort(new Comparator<Car>() {
            @Override
            public int compare(Car car, Car t1) {
                return car.model.compareTo(t1.model);
            }
        });
        return tempLista;
    }

    public List<Car> getList(){
        return cars;
    }

}

public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}


// vashiot kod ovde