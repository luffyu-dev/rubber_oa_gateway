package com.rubber.oa.gateway.web.servlet;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.rubber.oa.gateway.web.manager.IRubberGatewayManager;
import com.rubber.oa.gateway.web.manager.handler.GatewayExceptionHandler;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luffyu
 * Created on 2022/4/21
 */
@Slf4j
public class OaGatewayWebServlet extends DispatcherServlet {

    @Autowired
    private IRubberGatewayManager iRubberGatewayManager;

    @Resource
    private GatewayExceptionHandler gatewayExceptionHandler;



    /**
     * Exposes the DispatcherServlet-specific request attributes and delegates to {@link #doDispatch}
     * for the actual dispatching.
     *
     * @param request
     * @param response
     */
    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info(">>>>>>当前的请求参数>>>>>>>"+request.getRequestURI());
        RubberOaGatewayCtx ctx = createGatewayCtx(request,response);
        try (Entry entry = SphU.entry(ctx.getUrl())) {
            if (iRubberGatewayManager.handlerService(ctx)) {
                return;
            }
            super.doService(request, response);
        }catch (BlockException e){
            gatewayExceptionHandler.handler(null,e);
        }
    }



    private RubberOaGatewayCtx createGatewayCtx(HttpServletRequest request, HttpServletResponse response){
        RubberOaGatewayCtx ctx = new RubberOaGatewayCtx();
        ctx.setUrl(request.getRequestURI());
        ctx.setRequest(request);
        ctx.setResponse(response);
        return ctx;
    }
}
