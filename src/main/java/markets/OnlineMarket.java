package markets;

import interfaces.IUser;
import orders.Order;
import orders.OrderStatus;

import java.util.ArrayList;
import java.util.List;

import static orders.OrderStatus.*;

public class OnlineMarket implements IUser {

    private String name;
    private List<Product> sellingProducts;
    private List<Order> currentOrders;
    private List<Order> historyOrders;

    public OnlineMarket(String name) {
        this.name = name;
        this.sellingProducts = new ArrayList<>();
        this.currentOrders = new ArrayList<>();
        this.historyOrders = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.sellingProducts.add(product);
    }

    public List<Product> getSellingProducts(){
        return this.sellingProducts;
    }

    public List<Order> getCurrentOrders(){
        return this.currentOrders;
    }

    public List<Order> getHistoryOrders(){
        return this.historyOrders;
    }



    // hold the first new order
    public String holdOrder() {
        if(currentOrders.size()!=0){
            for (Order order : currentOrders) {
                if(order.getStatus().equals("This order's status is NEW")) {
                    order.setStatus(HOLD);
                    return order.getStatus();
                }
            }
        }
        return "There is nothing to hold";

    }

    // ship the first hold order
    public String shipOrder() {
        if(currentOrders.size()!=0){
            for (Order order : currentOrders) {
                if(order.getStatus().equals("This order's status is HOLD")) {
                    order.setStatus(SHIPPED);
                    return order.getStatus();
                }
            }
        }
        return "There is nothing to ship";
    }

    // deliver the first ship order
    public String deliverOrder() {
        if(currentOrders.size()!=0){
            for (Order order : currentOrders) {
                if(order.getStatus().equals("This order's status is SHIPPED")) {
                    order.setStatus(DELIVERED);
                    currentOrders.remove(order);
                    historyOrders.add(order);

                    return order.getStatus();
                }
            }
        }
        return "There is nothing to deliver";
    }

    @Override
    public String cancelOrder(OnlineMarket market, Order order) {
        if(!order.getStatus().equals("This order's status is SHIPPED")&&
                !order.getStatus().equals("This order's status is DELIVERED")){
            order.getCustomer().getHistoryOrders().remove(order);

            order.getCustomer().addMoneyToWallet(order.getTotalPrice());


            // set order status to delivered(closed)
            order.setStatus(OrderStatus.DELIVERED);
            // remove from currentOrders
            this.getCurrentOrders().remove(order);
            // add to historyOrders at the market
            this.getHistoryOrders().add(order);
            return "Canceled Order.";
        } else{
            return "This order cannot be canceled.";
        }

    }


//    public String changeOrderStatus(){
//        if(currentOrders.size()!=0){
//
//            switch(currentOrders.get(0).getStatus()){
//                case NEW:
//                    currentOrders.get(0).setStatus(HOLD);
//                    return "This order's status is hold.";
//                case HOLD:
//                    currentOrders.get(0).setStatus(SHIPPED);
//                    return "This order's status is shipped.";
//                case SHIPPED:
//                    currentOrders.get(0).setStatus(DELIVERED);
//                    currentOrders.remove(0);
//                    return "This order's status is delivered.";
//            }
//        }
//        return null;
//    }






}
