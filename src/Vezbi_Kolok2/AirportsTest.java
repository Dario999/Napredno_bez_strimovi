package Vezbi_Kolok2;

import javax.xml.stream.XMLInputFactory;
import java.util.*;
import java.util.zip.CheckedOutputStream;

class Airport implements Comparable<Airport>{

    String name;
    String country;
    String code;
    int passengers;

    public Airport(String name,String country,String code,int passengers){
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\n%d",name,code,country,passengers);
    }

    @Override
    public int compareTo(Airport airport) {
        return code.compareTo(airport.code);
    }
}

class Flight implements Comparable<Flight>{

    String from;
    String to;
    int time;
    int duration;

    public Flight(String from,String to,int time,int duration){
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }


    @Override
    public int compareTo(Flight flight) {
        if (to.compareTo(flight.to) == 0){
            if (time < flight.time)
                return -1;
            else if (time > flight.time)
                return 1;
            else{
                if (duration < flight.duration)
                    return -1;
                else if (duration > flight.duration)
                    return 1;
                else
                    return 0;
            }
        }else {
            return to.compareTo(flight.to);
        }
    }

    //1. LHR-ATL 12:44-17:35 4h51m


    @Override
    public String toString() {
        int hours = time / 60;
        int minutes = time % 60;
        int hoursArrival = hours + duration / 60;
        int minutesArrival = minutes + duration % 60;
        if (minutesArrival >= 60)
            hoursArrival += minutesArrival / 60;
        minutesArrival %= 60;
        if (hoursArrival >= 24)
            return String.format("%s-%s %02d:%02d-%02d:%02d +1d %dh%02dm\n",from,to,hours,minutes,hoursArrival-24,minutesArrival,duration/60,duration%60);
        else
            return String.format("%s-%s %02d:%02d-%02d:%02d %dh%02dm\n",from,to,hours,minutes,hoursArrival,minutesArrival,duration/60,duration%60);
    }
}

class Flights{

    TreeSet<Flight> set;

    public Flights(){
        set = new TreeSet<>();
    }

    public void addFlight(Flight flight){
        set.add(flight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (Flight flight : set){
            sb.append(counter + ". " + flight);
            counter++;
        }
        return sb.toString();
    }

    public String showTo(String to){
        StringBuilder sb = new StringBuilder();
        for (Flight flight : set)
            if (flight.to.equals(to))
                sb.append(flight);
         return sb.toString();
    }

    public List<Flight> showToList(String to){
        List<Flight> lista = new ArrayList<>();
        for (Flight flight : set)
            if (flight.to.equals(to))
                lista.add(flight);
        return lista;
    }
}

class Airports{

    private TreeMap<Airport,Flights> mapa;
    private HashMap<String,Airport> hashMap;

    public Airports(){
        mapa = new TreeMap<>();
        hashMap = new HashMap<>();
    }

    public void addAirport(String name,String country,String code,int passengers){
        Airport airport = new Airport(name,country,code,passengers);
        hashMap.put(code,airport);
        mapa.put(airport,new Flights());
    }

    public void addFlights(String from,String to,int time,int duration){
        Flight flight = new Flight(from,to,time,duration);
        Airport airport = hashMap.get(from);
        mapa.get(airport).addFlight(flight);
    }

    public void showFlightsFromAirport(String code){
        Airport airport = hashMap.get(code);
        System.out.println(airport);
        System.out.print(mapa.get(airport));
    }

    public void showDirectFlightsFromTo(String from,String to){
        Airport airport = hashMap.get(from);
        String string = mapa.get(airport).showTo(to);
        System.out.print(string);
        if (string.equals(""))
            System.out.println("No flights from " + from + " to " + to);

    }

    public void showDirectFlightsTo(String to){
        TreeSet<Flight> treeSet = new TreeSet<>();
        List<Flight> list = new ArrayList<>();
        for (Airport airport : mapa.keySet()){
            list = mapa.get(airport).showToList(to);
            for (Flight flight : list)
                treeSet.add(flight);
        }
        for (Flight flight : treeSet)
            System.out.print(flight);

//        Airport airport = hashMap.get("SFO");
//        Flights flights = mapa.get(airport);
//        System.out.println(flights);
    }


}

public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde

