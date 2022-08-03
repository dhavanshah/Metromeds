package com.metromeds.app.service.command;

import com.metromeds.app.handlers.CommandNotAvailableException;
import com.metromeds.app.business.payment.IPayment;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface PaymentService {
   IPayment executePayment(String resource) throws CommandNotAvailableException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;
}
