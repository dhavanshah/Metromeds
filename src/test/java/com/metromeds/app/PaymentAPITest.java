package com.metromeds.app;
import com.metromeds.app.models.payment.Payment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentAPITest extends AppApplicationTests {
    @LocalServerPort
    int randomServerPort;
    @Test
    public void testGetPayment() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/payments";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("SUCCESS"));
    }
    @Test
    public void testPostPaymentIsSuccessful() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String paymentBaseUrl = "http://localhost:" + randomServerPort + "/payments";
        URI uriPay = new URI(paymentBaseUrl);
        Payment payment = new Payment();
        payment.setId(3L);
        payment.setBillId(1);
        payment.setPaymentType("wallet");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNTg3MTczMiwiaWF0IjoxNjE1ODUzNzMyfQ.5i9yDz0yZNBKvJDEZ8IS9Pyd6g8VyeplITuduGpmhB9hUpUgmW3PW0p0D7C7iSq6mqUkDDAa9-66lha2n07Aaw");

        HttpEntity<Payment> request = new HttpEntity<>(payment, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uriPay, request, String.class);
        System.out.print("Payment Response is:" + result.getBody());

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
