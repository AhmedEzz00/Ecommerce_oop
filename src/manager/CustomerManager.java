package manager;

import entity.Customer;

import java.util.Iterator;
import java.util.List;

public class CustomerManager {
    private final List<Customer> customers;

    public CustomerManager() {
        customers = new java.util.ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }


    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(long customerId) {
        if (customers.removeIf(customer -> customer.getId() == customerId)){
            System.out.println("customer removed.");
        }
         else {
            System.out.println("customer not found.");
        }
    }

    public List<Customer> viewAllCustomers() {
        return customers;
    }


}
