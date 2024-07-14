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
public class Shopping {

    ArrayList<Fruit> listFruit = new ArrayList<>();
    ArrayList<Order> listOrder = new ArrayList<>();
    Hashtable<String, ArrayList<Order>> customer = new Hashtable<>();

    public Fruit findFruitByID(int id) {
        for (Fruit fruit : listFruit) {
            if (fruit.getId() == id) {
                return fruit;
            }

        }
        return null;

    }

    public void createFruit(Fruit newFruit) {
        listFruit.add(newFruit);
    }

    public void printListFruit() {
        System.out.println("=========== LIST OF FRUIT ==========");
        System.out.printf("%-10s%-15s%-15s%-15s%-15s\n", "Item", "Fruit Name", "Origin","Quantity", "Price");
        for (Fruit fruit : listFruit) {
            System.out.printf("%-10d%-15s%-15s%-15d%-15s\n", fruit.getId(), fruit.getName(), fruit.getOrigin(),fruit.getQuantity(), fruit.getPrice());
        }
    }

    public void updateOrder(int id, int quantity) {
        for (Order order : listOrder) {
            if (order.getId() == id) {
                order.setQuantity(order.getQuantity() + quantity);
            }
            return;
        }
    }

    public void orderList() {
        for (Map.Entry<String, ArrayList<Order>> entry : customer.entrySet()) {
            String name = entry.getKey();
            ArrayList<Order> orderList = entry.getValue();
            System.out.println("Customer: " + name);
            System.out.printf("%-15s%-15s%-15s%-15s\n", "Product", "Quantity", "Price", "Amount");
            int total = 0;
            for (int i = 0; i < orderList.size(); i++) {
                total += orderList.get(i).getPrice() * orderList.get(i).getQuantity();
                System.out.printf("%-15s%-15d%-15s%-15s\n", orderList.get(i).getName(), orderList.get(i).getQuantity(), orderList.get(i).getPrice() + "$", orderList.get(i).getPrice() * orderList.get(i).getQuantity() + "$");
            }
            System.out.println("Total :" + total + "$");
        }
    }

}
