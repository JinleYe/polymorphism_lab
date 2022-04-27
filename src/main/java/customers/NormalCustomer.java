package customers;

import markets.OnlineMarket;
import markets.Product;
import orders.Order;

import java.util.ArrayList;
import java.util.List;

import orders.OrderStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomStringUtils.*;

public class NormalCustomer extends Customer{

    public NormalCustomer(String name, String accountId, int wallet) {
        super(name, accountId, wallet);
    }



    // place order from the cart
    @Override
    public Order placeOrder(OnlineMarket market) {

        String orderId = RandomStringUtils.random(3, false, true);

        Order new_order = new Order(orderId, this.cart, this);
        this.wallet -= new_order.getTotalPrice();

        this.getHistoryOrders().add(new_order);
        market.getCurrentOrders().add(new_order);
        this.clearCart();

        return new_order;
    }


    // buy right now, from the product page
    @Override
    public Order placeOrder(OnlineMarket market, Product product) {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        String orderId = RandomStringUtils.random(3, false, true);
        Order new_order = new Order(orderId, productList, this);
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
            double refund = order.getTotalPrice();
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
