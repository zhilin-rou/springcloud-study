package com.zl.springcloud.service;

import com.zl.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id")long id);
}
