package customers;
import markets.OnlineMarket;
import markets.Product;

import orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.*;

public class VIPCustomerTest {

    private NormalCustomer normalCustomer;
    private VIPCustomer vipCustomer;
    private Product product1;
    private Product product2;
    private OnlineMarket shop;


    @BeforeEach
    public void setUp(){
        normalCustomer = new NormalCustomer("Jake","123", 1000);
        vipCustomer = new VIPCustomer("John", "124", 2000);
        product1 = new Product("item_1","111", 100);
        product2 = new Product("item_2", "112", 200);
        shop = new OnlineMarket("AliExpress");

        shop.addProduct(product1);
        shop.addProduct(product2);

    }

    @Test
    public void canSpeedUpShipping(){
        // normal customer place order first
        normalCustomer.placeOrder(shop, product1); // price: 100

        // vip customer place order next
        Order vip_order = vipCustomer.placeOrder(shop, product2); // price : 200
        vipCustomer.speedUpShipping(shop, vip_order);
        assertThat(shop.getCurrentOrders().get(0).getTotalPrice()).isEqualTo(160);
    }

    @Test
    public void canPlaceOrderFromCart(){
        vipCustomer.addProductToCart(shop, "item_1");
        vipCustomer.placeOrder(shop);
        assertThat(vipCustomer.getCart().size()).isEqualTo(0);
        assertThat(vipCustomer.getWallet()).isEqualTo(1920);
        assertThat(vipCustomer.getHistoryOrders().size()).isEqualTo(1);
    }

    @Test
    public void canPlaceOrderFromProduct(){
        vipCustomer.addProductToCart(shop, "item_1");
        vipCustomer.placeOrder(shop, product2);
        assertThat(vipCustomer.getCart().size()).isEqualTo(1);
        assertThat(vipCustomer.getWallet()).isEqualTo(1840);
        assertThat(vipCustomer.getHistoryOrders().size()).isEqualTo(1);

    }


}
