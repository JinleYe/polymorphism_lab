package customers;

import markets.OnlineMarket;
import markets.Product;
import orders.Order;
import orders.OrderStatus;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class VIPCustomer extends Customer{

    public VIPCustomer(String name, String accountId, int wallet) {
        super(name, accountId, wallet);
    }

    public void speedUpShipping(OnlineMarket market, Order order){

        market.getCurrentOrders().remove(order);
        market.getCurrentOrders().set(0, order);


    }

    public void setDiscount(Order order){
        order.setPrice(order.getTotalPrice()*0.8);

    }


    // place order from the cart
    @Override
    public Order placeOrder(OnlineMarket market) {
        String orderId = RandomStringUtils.random(3, false, true);
        Order new_order = new Order(orderId, this.cart, this);

        this.setDiscount(new_order);
        this.wallet -= new_order.getTotalPrice();

        // add order to history orders of customer class
        this.getHistoryOrders().add(new_order);
        // add order to the market class
        market.getCurrentOrders().add(new_order);

        // clear cart
        this.clearCart();
        return new_order;
    }

    // place order directly from the product
    @Override
    public Order placeOrder(OnlineMarket market, Product product) {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        String orderId = RandomStringUtils.random(3, false, true);
        Order new_order = new Order(orderId, productList, this);

        this.setDiscount(new_order);
        this.wallet -= new_order.getTotalPrice();

        this.getHistoryOrders().add(new_order);
        market.getCurrentOrders().add(new_order);

        return new_order;
    }


    @Override
    public String cancelOrder(OnlineMarket market, Order order) {

        if(!order.getStatus().equals("This order's status is SHIPPED")&&
                !order.getStatus().equals("This order's status is DELIVERED")){
            historyOrders.remove(order);
            double refund = order.getTotalPrice() * 0.8;
            this.wallet += refund;


            // set order status to delivered(closed)
            order.setStatus(OrderStatus.DELIVERED);
            // remove from currentOrders
            market.getCurrentOrders().remove(order);
            // add to historyOrders at the market
            market.getHistoryOrders().add(order);
            return "Canceled Order.";
        } else{
            return "This order cannot be canceled.";
        }
    }
}
