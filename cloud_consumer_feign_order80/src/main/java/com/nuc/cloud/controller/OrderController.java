package com.nuc.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.nuc.cloud.apis.PayFeignApi;
import com.nuc.cloud.entities.PayDTO;
import com.nuc.cloud.resp.ResultCodeEnum;
import com.nuc.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    /**
     * 新增一条支付相关流水记录
     * @param payDTO
     * @return
     */
    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData resultData=payFeignApi.addPay(payDTO);
        return resultData;
    }

    /**
     * 按照主键记录查询支付流水信息
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData=null;
        try{
            System.out.println("调用开始------："+ DateUtil.now());
            resultData=payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用结束------："+DateUtil.now());
            ResultData.fail(ResultCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    /**
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping("/feign/pay/mylb")
    public String mylb(){
        return payFeignApi.mylb();

    }
}
