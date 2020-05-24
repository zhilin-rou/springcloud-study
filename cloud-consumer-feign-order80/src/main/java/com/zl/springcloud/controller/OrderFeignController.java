package com.zl.springcloud.controller;

import com.zl.springcloud.entites.CommonResult;
import com.zl.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        CommonResult paymentById = paymentFeignService.getPaymentById(id);
        return paymentById;
    }
}
