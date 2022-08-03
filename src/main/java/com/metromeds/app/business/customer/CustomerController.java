package com.metromeds.app.business.customer;

import com.metromeds.app.handlers.CommandHandler;
import com.metromeds.app.interceptor.response.ApiResponse;
import com.metromeds.app.repository.CustomerRepository;
import com.metromeds.app.models.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController implements CommandHandler {

    @Autowired
    private CustomerRepository customerRepository;
    private Map<String, String> params;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity execute(String method, Map<String, String> params) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        this.params = params;
        CustomerController controller = new CustomerController(this.customerRepository);
        controller.params = this.params;
        Method invokeCommand = this.getClass().getMethod(method);
        Object result = invokeCommand.invoke(controller);
        ApiResponse response = new ApiResponse();
        return response.setServletResponse(result);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        if (this.params.containsKey("id")) {
            customers.add(customerById(Integer.parseInt(this.params.get("id"))));
        } else {
            customerRepository.findAll().forEach(customers::add);
        }
        return customers;
    }

    public Customer customerById(Integer id) {
        return customerRepository.findOne(id);
    }

    public void saveOrUpdate(Customer customer) {
        customerRepository.save(customer);
    }

    public void delete(int id) {
        customerRepository.delete(id);
    }

    public void setParameters(Map<String, String> parms) {
        this.params = parms;
    }
}
