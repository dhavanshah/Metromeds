package com.metromeds.app.service;
import com.metromeds.app.models.payment.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    //@Value("${STRIPE_SECRET_KEY}")
    private String secretKey = "sk_test_51IUWdJBiVZ8qBXWDToumsoT77QLwHqLoitDgFEL6abftsvP46wNhpBWBzAJaM38HVK1wX9NcbRicdcclD4htUud100jDXhgyWT";

    @Autowired
    public StripeService() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}