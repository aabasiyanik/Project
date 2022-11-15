import java.util.*;
// This class is our interface class.
public interface Product {
    // This productsList is our database. It stores everything about the product in once.
    ArrayList<MyProducts> productsList = new ArrayList<>();

    // These methods are in our interface class, and we are defining them in MyProducts.java class which implements Product.java class.
    void addProducts();

    void searchProducts();

    void listProducts();

    void exit();
}
