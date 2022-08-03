package com.metromeds.app.service.command.impl;

import com.metromeds.app.handlers.CommandNotAvailableException;
import com.metromeds.app.business.payment.IPayment;
import com.metromeds.app.business.payment.StripeAdapter;
import com.metromeds.app.business.payment.WalletAdapter;
import com.metromeds.app.repository.CustomerRepository;
import com.metromeds.app.service.StripeService;
import com.metromeds.app.service.command.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Service
public class PaymentServiceImpl implements PaymentService {
    private String resource;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StripeService stripeService;
    @Override
    public IPayment executePayment(String resource) throws CommandNotAvailableException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        this.resource = resource;
        return this.invokePaymentCommand();
    }

    private IPayment invokePaymentCommand() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        IPayment command = null;
        switch (resource) {
            case "wallet":
                command = (IPayment) Class
                        .forName((WalletAdapter.class)
                                .getName()).getConstructor(CustomerRepository.class)
                        .newInstance(customerRepository);
                break;
            case "stripe":
                command = (IPayment) Class
                        .forName((StripeAdapter.class).getName()).getConstructor(StripeService.class)
                        .newInstance(stripeService);

                break;
            default:
                break;
        }
        return command;
    }

    }
