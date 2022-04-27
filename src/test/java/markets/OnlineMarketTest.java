package markets;
import customers.NormalCustomer;
import customers.VIPCustomer;
import markets.OnlineMarket;
import markets.Product;

import orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.*;

public class OnlineMarketTest {

    private NormalCustomer normalCustomer;
    private VIPCustomer vipCustomer;
    private Product product1;
    private Product product2;
    private OnlineMarket shop;

    @BeforeEach
    public void setUp() {
        normalCustomer = new NormalCustomer("Jake", "123", 1000);
        vipCustomer = new VIPCustomer("John", "124", 2000);
        product1 = new Product("item_1", "111", 100);
        product2 = new Product("item_2", "112", 200);
        shop = new OnlineMarket("AliExpress");

        normalCustomer.placeOrder(shop, product1);
        vipCustomer.placeOrder(shop, product1);

    }


    @Test
    public void canAddProduct(){
        shop.addProduct(product1);
        shop.addProduct(product2);
        assertThat(shop.getSellingProducts().size()).isEqualTo(2);
    }

    @Test
    public void canGetCurrentOrders(){
        assertThat(shop.getCurrentOrders().size()).isEqualTo(2);
    }

    @Test
    public void canHoldOrder(){
        assertThat(shop.holdOrder()).isEqualTo("This order's status is HOLD");
    }

    @Test
    public void canShipOrder(){
        shop.holdOrder();
        assertThat(shop.shipOrder()).isEqualTo("This order's status is SHIPPED");
    }

    @Test
    public void canDeliverOrder(){
        shop.holdOrder();
        shop.shipOrder();
        assertThat(shop.deliverOrder()).isEqualTo("This order's status is DELIVERED");
        assertThat(shop.getCurrentOrders().size()).isEqualTo(1);
        assertThat(shop.getHistoryOrders().size()).isEqualTo(1);
    }

    @Test
    public void canCancelOrder_order_notShipped(){
        // order has not been shipped
        Order order = vipCustomer.placeOrder(shop, product1);
        assertThat(shop.cancelOrder(shop, order)).isEqualTo("Canceled Order.");
        assertThat(shop.getCurrentOrders().size()).isEqualTo(2);
        assertThat(vipCustomer.getWallet()).isEqualTo(1920);

        // order has been shipped
    }

    @Test
    public void canCancelOrder_order_Shipped(){
        // order has been shipped
        shop.holdOrder();
        shop.shipOrder();
        Order order = shop.getCurrentOrders().get(0);
        assertThat(shop.cancelOrder(shop, order)).isEqualTo("This order cannot be canceled.");
    }

//    @Test
//    public void canChange(){
//        shop.changeOrderStatus();
//        shop.changeOrderStatus();
//        shop.changeOrderStatus();
//        assertThat(shop.getCurrentOrders().size()).isEqualTo(1);
//    }


}
