package markets;

public class Product {

    private String name;
    private String productId;
    private double price;

    public Product(String name, String productId, double price) {
        this.name = name;
        this.productId = productId;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public String getProductId(){
        return this.productId;
    }

    public double getPrice(){
        return this.price;
    }
}
