package com.zl.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfoOK(Integer id) {
        return "paymentInfoOK---------";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "paymentInfoTimeOut--------";
    }
}
