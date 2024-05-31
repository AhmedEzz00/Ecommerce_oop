package manager;

import entity.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductManager {

    private final List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }


    public void removeProduct(long productId) {
        if (products.removeIf(product -> product.getId() == productId)) {
            System.out.println("Product removed successfully");
        } else {
            System.out.println("Product not found");
        }
    }


    public List<Product> viewAllProducts() {
        return products;
    }

    public void updateStock(int productId, int newStockQuantity){
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == productId) {
                product.setStockQuantity(newStockQuantity);
                break;
            }
        }
    }
}
