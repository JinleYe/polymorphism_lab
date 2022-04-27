package interfaces;

import markets.OnlineMarket;
import orders.Order;

public interface IUser {

    String cancelOrder(OnlineMarket market, Order order);

}
