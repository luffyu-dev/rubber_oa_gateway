package com.rubber.oa.gateway.web.manager;


import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;

/**
 * @author luffyu
 * Created on 2022/4/21
 */
public interface IRubberGatewayManager {


    /**
     * 处理业务的服务
     * @param ctx 当前的请求参数
     * @return true表示捕获到一个结果集合，需要返回 false表示没有捕获到一个对象，按照原有流程执行
     */
    boolean handlerService(RubberOaGatewayCtx ctx);


}
