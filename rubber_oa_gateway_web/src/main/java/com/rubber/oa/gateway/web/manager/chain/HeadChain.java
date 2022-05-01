package com.rubber.oa.gateway.web.manager.chain;

import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2022/4/30
 */
@Service("headChain")
public class HeadChain extends AbstractRubberGatewayChain {

    @Resource(name = "gatewayParamsChain")
    private IRubberGatewayChain iRubberGatewayChain;


    /**
     * 下一个流程
     *
     * @return 返回下一个节点处理器
     */
    @Override
    public IRubberGatewayChain nextHandler() {
        return iRubberGatewayChain;
    }

    /**
     * 结果处理
     *
     * @param ctx 当前的ctx
     */
    @Override
    public void doHandler(RubberOaGatewayCtx ctx) {

    }
}
