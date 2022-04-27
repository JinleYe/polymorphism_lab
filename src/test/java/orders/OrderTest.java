package orders;
import customers.VIPCustomer;
import markets.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class OrderTest {

    private Order order;
    private Product product1;
    private Product product2;
    private List<Product> product_list;
    private VIPCustomer customer;

    @BeforeEach
    public void setUp(){
        product1 = new Product("item_1","111", 100);
        product2 = new Product("item_2", "112", 200);

        product_list = new ArrayList<>();
        Collections.addAll(product_list, product1, product2);

        customer = new VIPCustomer("Amy","234", 2000);

        order = new Order("234", product_list, customer);
    }

    @Test
    public void canGetStatus(){
        assertThat(order.getStatus()).isEqualTo("This order's status is NEW");
    }

    @Test
    public void canGetTotalPrice(){
        assertThat(order.getTotalPrice()).isEqualTo(300);
    }

    @Test
    public void canSetPrice(){
        order.setPrice(500);
        assertThat(order.getTotalPrice()).isEqualTo(500);
    }

    @Test
    public void canGetProducts(){
        assertThat(order.getProducts().size()).isEqualTo(2);
    }

}
