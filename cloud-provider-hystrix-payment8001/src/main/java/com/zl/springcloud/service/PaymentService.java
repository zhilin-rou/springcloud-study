package com.zl.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
//**********服务降级***********
    public String paymentInfoOK(Integer id){
        return "当前线程: "+Thread.currentThread().getName()+"paymentInfo_OK,id："+id+"\t"+"O(∩_∩)O哈哈~";
    }
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfoTimeOut(Integer id){
        int timeout=4;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"   耗时(秒)："+timeout;
    }

    public String  paymentInfoTimeOutHandler(Integer id){
        return "程序运行繁忙或报错,请稍后再试*****"+"当前线程: "+Thread.currentThread().getName()+id+"\t "+"orz!";
    }
//    *************服务熔断
@HystrixCommand(fallbackMethod="paymentCircuitBreakerFallback", commandProperties={
        @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),//是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期 "
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
})
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException();
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t "+"调用成功,流水号: "+serialNumber;
}
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){
        return "id不能为负数,请稍后再试~ id: "+ id;
    }
}
