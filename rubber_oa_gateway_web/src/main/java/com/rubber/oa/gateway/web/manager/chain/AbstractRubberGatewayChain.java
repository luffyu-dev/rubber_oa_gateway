package com.rubber.oa.gateway.web.manager.chain;

import com.rubber.oa.gateway.web.manager.handler.GatewayExceptionHandler;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2022/4/21
 */
public abstract class AbstractRubberGatewayChain implements IRubberGatewayChain {


    @Resource(name = "gatewayExceptionHandler")
    private GatewayExceptionHandler exceptionHandler;


    /**
     * 下一个流程
     * @return 返回下一个节点处理器
     */
    public abstract IRubberGatewayChain nextHandler();


    /**
     * 结果处理
     * @param ctx 当前的ctx
     */
    public abstract void doHandler(RubberOaGatewayCtx ctx);


    /**
     * 处理函数
     *
     * @param ctx 如参信息
     */
    @Override
    public void handler(RubberOaGatewayCtx ctx) {
        try {
            doHandler(ctx);
            IRubberGatewayChain nextHandler = nextHandler();
            if (nextHandler != null){
                nextHandler.handler(ctx);
            }
        }catch (Exception e){
            //结果异常处理
            exceptionHandler.handler(ctx,e);
        }finally {

        }

    }
}
