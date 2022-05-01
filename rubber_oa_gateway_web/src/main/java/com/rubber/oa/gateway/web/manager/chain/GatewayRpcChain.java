package com.rubber.oa.gateway.web.manager.chain;

import com.alibaba.fastjson.JSONObject;
import com.rubber.oa.gateway.web.manager.handler.GatewayResultHandler;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import com.rubber.oa.geteway.api.query.dto.RubberGatewayConfigDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 远程调用的handler
 * @author luffyu
 * Created on 2022/4/21
 */
@Slf4j
@Component("gatewayRpcChain")
public class GatewayRpcChain extends AbstractRubberGatewayChain {

    @Autowired
    private GatewayResultHandler gatewayResultHandler;


    /**
     * 下一个流程
     *
     * @return 返回下一个节点处理器
     */
    @Override
    public IRubberGatewayChain nextHandler() {
        return null;
    }

    /**
     * 结果处理
     *
     * @param ctx 当前的ctx
     */
    @Override
    public void doHandler(RubberOaGatewayCtx ctx) {
        Object data = doHandlerRpc(ctx);
        gatewayResultHandler.handlerResultSuccess(ctx,data);
    }

    /**
     * 发起rpc的调用
     */
    public Object doHandlerRpc(RubberOaGatewayCtx ctx){
        try {
            RubberGatewayConfigDto gatewayConfig = ctx.getGatewayConfig();
            ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
            reference.setInterface(gatewayConfig.getServiceInterface());
            reference.setVersion(gatewayConfig.getServiceVersion());
            reference.setTimeout(gatewayConfig.getServiceTimeOut() == null ? 3000 : gatewayConfig.getServiceTimeOut());
            reference.setGroup("*");
            reference.setGeneric("true");
            // 声明为泛化接口
            // 用org.apache.dubbo.rpc.service.GenericService可以替代所有接口引用
            GenericService genericService = reference.get();

            JSONObject params = ctx.getParams();
            params.remove("session");
            params.remove("system");
            params.put("session", ctx.getSession());
            params.put("system", ctx.getSystem());
            // 基本类型以及Date,List,Map等不需要转换，直接调用
            return genericService.$invoke(gatewayConfig.getServiceMethod(), new String[]{"com.alibaba.fastjson.JSONObject"}, new Object[]{params});
        } catch (Exception e) {
            log.info("调用泛化接口出错，request={},msg={}", ctx.getGatewayConfig(), e.getMessage());
            throw e;
        }finally {

        }

    }
}
