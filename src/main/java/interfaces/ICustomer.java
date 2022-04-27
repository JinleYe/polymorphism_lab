package interfaces;

import markets.OnlineMarket;
import markets.Product;
import orders.Order;


public interface ICustomer {

    Order placeOrder(OnlineMarket market);
    Order placeOrder(OnlineMarket market, Product product);



}
