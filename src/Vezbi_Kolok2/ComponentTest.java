//package Vezbi_Kolok2;

import java.util.*;

class Component implements Comparable<Component>{

    String color;
    int weight;
    Set<Component> components;

    public Component(String color,int weight){
        this.color = color;
        this.weight = weight;
        this.components = new TreeSet<>();
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public void changeColor(int weight,String color){
        if (this.weight < weight)
            this.color = color;
        for (Component temp : components)
            temp.changeColor(weight,color);
    }

    @Override
    public int compareTo(Component component) {
        if (weight == component.weight)
            return color.compareTo(component.color);
        else if (weight < component.weight)
            return -1;
        else
            return 1;
    }

    @Override
    public String toString() {
        return String.format("%d:%s\n",weight,color);
    }

    public String print(String lines){
        StringBuilder sb = new StringBuilder();
        if (components.size() == 0)
            return sb.toString();
        else {
            for (Component temp : components){
                sb.append(lines);
                sb.append(temp.toString());
                sb.append(temp.print(lines + "---"));
            }
        }
        return sb.toString();
    }
}

class Window {

    String name;
    Map<Integer,Component> mapa;

    public Window(String name){
        this.name = name;
        mapa = new TreeMap<>();
    }

    public void addComponent(int position,Component component) throws InvalidPositionException {
        if (mapa.containsKey(position))
            throw new InvalidPositionException(position);
        mapa.put(position,component);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW " + name + "\n");
        for (Integer temp : mapa.keySet()){
            Component component = mapa.get(temp);
            sb.append(temp + ":" + component.weight + ":" + component.color + "\n");
            sb.append(component.print("---"));
        }
        return sb.toString();
    }

    public void changeColor(int weight,String color){
        for (Component temp : mapa.values())
            temp.changeColor(weight,color);
    }

    public void swichComponents(int pos1,int pos2){
        Component first = mapa.remove(pos1);
        Component second = mapa.remove(pos2);
        mapa.put(pos1,second);
        mapa.put(pos2,first);
    }

}

class InvalidPositionException extends Exception{

    public InvalidPositionException(int pos){
        super("Invalid position " + pos + ", alredy taken!");
    }

}

public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
}

// вашиот код овде