package com.metromeds.app.business.discount;
import com.metromeds.app.models.customer.Customer;
import com.metromeds.app.models.order.Order;
import com.metromeds.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscountEngine {
    private DiscountStrategy discountStrategy = DiscountStrategy.FIXED;
    private IDiscount discount;

    @Autowired
    private CustomerRepository customerRepository;

    public Order applyDiscount(Order order){
        this.setDiscountStrategy(order.getPromoCode());
        this.setDiscountType(order.getPromoCode(),order.getCustomerId());
        double discountedAmount =this.discountStrategy.applyDiscount(order.getCartPrice(),discount);
        order.setAmountPayable(discountedAmount);
        order.setDiscountAmount(order.getCartPrice() - discountedAmount);
        return order;
    }

    private void setDiscountType(String promoCode,int customerId){

        PromoCodes code = PromoCodes.valueOf(promoCode);
        if(promoCode != null &&  !promoCode.isEmpty()) {
            switch (code) {
                case WEEKEND10:
                    this.discount = new Weekend10();
                    break;
                case SUMMER15:
                    this.discount = new Summer15();
                    break;
                default:
                    break;
            }
        }
        else{
            Customer customer = customerRepository.findOne(customerId);
            switch (customer.getCustomerTier()){
                case "Gold":
                    this.discount = new GoldCustomerDiscount();
                    break;
                case "Platinum":
                    this.discount = new PlatinumCustomerDiscount();
                    break;
                case "Silver":
                    this.discount = new SilverCustomerDiscount();
                    break;
                default:
                    break;
            }
        }

    }

    private void setDiscountStrategy(String promoCode){
        if(promoCode != null &&  !promoCode.isEmpty()) {
            this.discountStrategy = DiscountStrategy.PERCENT;
        }
    }



}
