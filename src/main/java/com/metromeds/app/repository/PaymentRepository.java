package com.metromeds.app.repository;

import com.metromeds.app.models.payment.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}