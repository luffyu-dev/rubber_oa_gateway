package com.rubber.oa.gateway.web.manager.chain;

import com.rubber.base.components.util.result.RubberSession;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录认证的login信息
 * @author luffyu
 * Created on 2022/4/21
 */
@Slf4j
@Component("gatewaySessionAuthChain")
public class GatewaySessionAuthChain extends AbstractRubberGatewayChain {

    @Resource(name = "gatewayRpcChain")
    private IRubberGatewayChain iRubberGatewayChain;


    /**
     * 需要登录校验
     */
    private static final Integer NEED_LOGIN_FLAG = 1;


    private static final String COOKIE_KEY = "cookies";


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
        RubberSession session = ctx.getSession();
        if (session == null){
            session = new RubberSession();
        }
        log.info("start gatewaySessionAuthHandler handler");
        if (NEED_LOGIN_FLAG.equals(ctx.getGatewayConfig().getCheckLoginFlag())){
            String cookies = ctx.getRequest().getHeader(COOKIE_KEY);
            session.setOperator(cookies);
            session.setUserId(0);
        }
        ctx.setSession(session);
    }
}
