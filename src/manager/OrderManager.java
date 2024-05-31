package manager;

import entity.Order;
import entity.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderManager {
    private final List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }


    public List<Order> getOrders() {
        return orders;
    }



    public void addOrder(Order order) {
        for (Product product : order.getProducts()) {
            if (product.getStockQuantity() < 1) {
                System.out.println("Product " + product.getName() + " is out of stock.");
                return;
            }
        }
        for (Product product : order.getProducts()) {
            product.decreaseStock(1);
        }
        orders.add(order);
    }

    public void removeOrder(long orderId) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getId() == orderId) {
                for (Product product : order.getProducts()) {
                    product.increaseStock(1);
                }
                iterator.remove();
                return;
            }
        }
    }

    public List<Order> viewAllOrders() {
        return orders;
    }
}
