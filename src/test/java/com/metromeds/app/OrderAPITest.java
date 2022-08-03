package com.metromeds.app;

import com.metromeds.app.models.order.Order;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderAPITest extends AppApplicationTests {
    @LocalServerPort
    int randomServerPort;
    @Test
    public void testGetOrderIsSuccessful() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:"+randomServerPort+"/api/orders";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeeds
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    public void testOrderIsFetchedCorrectly() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/orders?id=1";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(true, result.getBody().contains("1"));
        Assert.assertEquals(true, result.getBody().contains("Pickup"));
    }
    @Test
    public void testPostOrderIsSuccessful() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/orders";
        URI uriOrder = new URI(baseUrl);
        Order order = new Order();
        order.setId(1);
        order.setEmployeeId(1);
        order.setCustomerId(1);
        order.setOrderDate(new Date());
        order.setShippedDate(new Date());
        order.setPaidDate(new Date());
        order.setShipName("Metromeds");
        order.setShipAddress1("Limerick");
        order.setShipAddress2(null);
        order.setShipCity("Limerick");
        order.setShipState(null);
        order.setShipPostalCode(null);
        order.setShipCountry("Ireland");
        order.setShippingFee(10L);
        order.setPaymentType("Card");
        order.setOrderStatus("Delivered");
        order.setPromoCode("WEEKEND10");
        order.setCartPrice(10.22);
        order.setDeliveryCharge(5L);
        order.setAmountPayable(25.22);
        order.setServiceType("Express");
        order.setOrderType("Delivery");
        order.setIsPrescribed(1);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTg3MTczMiwiaWF0IjoxNjE1ODUzNzMyfQ.5i9yDz0yZNBKvJDEZ8IS9Pyd6g8VyeplITuduGpmhB9hUpUgmW3PW0p0D7C7iSq6mqUkDDAa9-66lha2n07Aaw");

        HttpEntity<Order> request = new HttpEntity<>(order, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(uriOrder, request, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
