/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

/**
 *
 * @author asus
 */
public class Main {

    static int id = 1;
    static Shopping sp = new Shopping();

    public int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== FRUIT SHOP SYSTEM =========");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice < 1 || choice > 4) {
                    System.err.println("Must be between 1 to 4 ");
                    continue;
                }
                return choice;
            } catch (NumberFormatException e) {
                System.err.println("Invalid Input");
                System.out.println("Enter again");
            }
        }
    }

    public String checkInputStr() {
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            try {
                str = sc.nextLine();
                if (str.equals("")) {
                    throw new Exception();
                }
                return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            } catch (Exception e) {
                System.err.println("Cannot be empty!!");
                System.out.print("Enter again: ");
            }

        }

    }

    public int checkInputInt() {
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine().trim());
                return input;

            } catch (NumberFormatException e) {
                System.err.println("Invalid !");
                System.out.print("Enter again: ");
            }
        }
    }
    
    public boolean checkYN(){
        System.out.print("Do you want to order now ? (Y/N): ");
        while ( true ){
            String input = checkInputStr();
            if ( input.equalsIgnoreCase("Y")){
                return true;
                
            }
            if ( input.equalsIgnoreCase("N")){
                return false;
            }
            System.err.println("Please input Y/y or N/n");
            System.out.print("Enter again: ");
        }
    }
    
    public int checkQuantity(Fruit fruit){
        Scanner sc = new Scanner(System.in);
        int quantity = 0;
        while ( true ){
            try {
                quantity = checkInputInt();
                if ( quantity > fruit.getQuantity()){
                    throw new Exception();
                }
                return quantity;
            } catch (Exception e) {
                System.err.println("Order quantity cannot greater than fruit quantity");
                System.out.print("Enter again: ");
            }
        }
    }

    public Fruit enterFruit() {
        System.out.print("Enter fruit name: ");
        String name = checkInputStr();
        System.out.print("Enter fruit origin: ");
        String origin = checkInputStr();
        System.out.print("Enter fruit price: ");
        int price = checkInputInt();
        System.out.print("Enter fruit quantity: ");
        int quantity = checkInputInt();
        return new Fruit(id++, name, price, origin, quantity);
    }

    public void orderFunction(ArrayList<Fruit> lf, Hashtable<String, ArrayList<Order>> ht) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter Item: ");
            int id = checkInputInt();
            Fruit fruit = sp.findFruitByID(id);
            System.out.println("You selected " + fruit.getName());
            System.out.print("Enter quantity: ");
            int quantity = checkQuantity(fruit);
            fruit.setQuantity(fruit.getQuantity() - quantity);
            sp.listOrder.add(new Order(id, fruit.getName(), quantity, fruit.getPrice()));
            if(checkYN()){
                break;
            }
        }
        
        System.out.print("Enter customer name: ");
        String name = checkInputStr();
        ht.put(name, sp.listOrder);
    }

    public static void main(String[] args) {
        Main m = new Main();

        while (true) {
            int choice = m.menu();
            switch (choice) {
                case 1:
                    sp.createFruit(m.enterFruit());
                    break;
                case 2:
                    sp.orderList();
                    break;
                case 3:
                    sp.printListFruit();
                    m.orderFunction(sp.listFruit, sp.customer);
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
