package com.metromeds.app.business.discount;

public enum DiscountStrategy {

    FIXED{
        @Override
        public double applyDiscount(double originalAmount, IDiscount discount) {
            double discountAmount = discount.getDiscount();
            if(originalAmount > 30 && originalAmount > discountAmount)
                return originalAmount - discountAmount;
            return originalAmount;
        }
    },
    PERCENT{
        @Override
        public double applyDiscount(double originalAmount, IDiscount discount) {
            double discountAmount = discount.getDiscount();
            if(originalAmount > 20)
                return discountAmount * originalAmount;
            return originalAmount;
        }
    };

    public abstract double applyDiscount(double originalAmount, IDiscount discount);
}
