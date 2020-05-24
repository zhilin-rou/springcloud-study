package com.zl.springcloud.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CommonResult<T>   {
    private Integer code;
    private String message;
    private T data;

    public  CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
