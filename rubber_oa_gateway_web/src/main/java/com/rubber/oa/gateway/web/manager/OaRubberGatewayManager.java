package com.rubber.oa.gateway.web.manager;

import com.rubber.oa.gateway.web.manager.handler.GatewayExceptionHandler;
import com.rubber.oa.gateway.web.manager.chain.IRubberGatewayChain;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import com.rubber.oa.geteway.api.query.OaGatewayQueryApi;
import com.rubber.oa.geteway.api.query.dto.RubberGatewayConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2022/4/21
 */
@Slf4j
@Component
public class OaRubberGatewayManager implements IRubberGatewayManager {


    @Autowired
    private OaGatewayQueryApi oaGatewayQueryApi;


    @Resource(name = "headChain")
    private IRubberGatewayChain IRubberGatewayChain;



    @Resource
    private GatewayExceptionHandler gatewayExceptionHandler;

    /**
     * 处理业务的服务
     *
     * @return true表示捕获到一个结果集合，需要返回 false表示没有捕获到一个对象，按照原有流程执行
     */
    @Override
    public boolean handlerService(RubberOaGatewayCtx ctx) {
        //解析对象
        doQueryGatewayConfig(ctx);
        if (ctx.getGatewayConfig() == null){
            log.info("当前没有配置url={}",ctx.getUrl());
            return false;
        }
        try {
            //查询网关db
            IRubberGatewayChain.handler(ctx);
        }catch (Exception e){
            //异常处理器
            gatewayExceptionHandler.handler(ctx,e);
        }
        return true;
    }


    /**
     * 查询网关的配置
     */
    private void doQueryGatewayConfig(RubberOaGatewayCtx ctx){
        RubberGatewayConfigDto gatewayConfig = oaGatewayQueryApi.getGatewayConfig(ctx.getUrl());
        ctx.setGatewayConfig(gatewayConfig);
    }

}
