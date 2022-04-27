package orders;

import customers.Customer;
import markets.Product;

import java.util.List;

public class Order {

    private String orderId;
    private OrderStatus status;
    private List<Product> products;
    private double totalPrice;
    private Customer customer;

    public Order(String orderId, List<Product> products, Customer customer) {
        this.orderId = orderId;
        this.status = this.status.NEW;
        this.products = products;
        this.totalPrice = calPrice(products);
        this.customer = customer;
    }

    public double calPrice(List<Product> products){
        double total = 0;
        for (Product product : products){
            total += product.getPrice();
        }
        return total;

    }

    public void setPrice(double price){
        this.totalPrice = price;
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }

    public String getStatus(){
        return "This order's status is " + this.status;
    }

//    public OrderStatus getStatus(){
//        return this.status;
//    }

    public void setStatus(OrderStatus status){
        this.status = status;
    }

    public List<Product> getProducts(){
        return this.products;
    }

    public Customer getCustomer(){
        return customer;
    }




}
