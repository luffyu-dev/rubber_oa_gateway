package com.rubber.oa.gateway.web.controller;

import com.rubber.oa.geteway.api.query.OaGatewayQueryApi;
import com.rubber.oa.geteway.api.query.dto.RubberGatewayConfigDto;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author luffyu
 * Created on 2022/4/23
 */
@RestController
@RequestMapping("/config")
public class OaGatewayConfigQueryController {



    @GetMapping("/info")
    public RubberGatewayConfigDto doQuery(String url){
        return new RubberGatewayConfigDto();
    }


}
