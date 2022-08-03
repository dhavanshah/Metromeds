package com.metromeds.app.business.order;
import com.metromeds.app.models.order.Order;

import java.text.ParseException;

public class OrderBuilder {
    OrderPacking ord;
        public void getPickUp(Order order) throws ParseException
        {
             ord = new PickUp();
             ord.getCost(order);
             ord.getShippingFee(order);
             ord.getDeliveryDate(order);
             ord.getProduct(order);

        }
        public void getDelivery(Order order) throws ParseException {
            ord = new Delivery();
            ord.getCost(order);
            ord.getShippingFee(order);
            ord.getDeliveryDate(order);
            ord.getProduct(order);

        }


}

