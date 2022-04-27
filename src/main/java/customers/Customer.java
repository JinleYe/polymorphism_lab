package customers;

import interfaces.ICustomer;
import interfaces.IUser;
import markets.OnlineMarket;
import markets.Product;
import orders.Order;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer implements ICustomer, IUser {

    private String name;
    private String accountId;
    protected List<Product> cart;
    protected double wallet;
    protected List<Order> historyOrders;

    public Customer(String name, String accountId, double wallet) {
        this.name = name;
        this.accountId = accountId;
        this.cart = new ArrayList<>();
        this.wallet = wallet;
        this.historyOrders = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public String getAccountId(){
        return this.accountId;
    }

    public double getWallet() {
        return this.wallet;
    }

    public void addMoneyToWallet(double money){
        this.wallet += money;
    }

    public List<Product> getCart(){
        return this.cart;
    }

    public List<Order> getHistoryOrders(){
        return this.historyOrders;
    }

    public void addProductToCart(OnlineMarket market, String name){
        for (Product product : market.getSellingProducts()){
            if (name.equals(product.getName())){
                cart.add(product);
            }
        }

    }

    public void clearCart(){
        this.cart = new ArrayList<>();
    }



}
