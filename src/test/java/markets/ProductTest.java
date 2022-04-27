package markets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp(){
        product = new Product("item1","123", 200);
    }

    @Test
    public void canGetName(){
        assertThat(product.getName()).isEqualTo("item1");
    }

    @Test
    public void canGetProductId(){
        assertThat(product.getProductId()).isEqualTo("123");
    }

    @Test
    public void canGetPrice(){
        assertThat(product.getPrice()).isEqualTo(200);
    }


}
