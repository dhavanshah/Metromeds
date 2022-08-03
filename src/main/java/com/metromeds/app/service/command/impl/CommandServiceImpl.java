package com.metromeds.app.service.command.impl;

import com.metromeds.app.handlers.CommandHandler;
import com.metromeds.app.business.customer.CustomerController;
import com.metromeds.app.business.employee.EmployeeController;
import com.metromeds.app.business.order.OrdersController;
import com.metromeds.app.business.payment.PaymentsController;
import com.metromeds.app.business.product.ProductsController;
import com.metromeds.app.repository.*;
import com.metromeds.app.service.command.CommandService;
import com.metromeds.app.service.command.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Service
public class CommandServiceImpl implements CommandService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;
    private String resource;
    private String method;
    private Map<String, String> allParams;
    @Override
    public ResponseEntity executeController(String resource, String method, Map<String, String> allParams) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        this.resource = resource;
        this.method = method;
        this.allParams = allParams;
        return this.invokeControllerCommand();
    }

    public ResponseEntity invokeControllerCommand() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, IOException {
        CommandHandler command = null;
        String requestMethod = this.setRequestMethods(method, resource);
        switch (resource) {
            case "products":
                command = (CommandHandler) Class
                        .forName((ProductsController.class)
                        .getName()).getConstructor(ProductRepository.class)
                        .newInstance(productRepository);
                break;
            case "employees":
                command = (CommandHandler) Class
                        .forName((EmployeeController.class)
                        .getName()).getConstructor(EmployeeRepository.class)
                        .newInstance(employeeRepository);
                break;
            case "customers":
                command = (CommandHandler) Class
                        .forName((CustomerController.class)
                        .getName()).getConstructor(CustomerRepository.class)
                        .newInstance(customerRepository);
                break;
            case "payments":
                command = (CommandHandler) Class
                        .forName((PaymentsController.class)
                        .getName()).getConstructor(PaymentRepository.class,OrderRepository.class, BillRepository.class, PaymentService.class,CustomerRepository.class)
                        .newInstance(paymentRepository,orderRepository,billRepository,paymentService,customerRepository);
                break;
            case "orders":
                command = (CommandHandler) Class
                        .forName((OrdersController.class)
                                .getName()).getConstructor(OrderRepository.class, BillRepository.class)
                        .newInstance(orderRepository, billRepository);
                break;
            default:
                break;
        }
        assert command != null;
        return command.execute(requestMethod, allParams);
    }

    private String setRequestMethods(String method, String resource) {
        String requestType = method.toLowerCase();
        resource = resource.toLowerCase();
        String resourceName = resource.substring(0, 1).toUpperCase() + resource.substring(1);
        return requestType + resourceName;

    }
}