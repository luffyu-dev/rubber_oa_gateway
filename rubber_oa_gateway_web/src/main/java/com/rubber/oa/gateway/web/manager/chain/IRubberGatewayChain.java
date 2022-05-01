package com.rubber.oa.gateway.web.manager.chain;

import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;

/**
 * @author luffyu
 * Created on 2022/4/21
 */
@FunctionalInterface
public interface IRubberGatewayChain {
    /**
     * 处理函数
     * @param ctx 如参信息
     */
    void handler(RubberOaGatewayCtx ctx);
}
