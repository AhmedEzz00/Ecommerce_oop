import entity.Customer;
import entity.Order;
import entity.Product;
import manager.CustomerManager;
import manager.OrderManager;
import manager.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FawryTask {

    private static final OrderManager orderManager =new OrderManager();
    private static final CustomerManager customerManager = new CustomerManager();
    private static final ProductManager productManager = new ProductManager();
    private static final Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {
        boolean isRunning= true;
        while(isRunning) {
            viewMainMenu();
            int option= Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:{
                    createNewProduct();
                    break;
                }
                case 2: {
                    removeProduct();
                    break;
                }
                case 3: {
                    viewAllProducts();
                    break;
                }
                case 4: {
                    createNewCustomer();
                    break;
                }
                case 5: {
                    removeCustomer();
                    break;
                }
                case 6: {
                    viewAllCustomers();
                    break;
                }
                case 7: {
                    createNewOrder();
                    break;
                }
                case 8: {
                    viewAllOrders();
                    break;
                }
                case 9:{
                    removeOrder();
                    break;
                }
                case 10:{
                    isRunning = false;
                    break;
                }
                default: {
                    System.out.println("invalid option");
                    break;
                }
                }
            }
        }

    private static void viewMainMenu() {
        System.out.println("\tMain menu");
        System.out.println("please choose an option");
        System.out.println("1. Add new product");
        System.out.println("2. remove product");
        System.out.println("3. view all products");
        System.out.println("4. add new customer");
        System.out.println("5. remove customer");
        System.out.println("6. view all customers");
        System.out.println("7. create new order");
        System.out.println("8. view all orders");
        System.out.println("9. remove order");
        System.out.println("10. exit");
    }

        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\
            /////////// product methods \\\\\\\\\\\
        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\
    private static void createNewProduct() {
        System.out.println("\tAdd new product");
        System.out.print("please enter product id: ");
        Long id = Long.parseLong(scanner.nextLine());
        if (findProductById(id)!= null){
            System.out.println("product already exists");
        }else   {
            System.out.print("please enter product name: ");
            String name = scanner.nextLine();
            System.out.print("please enter product description: ");
            String description = scanner.nextLine();
            System.out.print("please enter product price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("please enter product stock quantity: ");
            int stockQuantity = Integer.parseInt(scanner.nextLine());
            Product product = new Product(id, name, description, price, stockQuantity);
            productManager.addProduct(product);
            System.out.println("product added.");
        }

    }

    private static void removeProduct() {
        System.out.println("\tremove product");
        System.out.print("please enter product id: ");
        long productId = Long.parseLong(scanner.nextLine());
        if (findProductById(productId)== null){
            System.out.println("product not found");
            return;
        }
        productManager.removeProduct(productId);
    }

    private static void viewAllProducts() {
        System.out.println("\tview all products");
        List<Product> products = productManager.viewAllProducts();
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\
             /////////// customer methods \\\\\\\\\\\
        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\

    private static void createNewCustomer() {
        System.out.println("\tadd new customer");
        System.out.print("please enter customer id: ");
        long id= Long.parseLong(scanner.nextLine());
        if (findCustomerById(id) != null) {
            System.out.println("customer already exists");
        }else {
            System.out.print("please enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("please enter customer email: ");
            String email = scanner.nextLine();
            System.out.print("please enter customer address: ");
            String address = scanner.nextLine();
            Customer customer = new Customer(id, name, email, address);
            customerManager.addCustomer(customer);
            System.out.println("customer added.");
        }
    }

    private static void removeCustomer() {
        System.out.println("\tremove customer");
        System.out.print("please enter customer id: ");
        long customerId= Long.parseLong(scanner.nextLine());
        if (findCustomerById(customerId) == null) {
            System.out.println("customer not found");
            return;
        }
        customerManager.removeCustomer(customerId);
    }

    private static void viewAllCustomers() {
        System.out.println("\tview all customers");
        List<Customer> customers = customerManager.viewAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

    }

        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\
              /////////// order methods \\\\\\\\\\\
        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\

    private static void createNewOrder() {
        System.out.println("\tcreate new order");
        System.out.println("please enter order id: ");
        long orderId = Long.parseLong(scanner.nextLine());
        if (findOrderById(orderId)!=null){
            System.out.println("order with id: "+orderId+" already exists");
            return;
        }
        Customer customer = addCustomerToOrder();
        if (customer == null) return;

        List<Product> products= addProductsToOrder();
        if (products== null) return;

        Order order = new Order(orderId, customer, products);
        orderManager.addOrder(order);
        System.out.println("order created.");

    }


    private static void viewAllOrders(){
        System.out.println("\tview all orders");
        List<Order> orders = orderManager.viewAllOrders();
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }

    private static void removeOrder() {
        System.out.println("\tremove order");
        System.out.print("please enter order id: ");
        long orderId = Long.parseLong(scanner.nextLine());
        if (findOrderById(orderId)== null){
            System.out.println("order not found");
            return;
        }
        orderManager.removeOrder(orderId);
    }


    /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\
    /////////// helper methods \\\\\\\\\\\
    /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\


    private static List<Product> addProductsToOrder() {
        List<Product> products= new ArrayList<>();
        while (true) {
            System.out.println("please enter product id: ");
            long productId= Long.parseLong(scanner.nextLine());
            if(productId== -1){
                break;
            }
            Product product = findProductById(productId);
            if (product!= null){
                if (product.getStockQuantity() > 0) {
                    products.add(product);
                } else {
                    System.out.println("Product " + product.getName() + " is out of stock.");
                }
            }else{
                System.out.println("product not found.");
            }
        }
        if (products.isEmpty()){
            System.out.println("no products added to the order.");
            return null;
        }
        return products;
    }

    private static Customer addCustomerToOrder() {
        System.out.print("please enter customer id: ");
        long customerId = Long.parseLong(scanner.nextLine());
        Customer customer= findCustomerById(customerId);
        if (customer == null) {
            System.out.println("customer not found");
            return null;
        }
        return customer;
    }

    private static Product findProductById(long productId) {
        return productManager.viewAllProducts()
                .stream()
                .filter(product -> product.getId()== productId)
                .findFirst()
                .orElse(null);
    }

    private static Customer findCustomerById(long customerId){
        return customerManager.viewAllCustomers()
                .stream()
                .filter(customer -> customer.getId()== customerId)
                .findFirst().orElse(null);
    }

    private static Order findOrderById(long orderId){
        return orderManager.viewAllOrders()
                .stream()
                .filter(order -> order.getId()==orderId)
                .findFirst().orElse(null);
    }
}
