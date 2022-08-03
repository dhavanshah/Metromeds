package com.metromeds.app.business.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metromeds.app.handlers.CommandHandler;
import com.metromeds.app.business.customer.CustomerLoyalty;
import com.metromeds.app.interceptor.response.ApiResponse;
import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.customer.Customer;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.models.payment.Payment;
import com.metromeds.app.models.payment.PaymentStatus;
import com.metromeds.app.repository.BillRepository;
import com.metromeds.app.repository.CustomerRepository;
import com.metromeds.app.repository.OrderRepository;
import com.metromeds.app.repository.PaymentRepository;
import com.metromeds.app.service.command.PaymentService;
import com.metromeds.app.service.helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

@RestController
public class PaymentsController implements CommandHandler {

    PaymentRepository paymentRepository;
    PaymentService paymentService;
    BillRepository billRepository;
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    private Map<String, String> params;

    public PaymentsController(PaymentRepository paymentRepository, OrderRepository orderRepository, BillRepository billRepository, PaymentService paymentService, CustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.billRepository = billRepository;
        this.paymentService = paymentService;
        this.customerRepository = customerRepository;

    }

    public ResponseEntity execute(String method, Map<String, String> parms) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        this.params = parms;
        PaymentsController controller = new PaymentsController(this.paymentRepository,this.orderRepository,this.billRepository,this.paymentService, this.customerRepository);
        controller.params = this.params;
        Method invokeCommand = this.getClass().getMethod(method);
        Object result = invokeCommand.invoke(controller);
        ApiResponse response = new ApiResponse();
        return response.setServletResponse(result);
    }

    public List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();
        if (this.params.containsKey("id")) {
            payments.add(paymentById(Long.parseLong(this.params.get("id"))));
        }
        else {
            this.paymentRepository.findAll().forEach(payments::add);
        }
        return payments;
    }

    public Payment paymentById(Long id) {
        return paymentRepository.findOne(id);
    }

    public ResponseEntity postPayments() throws NoSuchFieldException, ParseException, IllegalAccessException, JsonProcessingException {
        Payment payment = new Payment();
        payment = (Payment) helper.getVariables(payment,this.params);

        Bill bill = billRepository.findOne(payment.getBillId());
        Order order = orderRepository.findOne(bill.getOrderId());
        Customer customer = customerRepository.findOne(order.getCustomerId());
        try {
            Payment result = paymentService.executePayment(payment.getPaymentType()).pay(order,bill);
            payment.setId((paymentRepository.count() + 1));
            payment.setPaymentStatus(result.getPaymentStatus());
            payment.setPaymentRef(result.getPaymentRef());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        Map <String,String> bodyMap = new HashMap<>();
        if(payment.getPaymentStatus().equals(PaymentStatus.SUCCESS.toString())) {
            payment.setPaymentDate(new Date());
            paymentRepository.save(payment);
            new CustomerLoyalty(customerRepository, orderRepository, billRepository).updateLoyaltyPoints(order, customer);
            new CustomerLoyalty(customerRepository, orderRepository, billRepository).updateCustomerTier(customer);
            bodyMap.put("message","Payment Successfully Completed");
            ObjectMapper objectMapper = new ObjectMapper();
            String response = objectMapper.writeValueAsString(payment);
            bodyMap.put("object",response);
        }else
            bodyMap.put("message","Payment Failed");

        return new ResponseEntity(bodyMap, HttpStatus.OK);
    }



    public void putPayments(Payment payment) {
        paymentRepository.save(payment);
    }

    public ResponseEntity deletePayments() {
        if (this.params.containsKey("id")) {
            paymentRepository.delete(Long.parseLong(this.params.get("id")));
            return new ResponseEntity("Order Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity("Failed to delete order", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
