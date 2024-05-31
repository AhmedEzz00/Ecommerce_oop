package entity;

import java.util.Iterator;
import java.util.List;

public class Order {
   private Long id;
   private Customer customer;
   private List<Product> products;
   private int totalAmount;

    public Order(Long id, Customer customer, List<Product> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double calculateTotaAmount() {
       totalAmount= 0;
       Iterator<Product> iterator = products.iterator();
       while (iterator.hasNext()) {
           Product product = iterator.next();
           totalAmount += product.getPrice();
       }
       return totalAmount;


    }

    @Override
    public String toString() {
        return "Order {" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", totalAmount=" + calculateTotaAmount() +
                "}";
    }
}
