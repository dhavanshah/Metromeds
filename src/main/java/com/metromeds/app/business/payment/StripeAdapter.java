package com.metromeds.app.business.payment;

import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.models.payment.ChargeRequest;
import com.metromeds.app.models.payment.Payment;
import com.metromeds.app.models.payment.PaymentStatus;
import com.metromeds.app.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;

import java.util.HashMap;
import java.util.Map;

public class StripeAdapter implements IPayment {

    private StripeService stripeService;
    public StripeAdapter(StripeService stripeService){
        this.stripeService=stripeService;
    }
    public ChargeRequest createChargeRequest(Bill bill){
        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount((int)(bill.getTotalAmount() * 100));
        chargeRequest.setCurrency(ChargeRequest.Currency.INR);
        chargeRequest.setDescription("Test");
        try {
            chargeRequest.setStripeToken(generateToken().getId());
        }catch (Exception ex){ex.printStackTrace();}
        chargeRequest.setStripeEmail("myappsyukti@gmail.com");
        return chargeRequest;
    }

    private Token generateToken() throws StripeException {
        Map<String, Object> params = new HashMap<>();

            Map<String, Object> card = new HashMap<>();
            card.put("number", "4242424242424242");
            card.put("exp_month", 4);
            card.put("exp_year", 2022);
            card.put("cvc", "314");
            params.put("card", card);


        return Token.create(params);
    }

    @Override
    public Payment pay(Order order, Bill bill) {
        Charge charge = null;
        try {
            charge = stripeService.charge(createChargeRequest(bill));
        } catch (StripeException e) {
            e.printStackTrace();
        }
        Payment payment = new Payment();
        if(charge!=null)
        {
            payment.setPaymentRef(charge.getReceiptUrl());
            payment.setPaymentStatus(PaymentStatus.SUCCESS.toString());
        }
        else
        {
            payment.setPaymentStatus(PaymentStatus.FAILED.toString());
        }
        return payment;

    }
}
