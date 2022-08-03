package com.metromeds.app.business.order;
import com.metromeds.app.handlers.CommandHandler;
import com.metromeds.app.business.bill.BillGenerationMemento;
import com.metromeds.app.interceptor.response.ApiResponse;
import com.metromeds.app.models.bill.Bill;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.repository.BillRepository;
import com.metromeds.app.repository.OrderRepository;
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
public class OrdersController implements CommandHandler {
    OrderRepository orderRepository;
    BillRepository billRepository;

    private Map<String, String> params;

    public OrdersController(OrderRepository orderRepository, BillRepository billRepository) {
        this.orderRepository = orderRepository;
        this.billRepository = billRepository;
    }

    public ResponseEntity execute(String method, Map<String, String> parms) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        this.params = parms;
        OrdersController controller = new OrdersController(this.orderRepository, this.billRepository);
        controller.params = this.params;
        Method invokeCommand = this.getClass().getMethod(method);
        Object result = invokeCommand.invoke(controller);
        ApiResponse response = new ApiResponse();
        return response.setServletResponse(result);
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        if (this.params.containsKey("id")) {
            orders.add(orderById(Integer.parseInt(this.params.get("id"))));
        } else {
            orderRepository.findAll().forEach(orders::add);
        }
        return orders;
    }

    public ResponseEntity createOrder(Order order) throws ParseException {
        if(order.getOrderType().equals("pickup")) {
            new OrderBuilder().getPickUp(order);
        }
        else {
            new OrderBuilder().getDelivery(order);
        }
        BillGenerationMemento billGenerationMemento = new BillGenerationMemento();
        Bill bill = billGenerationMemento.generateBill(order, this.billRepository.count());
        if (order.getIsPrescribed() == 1) {
            billRepository.save(bill);
            orderRepository.save(order);
            return new ResponseEntity("Order Placed Successfully", HttpStatus.OK);
        } else {
            billGenerationMemento.undoBillGeneration();
            return new ResponseEntity("Failed to Create, Please provide prescription", HttpStatus.PRECONDITION_FAILED);
        }
    }

    public Order orderById(Integer id) {
        return orderRepository.findOne(id);
    }

    public ResponseEntity postOrders() throws NoSuchFieldException, IllegalAccessException, ParseException {
        Order order = new Order();
        order = (Order) helper.getVariables(order,this.params);
        return this.createOrder(order);
    }

    public void putOrders(Order order) {
        orderRepository.save(order);
    }

    public ResponseEntity deleteOrders() {
        if (this.params.containsKey("id")) {
            orderRepository.delete(Integer.parseInt(this.params.get("id")));
            return new ResponseEntity("Order Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity("Failed to delete order", HttpStatus.EXPECTATION_FAILED);
        }
    }
}

