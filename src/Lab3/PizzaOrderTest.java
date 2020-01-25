    //package Lab3;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;



    // EXCEPTIONS FROM HERE
    class InvalidExtraTypeException extends Exception{

        public InvalidExtraTypeException(){}

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }

    class InvalidPizzaTypeException extends Exception{

        public InvalidPizzaTypeException(){

        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }

    class ItemOutOfStockException extends Exception{

        private Item item;

        public ItemOutOfStockException(Item item){
            this.item = item;
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }

    class ArrayIndexOutOfBоundsException extends Exception{

        private int index;

        public ArrayIndexOutOfBоundsException(int idx){
            this.index = idx;
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }

    class EmptyOrder extends Exception{

        public EmptyOrder(){}

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }

    class OrderLockedException extends Exception{

        public OrderLockedException(){}

        @Override
        public String getMessage() {
            return super.getMessage();
        }
    }
    // EXCEPTIONS TO HERE


    interface Item {
        public int getPrice();
    }

    class ExtraItem implements Item{

        private String type;

        public ExtraItem(String type) throws InvalidExtraTypeException {
            if(!(type.equals("Coke") || type.equals("Ketchup")))
                throw new InvalidExtraTypeException();
            this.type = type;
        }

        @Override
        public int getPrice() {
            if(type.equals("Coke"))
                return 5;
            else
                return 3;
        }

        @Override
        public String toString() {
            return type.toString();
        }
    }

    class PizzaItem implements Item{

        private String type;

        public PizzaItem(String type) throws InvalidPizzaTypeException {
            if(!(type.equals("Standard") || type.equals("Pepperoni") || type.equals("Vegetarian")))
                throw new InvalidPizzaTypeException();
            this.type = type;
        }

        @Override
        public int getPrice() {
            if(type.equals("Standard"))
                return 10;
            else if(type.equals("Vegetarian"))
                return 8;
            else
                return 12;
        }

        @Override
        public String toString() {
            return type.toString();
        }
    }

    class Order{

        private List<Item> items;
        private List<Integer> quantity;
        private boolean locked;

        public Order(){
            this.items = new ArrayList<>();
            this.quantity = new ArrayList<>();
            this.locked = false;
        }

        public void addItem(Item item,int count) throws ItemOutOfStockException, OrderLockedException {
            if (locked)
                throw new OrderLockedException();
            if(count > 10)
                throw new ItemOutOfStockException(item);

            if(this.items.contains(item)){
                int temp = items.indexOf(item);
                quantity.set(temp,count);
            }else{
                items.add(item);
                quantity.add(count);
            }
        }

        public int getPrice(){
            int sum = 0;
            int count = 0;
            for(Item item : items){
                sum += (item.getPrice() * quantity.get(count));
                count++;
            }
            return sum;
        }

        public void displayOrder(){
            int count = 0;
            StringBuilder st = new StringBuilder();
            for (Item item : items){
                int howMany = quantity.get(count);
                int toprint = count + 1;
                st.append(String.format("%3d.%-15sx%2d%5d$\n", toprint,item.toString(),howMany,item.getPrice()*howMany));
                count++;
            }
            st.append(String.format("%-22s%5d$","Total:",getPrice()));
            System.out.println(st.toString());
        }

        public void removeItem(int idx) throws ArrayIndexOutOfBоundsException, OrderLockedException {
            if (locked)
                throw new OrderLockedException();
            if(idx >= this.items.size())
                throw new ArrayIndexOutOfBоundsException(idx);
            this.items.remove(idx);
        }

        public void lock() throws EmptyOrder {
            if(items.size() == 0)
                throw new EmptyOrder();
            this.locked = true;
        }

    }

    public class PizzaOrderTest {

        public static void main(String[] args) {
            Scanner jin = new Scanner(System.in);
            int k = jin.nextInt();
            if (k == 0) { //test Item
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    System.out.println(item.getPrice());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            if (k == 1) { // test simple order
                Order order = new Order();
                while (true) {
                    try {
                        String type = jin.next();
                        String name = jin.next();
                        Item item = null;
                        if (type.equals("Pizza")) item = new PizzaItem(name);
                        else item = new ExtraItem(name);
                        if (!jin.hasNextInt()) break;
                        order.addItem(item, jin.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getClass().getSimpleName());
                    }
                }
                jin.next();
                System.out.println(order.getPrice());
                order.displayOrder();
                while (true) {
                    try {
                        String type = jin.next();
                        String name = jin.next();
                        Item item = null;
                        if (type.equals("Pizza")) item = new PizzaItem(name);
                        else item = new ExtraItem(name);
                        if (!jin.hasNextInt()) break;
                        order.addItem(item, jin.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getClass().getSimpleName());
                    }
                }
                System.out.println(order.getPrice());
                order.displayOrder();
            }
            if (k == 2) { // test order with removing
                Order order = new Order();
                while (true) {
                    try {
                        String type = jin.next();
                        String name = jin.next();
                        Item item = null;
                        if (type.equals("Pizza")) item = new PizzaItem(name);
                        else item = new ExtraItem(name);
                        if (!jin.hasNextInt()) break;
                        order.addItem(item, jin.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getClass().getSimpleName());
                    }
                }
                jin.next();
                System.out.println(order.getPrice());
                order.displayOrder();
                while (jin.hasNextInt()) {
                    try {
                        int idx = jin.nextInt();
                        order.removeItem(idx);
                    } catch (Exception e) {
                        System.out.println(e.getClass().getSimpleName());
                    }
                }
                System.out.println(order.getPrice());
                order.displayOrder();
            }
            if (k == 3) { //test locking & exceptions
                Order order = new Order();
                try {
                    order.lock();
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
                try {
                    order.addItem(new ExtraItem("Coke"), 1);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
                try {
                    order.lock();
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
                try {
                    order.removeItem(0);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
        }

    }