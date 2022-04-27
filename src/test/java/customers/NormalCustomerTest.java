package customers;
import markets.OnlineMarket;
import markets.Product;
import orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;


public class NormalCustomerTest {

    private NormalCustomer normalCustomer;
    private Product product1;
    private Product product2;
    private OnlineMarket shop;

    @BeforeEach
    public void setUp(){
        normalCustomer = new NormalCustomer("Amy","123", 1000);
        product1 = new Product("item_1","111", 100);
        product2 = new Product("item_2", "112", 200);
        shop = new OnlineMarket("AliExpress");

        shop.addProduct(product1);
        shop.addProduct(product2);

    }

    @Test
    public void canGetBasicInfo(){
        assertThat(normalCustomer.getName()).isEqualTo("Amy");
        assertThat(normalCustomer.getAccountId()).isEqualTo("123");
        assertThat(normalCustomer.getWallet()).isEqualTo(1000);
    }

    @Test
    public void canAddProductToCart(){
        normalCustomer.addProductToCart(shop, "item_1");

        assertThat(normalCustomer.getCart().size()).isEqualTo(1);
    }

    @Test
    public void canPlaceOrderFromCart(){
        normalCustomer.addProductToCart(shop, "item_1");
        normalCustomer.placeOrder(shop);
        assertThat(normalCustomer.getHistoryOrders().size()).isEqualTo(1);
        assertThat(normalCustomer.getWallet()).isEqualTo(900);
        assertThat(normalCustomer.getCart().size()).isEqualTo(0);

        assertThat(normalCustomer.getHistoryOrders().get(0).getStatus()).isEqualTo("This order's status is NEW");
    }

    @Test
    public void canPlaceOrderFromProduct(){
        normalCustomer.addProductToCart(shop, "item_1"); // product 1, price: 100
        normalCustomer.placeOrder(shop, product2); // product 2, price: 200

        assertThat(normalCustomer.getCart().size()).isEqualTo(1);
        assertThat(normalCustomer.getHistoryOrders().size()).isEqualTo(1);
        assertThat(normalCustomer.getWallet()).isEqualTo(800);
    }



}
